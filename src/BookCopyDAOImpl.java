import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookCopyDAOImpl implements BookCopyDAO {
    // JDBC URL, username, and password for connecting to the database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    // SQL queries for CRUD operations
    private static final String SELECT_BY_ID = "SELECT * FROM book_copies WHERE id = ?";
    private static final String INSERT = "INSERT INTO book_copies (id, available) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE book_copies SET available = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM book_copies WHERE id = ?";

    // Connection object for interacting with the database
    private Connection connection;

    // Constructor to establish database connection
    public BookCopyDAOImpl() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    @Override
    public BookCopy findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new BookCopy(resultSet.getInt("id"), resultSet.getBoolean("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(BookCopy bookCopy) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setInt(1, bookCopy.getId());
            statement.setBoolean(2, bookCopy.isAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BookCopy bookCopy) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setBoolean(1, bookCopy.isAvailable());
            statement.setInt(2, bookCopy.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(BookCopy bookCopy) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, bookCopy.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<BookCopy> findAllBookCopies(){
        ArrayList<BookCopy> allCopies = new ArrayList<>();
        try {
            String SELECT_ALL_COPIES = "SELECT * FROM BookCopy";
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_COPIES);
            stmt.setBoolean(1, true);
            ResultSet rslt = stmt.executeQuery();
            while (rslt.next()) {
                int id = rslt.getInt("id");
                int titleId = rslt.getInt("titleId");
                boolean available = rslt.getBoolean("available");
                String title = rslt.getString("title");
                String author = rslt.getString("author");
                BookCopy bookCopy = new BookCopy(titleId, title, author, id, available);
                allCopies.add(bookCopy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCopies;
    }

    @Override
    public ArrayList<BookCopy> findBorrowedCopies(){
        ArrayList<BookCopy> borrowedCopies = new ArrayList<>();
        try {
            String SELECT_AVAILABLE_COPIES = "SELECT * FROM BookCopy WHERE available = 0";
            PreparedStatement stmt = connection.prepareStatement(SELECT_AVAILABLE_COPIES);
            stmt.setBoolean(1, true);
            ResultSet rslt = stmt.executeQuery();
            while (rslt.next()) {
                BookCopy bookCopy = new BookCopy(rslt.getInt("id"), false);
                borrowedCopies.add(bookCopy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedCopies;
    }

    @Override
    public ArrayList<BookCopy> findAvailableCopies(){
        ArrayList<BookCopy> availableCopies = new ArrayList<>();
        try {
            String SELECT_AVAILABLE_COPIES = "SELECT * FROM BookCopy WHERE available = 1";
            PreparedStatement stmt = connection.prepareStatement(SELECT_AVAILABLE_COPIES);
            stmt.setBoolean(1, true);
            ResultSet rslt = stmt.executeQuery();
            while (rslt.next()) {
                BookCopy bookCopy = new BookCopy(rslt.getInt("id"), true);
                availableCopies.add(bookCopy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableCopies;
    }

    // Close database connection
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

