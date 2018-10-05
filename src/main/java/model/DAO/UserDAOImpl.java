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

    private final String GET_ALL_USERS = "SELECT USER.USER_ID,\n" +
            "USER.USER_LASTNAME,\n" +
            "USER.USER_FIRSTNAME,\n" +
            "USER.USER_BIRTHDAY,\n" +
            "USER.USER_PHONENUMBER,\n" +
            "USER.USER_EMAIL,\n" +
            "USER.USER_IIN,\n" +
            "USER.USER_ADDRESS,\n" +
            "USER.USER_DRIVERLICENSE,\n" +
            "USER.USER_LOGIN,\n" +
            "USER.USER_PASSWORD,\n" +
            "FROM USER" +
            "LEFT JOIN USER ON ROLE.ROLE_ID = USER.USER_ROLE";

    private final String GET_USER_BY_ID = "SELECT USER.USER_ID,\n" +
            "USER.USER_LASTNAME,\n" +
            "USER.USER_FIRSTNAME,\n" +
            "USER.USER_BIRTHDAY,\n" +
            "USER.USER_PHONENUMBER,\n" +
            "USER.USER_EMAIL,\n" +
            "USER.USER_IIN,\n" +
            "USER.USER_ADDRESS,\n" +
            "USER.USER_DRIVERLICENSE,\n" +
            "USER.USER_LOGIN,\n" +
            "USER.USER_PASSWORD,\n" +
            "FROM USER" +
            "LEFT JOIN USER ON ROLE.ROLE_ID = USER.USER_ROLE" +
            "WHERE USER_ID LIKE ?";

    private final String GET_USER_BY_ROLE = "SELECT USER.USER_ID,\n" +
            "USER.USER_LASTNAME,\n" +
            "USER.USER_FIRSTNAME,\n" +
            "USER.USER_BIRTHDAY,\n" +
            "USER.USER_PHONENUMBER,\n" +
            "USER.USER_EMAIL,\n" +
            "USER.USER_IIN,\n" +
            "USER.USER_ADDRESS,\n" +
            "USER.USER_DRIVERLICENSE,\n" +
            "USER.USER_LOGIN,\n" +
            "USER.USER_PASSWORD,\n" +
            "FROM USER" +
            "LEFT JOIN USER ON ROLE.ROLE_ID = USER.USER_ROLE" +
            "WHERE USER_ROLE LIKE ?";

    private final String GET_USER_BY_LOGIN = "SELECT USER.USER_ID,\n" +
            "USER.USER_LASTNAME,\n" +
            "USER.USER_FIRSTNAME,\n" +
            "USER.USER_BIRTHDAY,\n" +
            "USER.USER_PHONENUMBER,\n" +
            "USER.USER_EMAIL,\n" +
            "USER.USER_IIN,\n" +
            "USER.USER_ADDRESS,\n" +
            "USER.USER_DRIVERLICENSE,\n" +
            "USER.USER_LOGIN,\n" +
            "USER.USER_PASSWORD,\n" +
            "FROM USER" +
            "LEFT JOIN USER ON ROLE.ROLE_ID = USER.USER_ROLE" +
            "WHERE USER_LOGIN LIKE ?";

    private final String ADD_USER = "INSERT INTO USER USER.USER_LASTNAME = ?,\n" +
            "USER.USER_FIRSTNAME = ?,\n" +
            "USER.USER_BIRTHDAY = ?,\n" +
            "USER.USER_PHONENUMBER = ?,\n" +
            "USER.USER_EMAIL = ?,\n" +
            "USER.USER_IIN = ?,\n" +
            "USER.USER_ADDRESS = ?,\n" +
            "USER.USER_DRIVERLICENSE = ?,\n" +
            "USER.USER_LOGIN = ?,\n" +
            "USER.USER_PASSWORD = ?, USER_ROLE = ?";

    private final String UPDATE_USER = "UPDATE USER SET USER_ID = ?, USER.USER_LASTNAME = ?,\n" +
            "USER.USER_FIRSTNAME = ?,\n" +
            "USER.USER_BIRTHDAY = ?,\n" +
            "USER.USER_PHONENUMBER = ?,\n" +
            "USER.USER_EMAIL = ?,\n" +
            "USER.USER_IIN = ?,\n" +
            "USER.USER_ADDRESS = ?,\n" +
            "USER.USER_DRIVERLICENSE = ?,\n" +
            "USER.USER_LOGIN = ?,\n" +
            "USER.USER_PASSWORD = ?, USER_ROLE = ?" +
            "WHERE USER_ID LIKE ?";

    private final String DELETE_USER = "DELETE FROM USER WHERE USER_ID LIKE ?";

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
                user.setIIN(resultSet.getInt("USER_IIN"));
                user.setUserAddress(resultSet.getString("USER_ADDRESS"));
                user.setDriverLicense(resultSet.getInt("USER_DRIVERLICENSE"));
                user.setLogin(resultSet.getString("USER_LOGIN"));
                user.setPassword(resultSet.getString("USER_PASSWORD"));
                user.setRole(resultSet.getString("USER_ROLE"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    private void saveToDB(String sql){
        UserEntity user = new UserEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, user.getId());
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
            preparedStatement.setString(12, user.getRole());
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
    public UserEntity getByRole(String role) {
        UserEntity user = new UserEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ROLE)){
            preparedStatement.setString(1, role);
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

    @Override
    public void saveOrUpdate(UserEntity userEntity) {
        if(userEntity.getId() == 0) {
            saveToDB(ADD_USER);
        } else {
            saveToDB(UPDATE_USER);
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
}
