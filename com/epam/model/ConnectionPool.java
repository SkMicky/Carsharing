package com.epam.carsharing.entity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool uniqueInstance;
    private BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<Connection>(25);

    private ConnectionPool(int connectionsCount) {
        for (int i = 0; i < connectionsCount; i++) {
            availableConnections.add(createConnect());
        }
    }

    private Connection createConnect() {
        Connection con = null;
        ResourceBundle resource = ResourceBundle.getBundle("resources/properties/database/database");
        String url = resource.getString("db.url");
        String username = resource.getString("db.user");
        String password = resource.getString("db.password");
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Don't connected!");
        }
        return con;
    }

    public static ConnectionPool getUniqueInstance() {
        if(uniqueInstance == null){
            uniqueInstance = new ConnectionPool(15);
        }
        return uniqueInstance;
    }

    public Connection getConnection(){
        return availableConnections.poll();
    }

    public void putBackConnection(Connection con) {
        availableConnections.add(con);
    }
}

