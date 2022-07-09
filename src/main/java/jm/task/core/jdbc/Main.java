package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        User user = new User();
        Util.getConnection();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Anton", "Sed", (byte) 45);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        userDaoJDBC.saveUser("Antonina", "Sed", (byte) 35);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        userDaoJDBC.saveUser("Max", "Pain", (byte) 55);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        userDaoJDBC.saveUser("Helena", "Beauty", (byte) 25);
        System.out.println("User с именем – " + user.getName() + " " + user.getLastName() + " добавлен в базу данных");
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
        try {
            Util.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
