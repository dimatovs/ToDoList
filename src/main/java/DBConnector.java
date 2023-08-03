import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
    private static final String dbUrl = "jdbc:mysql://localhost:3306/todolist";
    private static final String login = "root";
    private static final String password = "1234";
    private static Connection connection;

    public void addTask(String task) {
        try {
            connection = DriverManager.getConnection(dbUrl, login, password);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tasklist(task) VALUES (?)");
            statement.setString(1,task);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbUrl, login, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tasklist WHERE state != 2");
            while (result.next()) {
                allTasks.add(new Task(result.getInt(1), result.getString(2),result.getInt(3)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return allTasks;
    }

    public void updateTask(int id, int status) {
        try {
            connection = DriverManager.getConnection(dbUrl, login, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE tasklist SET state=? WHERE id = ?");
            statement.setInt(1,status);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteDoneTasks() {
        try {
            connection = DriverManager.getConnection(dbUrl, login, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE tasklist SET state=2 WHERE state = 1");
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

