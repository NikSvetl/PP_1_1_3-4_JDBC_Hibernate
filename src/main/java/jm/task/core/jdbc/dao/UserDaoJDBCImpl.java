package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user " +
                "(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30)," +
                "lastName VARCHAR(30), age INT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Oшибка создания таблицы");
        }
    }

    public void dropUsersTable() {
        String dropSql = "DROP TABLE IF EXISTS user";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropSql);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSql = "INSERT INTO  user (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + ", " + lastName +
                    ", " + age + " (добавлен)");
        } catch (SQLException e) {
            System.out.println("Ошибка добавления пользователя");
        }
    }

    public void removeUserById(long id) {
        String removeSql = "DELETE  FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeSql)) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с id = " + id + " удален");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователя с id = " + id);
        }
    }

    public List<User> getAllUsers() {
        String allUsersSql = "SELECT * FROM user";

        List<User> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(allUsersSql)) {
            while ((resultSet.next())) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                result.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка вывода");
        }
        return result;
    }

    public void cleanUsersTable() {
        String cleanSql = "TRUNCATE TABLE user";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(cleanSql);
            System.out.println("Clean table");
        } catch (SQLException e) {
            System.out.println("Failed clean table");
        }
    }
}
