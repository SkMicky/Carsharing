package model;

import java.util.ResourceBundle;

class ConnectionSource {

    private static final ResourceBundle resource = ResourceBundle.getBundle("/property/database/database");

    static String getPoolVolume() {
        return resource.getString("db.connectionPoolVolume");
    }

    static String getDriver(){ return resource.getString("db.driver");}

    static String getUrl() {
        return resource.getString("db.url");
    }

    static String getUsername() {
        return resource.getString("db.user");
    }

    static String getPassword() {
        return resource.getString("db.password");
    }
}
