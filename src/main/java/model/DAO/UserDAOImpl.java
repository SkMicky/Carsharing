package model.DAO;

import model.ConnectionPool;
import model.entity.User;
import model.entity.enumeration.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

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
            "USER.USER_PASSWORD, USER.USER_ROLE FROM USER WHERE USER_ID = ?";

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
            "USER.USER_PASSWORD, USER.USER_ROLE " +
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
            "USER.USER_PASSWORD, USER.USER_ROLE " +
            "FROM USER WHERE USER.USER_LOGIN = ?";

    private final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT USER.USER_LOGIN FROM USER WHERE USER.USER_LOGIN = ? " +
            "AND USER.USER_PASSWORD = ?";

    private final String ADD_USER = "INSERT INTO USER(USER_LASTNAME, USER_FIRSTNAME, USER_BIRTHDAY, " +
            "USER_PHONENUMBER, USER_EMAIL, USER_IIN, USER_ADDRESS, USER_DRIVERLICENSE, USER_LOGIN, " +
            "USER_PASSWORD, USER_ROLE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    private final String UPDATE_USER = "UPDATE USER SET USER_LASTNAME = ?, USER_FIRSTNAME = ?, " +
            "USER_BIRTHDAY = ?, USER_PHONENUMBER = ?, USER_EMAIL = ?, USER_IIN = ?, " +
            "USER_ADDRESS = ?, USER_DRIVERLICENSE = ?, USER_LOGIN = ? WHERE USER_ID = ?";

    private final String DELETE_USER = "DELETE FROM `carsharing`.`USER`" +
            "WHERE USER_ID LIKE ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class.getName());

    private User getFromDB(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("USER_ID"));
        user.setFirstName(resultSet.getString("USER_FIRSTNAME"));
        user.setLastName(resultSet.getString("USER_LASTNAME"));
        user.setBirthday(resultSet.getDate("USER_BIRTHDAY"));
        user.setPhoneNumber(resultSet.getString("USER_PHONENUMBER"));
        user.setEmail(resultSet.getString("USER_EMAIL"));
        user.setIin(resultSet.getString("USER_IIN"));
        user.setUserAddress(resultSet.getString("USER_ADDRESS"));
        user.setDriverLicense(resultSet.getString("USER_DRIVERLICENSE"));
        user.setLogin(resultSet.getString("USER_LOGIN"));
        user.setPassword(resultSet.getString("USER_PASSWORD"));
        user.setRole(UserRole.getUserRole(resultSet.getInt("USER_ROLE")));
        return user;
    }

    private void saveToDB(String sql, User user){
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getIin());
            preparedStatement.setString(7, user.getUserAddress());
            preparedStatement.setString(8, user.getDriverLicense());
            preparedStatement.setString(9, user.getLogin());
            preparedStatement.setString(10, user.getPassword());
            preparedStatement.setInt(11, user.getRole().getId());
            preparedStatement.execute();
        } catch (SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public User getById(long id) {
        User user = new User();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    user = getFromDB(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> getByRole(int role) {
        List<User> users = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ROLE)){
            preparedStatement.setInt(1, role);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    users.add(getFromDB(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return users;
    }

    @Override
    public User getByLogin(String login) {
        User user = new User();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    user = getFromDB(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return user;
    }

    public User searchInDataBase(String login, String password){
        User user = new User();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user.setLogin(resultSet.getString("USER_LOGIN"));
                }
            }
        } catch (SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return user;
    }

    @Override
    public void saveOrUpdate(User user) {
        if(user.getId() == 0) {
            saveToDB(ADD_USER, user);
        } else {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)){
                preparedStatement.setString(1, user.getLastName());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.setDate(3, user.getBirthday());
                preparedStatement.setString(4, user.getPhoneNumber());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getIin());
                preparedStatement.setString(7, user.getUserAddress());
                preparedStatement.setString(8, user.getDriverLicense());
                preparedStatement.setString(9, user.getLogin());
                preparedStatement.setLong(10, user.getId());
                preparedStatement.execute();
            } catch (SQLException e){
                LOGGER.error(e);
            } finally{
                connectionPool.putBackConnection(connection);
            }
        }
    }

    @Override
    public void remove(long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }
}
