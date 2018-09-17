package model.entity;

import java.sql.Date;

public class UserEntity extends AbstractEntity {

    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private int IIN;
    private String userAddress;
    private int driverLicense;
    private String login;
    private String password;
    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIIN() {
        return IIN;
    }

    public void setIIN(int IIN) {
        this.IIN = IIN;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
