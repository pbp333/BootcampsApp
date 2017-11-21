package org.academiadecodigo.bootcampsapp.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by codecadet on 17/11/17.
 */
public class ConnectionManager {

    Connection connection = null;

    public Connection getConnection(){

        if (connection == null){

            try {

                DriverManager.registerDriver(new com.mysql.jdbc.Driver ());

                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "");
            } catch (SQLException e) {
                System.out.println("Failed to connect to database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void close(){

        if(connection != null){

            try {
                connection.close();

            } catch (SQLException e) {
                System.out.println("Failed to close connection.");
                e.printStackTrace();
            }
        }
    }
}
