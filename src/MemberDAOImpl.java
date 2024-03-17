import java.sql.*;
import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO{

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String INSERT = "INSERT INTO users (id, name, MaxOnLoan) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET name = ?, MaxOnLoan = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";

    private final Connection connection;

    public MemberDAOImpl() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    @Override
    public Member findById(int id){
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Member(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("MaxOnLoan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Member member) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setInt(1, member.getId());
            statement.setString(2, member.getName());
            statement.setInt(3, member.getMAX_ON_LOAN());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Member member) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, member.getName());
            statement.setInt(2, member.getMAX_ON_LOAN());
            statement.setInt(3, member.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Member member) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, member.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ArrayList<Object> getMemberArrayList() {
        ArrayList<Object> memberObjectList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            memberObjectList.add(findById(i));
        }
        return memberObjectList;
    }
}