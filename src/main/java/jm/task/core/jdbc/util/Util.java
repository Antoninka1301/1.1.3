package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String dbURL = "jdbc:mysql://localhost/base1";
    private static String dbUsername = "root";
    private static String dbPassword = "Antonchik0507";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
