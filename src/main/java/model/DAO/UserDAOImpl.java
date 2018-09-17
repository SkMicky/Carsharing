package model.DAO;

import model.ConnectionPool;
import model.entity.User;
import model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class UserDAOImpl {

    private final String GET_ALL_USERS = "SELECT USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_BIRTHDAY, " +
            "USER_PHONENUMBER, USER_EMAIL, USER_IIN, USER_ADDRESS, USER_DRIVERLICENSE, USER_LOGIN, USER_PASSWORD, " +
            "USER_ROLE FROM USER";

    private final String GET_USER_BY_ID = "SELECT SELECT USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_BIRTHDAY, " +
            "USER_PHONENUMBER, USER_EMAIL, USER_IIN, USER_ADDRESS, USER_DRIVERLICENSE, USER_LOGIN, USER_PASSWORD, " +
            "USER_ROLE FROM USER WHERE USER_ID = ?";

    private final String GET_USER_BY_ROLE = "SELECT SELECT USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_BIRTHDAY, " +
            "USER_PHONENUMBER, USER_EMAIL, USER_IIN, USER_ADDRESS, USER_DRIVERLICENSE, USER_LOGIN, USER_PASSWORD, " +
            "USER_ROLE FROM USER WHERE USER_ROLE = ?";

    private final String GET_USER_BY_LOGIN = "SELECT SELECT USER_ID, USER_FIRSTNAME, USER_LASTNAME, USER_BIRTHDAY, " +
            "USER_PHONENUMBER, USER_EMAIL, USER_IIN, USER_ADDRESS, USER_DRIVERLICENSE, USER_LOGIN, USER_PASSWORD, " +
            "USER_ROLE FROM USER WHERE USER_LOGIN = ?";

    private final String ADD_USER = "INSERT INTO USER USER_FIRSTNAME = ?, USER_LASTNAME = ?, " +
            "USER_BIRTHDAY = ?, USER_PHONENUMBER = ?, USER_EMAIL = ?, USER_IIN = ?, USER_ADDRESS = ?, " +
            "USER_DRIVERLICENSE = ?, USER_LOGIN = ?, USER_PASSWORD = ?, USER_ROLE = ?";

    private final String UPDATE_USER = "UPDATE USER SET USER_ID = ?, USER_FIRSTNAME = ?, USER_LASTNAME = ?, " +
            "USER_BIRTHDAY = ?, USER_PHONENUMBER = ?, USER_EMAIL = ?, USER_IIN = ?, USER_ADDRESS = ?, " +
            "USER_DRIVERLICENSE = ?, USER_LOGIN = ?, USER_PASSWORD = ?, USER_ROLE = ? WHERE USER_ID = ?";

    private final String DELETE_USER = "DELETE FROM USER WHERE USER_ID = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection = connectionPool.getConnection();

    private User getEntityFromDB(ResultSet resultSet, User user){
        try{
            while(resultSet.next()){
                user.setID(resultSet.getInt("USER_ID"));
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

    private void saveEntityToDB(String sql, User user){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, user.getID());
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
        User user = new User();
        LinkedBlockingQueue<User> users = new LinkedBlockingQueue<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            getEntityFromDB(resultSet, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        users.add(user);
        return users;
    }

    public void getUserByID(int userID){
        User user = new User();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setInt(1, userID);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                getEntityFromDB(resultSet, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserByLogin(String userLogin){
        User user = new User();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1, userLogin);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                getEntityFromDB(resultSet, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserByRole(String userRole){
        User user = new User();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ROLE)){
            preparedStatement.setString(1, userRole);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                getEntityFromDB(resultSet, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateUser(User user){
        if(user.getID() == 0) {
            saveEntityToDB(ADD_USER, user);
        } else {
            saveEntityToDB(UPDATE_USER, user);
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
