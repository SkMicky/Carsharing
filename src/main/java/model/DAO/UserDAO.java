package model.DAO;

import model.ConnectionPool;
import model.entity.User;
import model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class UserDAO {

    private final String GET_ALL_USERS = "SELECT * FROM USER";

    private final String GET_USER_BY_ID = "SELECT * FROM USER WHERE USER_ID = ?";

    private final String GET_USER_BY_ROLE = "SELECT * FROM USER WHERE USER_ROLE = ?";

    private final String GET_USER_BY_LOGIN = "SELECT * FROM USER WHERE USER_LOGIN = ?";

    private final String ADD_USER = "INSERT INTO USER USER_FIRSTNAME = ?, USER_LASTNAME = ?, " +
            "USER_BIRTHDAY = ?, USER_PHONENUMBER = ?, USER_EMAIL = ?, USER_IIN = ?, USER_ADDRESS = ?, " +
            "USER_DRIVERLICENSE = ?, USER_LOGIN = ?, USER_PASSWORD = ?, USER_ROLE = ?";

    private final String UPDATE_USER = "UPDATE USER SET USER_ID = ?, USER_FIRSTNAME = ?, USER_LASTNAME = ?, " +
            "USER_BIRTHDAY = ?, USER_PHONENUMBER = ?, USER_EMAIL = ?, USER_IIN = ?, USER_ADDRESS = ?, " +
            "USER_DRIVERLICENSE = ?, USER_LOGIN = ?, USER_PASSWORD = ?, USER_ROLE = ? WHERE USER_ID = ?";

    private final String DELETE_USER = "DELETE FROM USER WHERE USER_ID = ?";

    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();

    User user = new User();

    private User grabbingUser(String sql){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                user.setUserID(resultSet.getInt("USER_ID"));
                user.setFirstName(resultSet.getString("USER_FIRSTNAME"));
                user.setLastName(resultSet.getString("USER_LASTNAME"));
                user.setBirthday(resultSet.getDate("USER_BIRTHDAY"));
                user.setPhoneNumber(resultSet.getString("USER_PHONENUMBER"));
                user.setEmail(resultSet.getString("USER_EMAIL"));
                user.setIIN(resultSet.getInt("USER_IIN"));
                user.setUserAddress(resultSet.getString("USER_ADDRESS"));
                user.setDriverLicense(resultSet.getInt("USER_DRIVERLICENSE"));
                user.setLogin(resultSet.getString("USER_LOGIN"));
                user.setPassword(resultSet.getString("USER_PASSWORD"));
                user.setRole((Role)resultSet.getObject("USER_ROLE"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return user;
    }

    private void creatingUser(String sql){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setDate(4, user.getBirthday());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setInt(7, user.getIIN());
            preparedStatement.setString(8, user.getUserAddress());
            preparedStatement.setInt(9, user.getDriverLicense());
            preparedStatement.setString(10, user.getLogin());
            preparedStatement.setString(11, user.getPassword());
            preparedStatement.setString(12, String.valueOf(user.getRole()));
        } catch (SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    public LinkedBlockingQueue<User> getAllUsers(){
        LinkedBlockingQueue<User> users = new LinkedBlockingQueue<>();
        grabbingUser(GET_ALL_USERS);
        users.add(user);
        return users;
    }

    public void getUserByID(int userID){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement.setInt(1, userID);
            grabbingUser(GET_USER_BY_ID);
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getUserByLogin(String userLogin){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement.setString(1, userLogin);
            grabbingUser(GET_USER_BY_LOGIN);
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getUserByRole(String userRole){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement.setString(1, userRole);
            grabbingUser(GET_USER_BY_ROLE);
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addUser(){
        if (user == null){
            creatingUser(ADD_USER);
        } else {
            System.out.println("ADDING USER ERROR!");
        }
    }

    public void updateUser(){
        if (user != null){
            creatingUser(UPDATE_USER);
        } else {
            System.out.println("Updating USER ERROR!");
        }
    }

    public void deleteUser(int userID){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }
}
