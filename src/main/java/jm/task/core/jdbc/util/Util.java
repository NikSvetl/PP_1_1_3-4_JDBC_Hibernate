package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/users?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Cоединение с БД установленно");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Соединение не установленно");

        }
        return connection;
    }
}
