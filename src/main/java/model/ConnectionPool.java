package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<Connection>();

    private ConnectionPool() {
        for (int i = 0; i < Integer.parseInt(ConnectionSource.getPoolVolume()); i++) {
            createConnection();
        }
    }

    private void createConnection() {
        try {
            Class.forName(ConnectionSource.getDriver());
            Connection con = DriverManager.getConnection(ConnectionSource.getUrl(), ConnectionSource.getUsername(),
                    ConnectionSource.getPassword());
            availableConnections.add(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection(){
        return availableConnections.poll();
    }

    public void putBackConnection(Connection con) {
        availableConnections.add(con);
    }
}