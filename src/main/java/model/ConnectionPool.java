package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<Connection>();

    private ConnectionSource connectionSource = new ConnectionSource();

    private ConnectionPool() {
        for (int i = 0; i < Integer.parseInt(connectionSource.getPoolVolume()); i++) {
            createConnection();
        }
    }

    private void createConnection() {
        Connection con = null;
        try {
            Class.forName(connectionSource.getDriver());
            con = DriverManager.getConnection(connectionSource.getUrl(), connectionSource.getUsername(),
                    connectionSource.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Don't connected!");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        availableConnections.add(con);
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

