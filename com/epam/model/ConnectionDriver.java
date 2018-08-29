package com.epam.carsharing.entity.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionDriver {
    private String username = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/crm?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Don't connected!");
        }
        return con;
    }

    public void getProperties(){
        Properties properties = new Properties();
        InputStream inputStream = null;

        try{
            String file = "database.properties";
            inputStream = ConnectionDriver.class.getClassLoader().getResourceAsStream(file);
            if(inputStream == null){
                System.out.println("Не удалось найти " + file);
                return;
            }

            properties.load(inputStream);

            properties.getProperty("db.url");
            properties.getProperty("db.user");
            properties.getProperty("db.password");
            properties.getProperty("db.driver");
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Не удалось закрыть файл");
                }
            }
        }
    }
}

