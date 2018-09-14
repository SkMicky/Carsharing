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
    private ResourceBundle resource = ResourceBundle.getBundle("resources/properties/database/database");

    private ConnectionPool() {
        String poolVolume = resource.getString("db.connectionPoolVolume");
        for (int i = 0; i < Integer.parseInt(poolVolume); i++) {
            createConnection();
        }
    }

    private void createConnection() {
        Connection con = null;
        String driver = resource.getString("db.driver");
        String url = resource.getString("db.url");
        String username = resource.getString("db.user");
        String password = resource.getString("db.password");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
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

