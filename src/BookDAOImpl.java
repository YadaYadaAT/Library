import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOImpl implements BookDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    // SQL queries for CRUD operations
    private static final String SELECT_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String INSERT = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE books SET title = ?, author = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM books WHERE id = ?";

    // Connection object for interacting with the database
    private Connection connection;

    // Constructor to establish database connection
    public BookDAOImpl() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    @Override
    public Book findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> findAllBooks(){
        ArrayList<Book> allBooks = new ArrayList<>();
        try {
            String SELECT_ALL_BOOKS = "SELECT * FROM Book";
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet rslt = stmt.executeQuery();
            while (rslt.next()) {
                int id = rslt.getInt("id");
                String title = rslt.getString("title");
                String author = rslt.getString("author");
                Book book = new Book(id, title, author);
                allBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

    // Close database connection
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
