package model.DAO;

import model.ConnectionPool;
import model.entity.AbstractEntity;
import model.entity.OrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private final String GET_ALL_ORDERS = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST,  " +
            "FROM ORDER AND SELECT " +
            "ORDER_DETAIL.CAR_ID, FROM ORDER_ID " +
            "LEFT JOIN ORDER ON USER.USER_ID = ORDER.USER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR.CAR_ID = ORDER_DETAIL.CAR_ID";

    private final String GET_ORDER_BY_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST,  " +
            "FROM ORDER AND SELECT " +
            "ORDER_DETAIL.CAR_ID, FROM ORDER_ID " +
            "LEFT JOIN ORDER ON USER.USER_ID = ORDER.USER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR.CAR_ID = ORDER_DETAIL.CAR_ID" +
            "WHERE ORDER_ID LIKE ?";

    private final String GET_ORDER_BY_USER_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST,  " +
            "FROM ORDER AND SELECT " +
            "ORDER_DETAIL.CAR_ID, FROM ORDER_ID " +
            "LEFT JOIN ORDER ON USER.USER_ID = ORDER.USER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR.CAR_ID = ORDER_DETAIL.CAR_ID" +
            "WHERE USER_ID LIKE ?";

    private final String GET_ORDER_BY_CAR_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST,  " +
            "FROM ORDER AND SELECT " +
            "ORDER_DETAIL.CAR_ID, FROM ORDER_ID " +
            "LEFT JOIN ORDER ON USER.USER_ID = ORDER.USER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR.CAR_ID = ORDER_DETAIL.CAR_ID" +
            "WHERE USER_ID LIKE ?";

    private final String ADD_ORDER = "INSERT INTO ORDER ORDER_DATE = ?, USER_ID = ?, TOTAL_COST = ?" +
            "INSERT INTO ORDER_DETAIL CAR_ID = ?";

    private final String UPDATE_ORDER = "UPDATE ORDER SET ORDER_ID = ?, ORDER_DATE = ?, USER_ID = ?, TOTAL_COST = ?" +
            "UPDATE ORDER_DETAIL SET ORDER_DETAIL_ID = ?, ORDER_ID = ?, CAR_ID = ?" +
            "WHERE ORDER_ID = ?";

    private final String DELETE_ORDER = "DELETE FROM ORDER WHERE ORDER_ID LIKE ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private OrderEntity getFromDB(ResultSet resultSet) {
        OrderEntity order = new OrderEntity();
        try{
            while(resultSet.next()){
                order.setId(resultSet.getLong("ORDER_ID"));
                order.setDate(resultSet.getDate("ORDER_DATE"));
                order.getUser().setId(resultSet.getLong("USER_ID"));
                order.setTotalCost(resultSet.getInt("TOTAL_COST"));
                order.getCar().setId(resultSet.getLong("CAR_ID"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    private void saveToDB(String sql) {
        OrderEntity order = new OrderEntity();
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setDate(2, (Date) order.getDate());
            preparedStatement.setLong(3, order.getUser().getId());
            preparedStatement.setInt(4, order.getTotalCost());
            preparedStatement.setLong(6, order.getCar().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    @Override
    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()){
                orders.add(getFromDB(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return orders;
    }

    @Override
    public OrderEntity getById(Long orderId){
        OrderEntity order = new OrderEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)){
            preparedStatement.setLong(1, orderId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                order = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return order;
    }

    @Override
    public OrderEntity getByUserId(Long userId) {
        OrderEntity order = new OrderEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_ID)){
            preparedStatement.setLong(1, userId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                order = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return order;
    }

    @Override
    public OrderEntity getByCarId(Long carId) {
        OrderEntity order = new OrderEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_CAR_ID)){
            preparedStatement.setLong(1, carId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                order = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return order;
    }

    @Override
    public void saveOrUpdate(OrderEntity orderEntity) {
        if(orderEntity.getId() == 0) {
            saveToDB(ADD_ORDER);
        } else {
            saveToDB(UPDATE_ORDER);
        }
    }

    @Override
    public void remove(Long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }
}
