package org.academiadecodigo.bootcampsapp.service;



import org.academiadecodigo.bootcampsapp.model.User;
import org.academiadecodigo.bootcampsapp.persistence.ConnectionManager;

import java.sql.*;

/**
 * Created by codecadet on 17/11/17.
 */
public class JdbcUserService implements UserService {

    ConnectionManager connectionManager;

    Connection connection;

    public JdbcUserService(ConnectionManager connectionManager) {

        this.connectionManager = connectionManager;

        connection = connectionManager.getConnection();
    }

    @Override
    public boolean authenticate(String username, String password) {


        String query = "select username, password from users where username = ? and password = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                statement.close();

                return true;
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public void addUser(User user) {

        String query = "insert into users(username, password, email) values(?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());

            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(User user) {

        Connection connection = connectionManager.getConnection();

        String query = "delete from users where name = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getUsername());

            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public User findByName(String name) {

        User user = null;

        Connection connection = connectionManager.getConnection();

        String query = "select * from users where username = ?";


        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public int count() {

        int result = 0;

        Connection connection = connectionManager.getConnection();

        String query = "select count(*) from users";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {

                result = resultSet.getInt(1);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean emailAvailability(String userEmail) {

        Connection connection = connectionManager.getConnection();

        String query = "select email from users";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            int i = 0;

            while (resultSet.next()) {

                System.out.println(i);

                i++;

                System.out.println(resultSet.getString(1));

                if (resultSet.getString(1).equals(userEmail)) {
                    statement.close();
                    return false;
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
