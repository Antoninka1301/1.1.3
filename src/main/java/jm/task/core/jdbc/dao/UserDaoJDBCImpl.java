package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String myTableName = "CREATE TABLE IF NOT EXISTS user (id BIGINT(19) AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45), lastName VARCHAR(45), age TINYINT(3))";


        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(myTableName);
            System.out.println("Table added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String DROP_TABLE = "DROP TABLE IF EXISTS user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String INSERT_USER = "INSERT INTO user(name, lastName, age) VALUES (?,?,?)";
        //    List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String DELETE_USER = "DELETE FROM user WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String SELECT_ALL = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_ALL);
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String CLEAN_TABLE = "TRUNCATE TABLE  user";
        try (Statement statement = connection.createStatement()) {
            statement.execute(CLEAN_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
