package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<Connection>();
    private final int CONNECTIONS_COUNT = Integer.parseInt(ConnectionSource.getPoolVolume());
    private final String URL = ConnectionSource.getUrl();
    private final String USERNAME = ConnectionSource.getUsername();
    private final String PASSWORD = ConnectionSource.getPassword();
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());



    private ConnectionPool() {
        for (int i = 0; i < CONNECTIONS_COUNT; i++) {
            createConnection();
        }
    }

    private void createConnection(){
        try {
            Class.forName(ConnectionSource.getDriver());
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            availableConnections.add(con);
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (ClassNotFoundException e){
            LOGGER.error(e);
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