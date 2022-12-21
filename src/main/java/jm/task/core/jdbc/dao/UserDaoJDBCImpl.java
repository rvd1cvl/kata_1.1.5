package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.connection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (connection) {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(45)," +
                    " lastName VARCHAR(45), age INT(3));");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (connection) {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (connection) {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate("INSERT INTO users (name, lastname, age) values (" + name + ", " + lastName + ", " + age + ")");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (connection) {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate("DELETE FROM users WHERE id = " + id + ";");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try (connection) {
            connection.setAutoCommit(false);
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                resultList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try (connection) {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate("TRUNCATE TABLE users;");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
