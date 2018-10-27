package model.DAO;

import model.ConnectionPool;
import model.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    private final String GET_ALL_USERS = "SELECT USER.USER_ID, " +
            "USER.USER_LASTNAME, " +
            "USER.USER_FIRSTNAME, " +
            "USER.USER_BIRTHDAY, " +
            "USER.USER_PHONENUMBER, " +
            "USER.USER_EMAIL, " +
            "USER.USER_IIN, " +
            "USER.USER_ADDRESS, " +
            "USER.USER_DRIVERLICENSE, " +
            "USER.USER_LOGIN, " +
            "USER.USER_PASSWORD FROM USER";

    private final String GET_USER_BY_ID = "SELECT USER.USER_ID, " +
            "USER.USER_LASTNAME, " +
            "USER.USER_FIRSTNAME, " +
            "USER.USER_BIRTHDAY, " +
            "USER.USER_PHONENUMBER, " +
            "USER.USER_EMAIL, " +
            "USER.USER_IIN, " +
            "USER.USER_ADDRESS, " +
            "USER.USER_DRIVERLICENSE, " +
            "USER.USER_LOGIN, " +
            "USER.USER_PASSWORD FROM USER WHERE USER_ID = ?";

    private final String GET_USER_BY_ROLE = "SELECT USER.USER_ID, " +
            "USER.USER_LASTNAME, " +
            "USER.USER_FIRSTNAME, " +
            "USER.USER_BIRTHDAY, " +
            "USER.USER_PHONENUMBER, " +
            "USER.USER_EMAIL, " +
            "USER.USER_IIN, " +
            "USER.USER_ADDRESS, " +
            "USER.USER_DRIVERLICENSE, " +
            "USER.USER_LOGIN, " +
            "USER.USER_PASSWORD " +
            "FROM USER WHERE USER.USER_ROLE = ?";

    private final String GET_USER_BY_LOGIN = "SELECT USER.USER_ID, " +
            "USER.USER_LASTNAME, " +
            "USER.USER_FIRSTNAME, " +
            "USER.USER_BIRTHDAY, " +
            "USER.USER_PHONENUMBER, " +
            "USER.USER_EMAIL, " +
            "USER.USER_IIN, " +
            "USER.USER_ADDRESS, " +
            "USER.USER_DRIVERLICENSE, " +
            "USER.USER_LOGIN, " +
            "USER.USER_PASSWORD " +
            "FROM USER WHERE USER.USER_LOGIN = ?";

    private final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT USER.USER_LOGIN FROM USER WHERE USER.USER_LOGIN = ? " +
            "AND USER.USER_PASSWORD = ?";

    private final String ADD_USER = "INSERT INTO USER(USER_LASTNAME, USER_FIRSTNAME, USER_BIRTHDAY, " +
            "USER_PHONENUMBER, USER_EMAIL, USER_IIN, USER_ADDRESS, USER_DRIVERLICENSE, USER_LOGIN, " +
            "USER_PASSWORD, USER_ROLE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    private final String UPDATE_USER = "UPDATE USER SET USER_ID = ?, USER.USER_LASTNAME = ?, USER.USER_FIRSTNAME = ?, " +
            "USER.USER_BIRTHDAY = ?, USER.USER_PHONENUMBER = ?, USER.USER_EMAIL = ?, USER.USER_IIN = ?, " +
            "USER.USER_ADDRESS = ?, USER.USER_DRIVERLICENSE = ?, USER.USER_LOGIN = ?, USER.USER_PASSWORD = ?, " +
            "USER_ROLE = ? WHERE USER_ID LIKE ?";

    private final String DELETE_USER = "DELETE FROM `carsharing`.`USER`" +
            "WHERE USER_ID LIKE ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserEntity getFromDB(ResultSet resultSet){
        UserEntity user = new UserEntity();
        try{
            while(resultSet.next()){
                user.setId(resultSet.getLong("USER_ID"));
                user.setFirstName(resultSet.getString("USER_FIRSTNAME"));
                user.setLastName(resultSet.getString("USER_LASTNAME"));
                user.setBirthday(resultSet.getDate("USER_BIRTHDAY"));
                user.setPhoneNumber(resultSet.getString("USER_PHONENUMBER"));
                user.setEmail(resultSet.getString("USER_EMAIL"));
                user.setIIN(resultSet.getLong("USER_IIN"));
                user.setUserAddress(resultSet.getString("USER_ADDRESS"));
                user.setDriverLicense(resultSet.getLong("USER_DRIVERLICENSE"));
                user.setLogin(resultSet.getString("USER_LOGIN"));
                user.setPassword(resultSet.getString("USER_PASSWORD"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    private void saveToDB(String sql, UserEntity user){
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setLong(6, user.getIIN());
            preparedStatement.setString(7, user.getUserAddress());
            preparedStatement.setLong(8, user.getDriverLicense());
            preparedStatement.setString(9, user.getLogin());
            preparedStatement.setString(10, user.getPassword());
            preparedStatement.setInt(11, user.getRole());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> users = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()){
                users.add(getFromDB(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        System.out.println(users);
        return users;
    }

    @Override
    public UserEntity getById(Long id) {
        UserEntity user = new UserEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                user = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return user;
    }

    @Override
    public UserEntity getByRole(int role) {
        UserEntity user = new UserEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ROLE)){
            preparedStatement.setInt(1, role);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                user = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return user;
    }

    @Override
    public UserEntity getByLogin(String login) {
        UserEntity user = new UserEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                user = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return user;
    }

    public UserEntity searchInDataBase(String login,String password){
        UserEntity user = new UserEntity();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user.setLogin(resultSet.getString("USER_LOGIN"));
                }
            }
        } catch (SQLException se){
            se.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return user;
    }

    @Override
    public void saveOrUpdate(UserEntity userEntity) {
        if(userEntity.getId() == null) {
            saveToDB(ADD_USER, userEntity);
        } else {
            saveToDB(UPDATE_USER, userEntity);
        }
    }

    @Override
    public void remove(Long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.getAll();
    }
}
