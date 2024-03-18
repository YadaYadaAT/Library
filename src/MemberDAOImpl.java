import java.sql.*;
import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO{

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String SELECT_BY_ID = "SELECT * FROM Member WHERE id = ?";
    private static final String INSERT = "INSERT INTO Member (id, name, MaxOnLoan, cost) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Member SET name = ?, MaxOnLoan = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Member WHERE id = ?";

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
                return new Member(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("MaxOnLoan"), resultSet.getInt("cost"));
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
            statement.setInt(4, member.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Member member) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setInt(1, member.getId());
            statement.setString(2, member.getName());
            statement.setInt(3, member.getMAX_ON_LOAN());
            statement.setInt(4, member.getCost());
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

    @Override
    public ArrayList<Member> findAllMembers(){
        ArrayList<Member> membersList = new ArrayList<>();
        try {
            String SELECT_ALL_MEMBERS = "SELECT * FROM Member";
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_MEMBERS);
            ResultSet rslt = stmt.executeQuery();
            while (rslt.next()) {
                int id = rslt.getInt("id");
                String name = rslt.getString("name");
                int MAX_ON_LOAN = rslt.getInt("MAX_ON_LOAN");
                int cost = rslt.getInt("cost");
                Member member = new Member(id, name, MAX_ON_LOAN, cost);
                membersList.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membersList;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}