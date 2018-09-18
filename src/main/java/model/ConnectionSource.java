package model;

import java.util.ResourceBundle;

class ConnectionSource {

    private ResourceBundle resource = ResourceBundle.getBundle("resources/properties/database/database");
    private final String poolVolume = resource.getString("db.connectionPoolVolume");
    private final String driver = resource.getString("db.driver");
    private final String url = resource.getString("db.url");
    private final String username = resource.getString("db.user");
    private final String password = resource.getString("db.password");

    String getPoolVolume() {
        return poolVolume;
    }

    String getDriver() {
        return driver;
    }

    String getUrl() {
        return url;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
