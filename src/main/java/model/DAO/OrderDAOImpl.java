package model.DAO;

import model.ConnectionPool;
import model.entity.Order;
import model.entity.enumeration.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private final String GET_ALL_ORDERS = "SELECT ORDER_ID, ORDER_DATE, USER_ID, CAR_ID, ORDER_STATUS FROM `ORDER`";

    private final String GET_ORDER_BY_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, CAR_ID, ORDER_STATUS " +
            "FROM `ORDER` WHERE ORDER_ID = ?";

    private final String GET_ORDER_BY_USER_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, CAR_ID, ORDER_STATUS " +
            "FROM `ORDER` WHERE USER_ID = ?";

    private final String GET_ORDER_BY_CAR_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, CAR_ID, ORDER_STATUS " +
            "FROM `ORDER` WHERE CAR_ID = ?";

    private final String ADD_ORDER = "INSERT INTO `ORDER`(ORDER_DATE, USER_ID, CAR_ID, ORDER_STATUS) VALUES(?, ?, ?, ?)";

    private final String UPDATE_ORDER = "UPDATE `ORDER` SET ORDER_DATE = ?, USER_ID = ?, CAR_ID = ? WHERE ORDER_ID = ?";

    private final String UPDATE_STATUS = "UPDATE `ORDER` SET ORDER_STATUS =? WHERE ORDER_ID = ?";

    private final String DELETE_ORDER = "DELETE FROM `ORDER` WHERE ORDER_ID LIKE ?";

    private final String DELETE_ORDERS_BY_USER_ID = "DELETE FROM `ORDER` WHERE USER_ID = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class.getName());

    private Order getFromDB(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("ORDER_ID"));
        order.setDate(resultSet.getTimestamp("ORDER_DATE"));
        order.setUserId(resultSet.getLong("USER_ID"));
        order.setCarId(resultSet.getLong("CAR_ID"));
        order.setStatus(OrderStatus.getOrderStatus(resultSet.getInt("ORDER_STATUS")));
        return order;
    }

    private void saveToDB(String sql, Order order) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, order.getDate());
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setLong(3, order.getCarId());
            preparedStatement.setInt(4, order.getStatus().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                orders.add(getFromDB(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return orders;
    }

    @Override
    public Order getById(long orderId){
        Order order = new Order();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)){
            preparedStatement.setLong(1, orderId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    order = getFromDB(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> getByUserId(long userId) {
        List<Order> orders = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_ID)){
            preparedStatement.setLong(1, userId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    orders.add(getFromDB(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return orders;
    }

    @Override
    public List<Order> getByCarId(long carId) {
        List<Order> orders = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_CAR_ID)){
            preparedStatement.setLong(1, carId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    orders.add(getFromDB(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return orders;
    }

    @Override
    public void saveOrUpdate(Order order) {
        if(order.getId() == 0) {
            saveToDB(ADD_ORDER, order);
        } else {
            saveToDB(UPDATE_ORDER, order);
        }
    }

    @Override
    public void remove(long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public void removeByUserId(long userId) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDERS_BY_USER_ID)){
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public void changeStatus(int status, long orderId){
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS)){
            preparedStatement.setInt(1, status);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }
}