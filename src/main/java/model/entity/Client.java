package main.java.model.entity;

import java.util.Date;

public class Client extends User {

    private int clientID;
    private Date birthday;
    private int iIN;
    private int driverLicense;
    private String addess;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getiIN() {
        return iIN;
    }

    public void setiIN(int iIN) {
        this.iIN = iIN;
    }

    public int getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getAddess() {
        return addess;
    }

    public void setAddess(String addess) {
        this.addess = addess;
    }
}
