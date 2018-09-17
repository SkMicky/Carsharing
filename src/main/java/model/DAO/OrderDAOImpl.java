package model.DAO;

import model.ConnectionPool;
import model.entity.AbstractEntity;
import model.entity.OrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderDAOImpl implements OrderDAO {

    private final String GET_ALL_ORDERS = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST, DISCOUNT " +
            "FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID";

    private final String GET_ORDER_BY_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST, DISCOUNT " +
            "FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE ORDER_ID = ?";

    private final String GET_ORDER_BY_USER_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST, DISCOUNT " +
            "FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE USER_ID = ?";

    private final String GET_ORDER_BY_CAR_ID = "SELECT ORDER_ID, ORDER_DATE, USER_ID, TOTAL_COST, DISCOUNT " +
            "FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE USER_ID = ?";

    private final String ADD_ORDER = "INSERT INTO ORDER ORDER_DATE = ?, USER_ID = ?, TOTAL_COST = ?, DISCOUNT = ?" +
            "INSERT INTO ORDER_DETAIL ORDER_ID = ?, CAR_ID = ?, TARIF = ?";

    private final String UPDATE_ORDER = "UPDATE ORDER SET ORDER_ID = ?, ORDER_DATE = ?, USER_ID = ?, TOTAL_COST = ?, DISCOUNT = ? " +
            "UPDATE ORDER_DETAIL SET ORDER_DETAIL_ID = ?, ORDER_ID = ?, CAR_ID = ?, TARIF = ? " +
            "WHERE ORDER_ID = ?";

    private final String DELETE_ORDER = "DELETE FROM ORDER WHERE ORDER_ID = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection = connectionPool.getConnection();

    @Override
    public OrderEntity getFromDB(ResultSet resultSet) {
        OrderEntity order = new OrderEntity();
        try{
            while(resultSet.next()){
                order.setId(resultSet.getInt("ORDER_ID"));
                order.setDate(resultSet.getDate("ORDER_DATE"));
                order.getUser().setId(resultSet.getInt("USER_ID"));
                order.setTotalCost(resultSet.getInt("TOTAL_COST"));
                order.setDiscount(resultSet.getInt("DISCOUNT"));
                order.getCar().setId(resultSet.getInt("CAR_ID"));
                order.setTarif(resultSet.getInt("TARIF"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return order;
    }

    @Override
    public void saveToDB(String sql) {
        OrderEntity order = new OrderEntity();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setDate(2, (Date) order.getDate());
            preparedStatement.setInt(3, order.getUser().getId());
            preparedStatement.setInt(4, order.getTotalCost());
            preparedStatement.setInt(5, order.getDiscount());
            preparedStatement.setInt(6, order.getCar().getId());
            preparedStatement.setInt(7, order.getTarif());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    @Override
    public LinkedBlockingQueue<OrderEntity> getAll() {
        OrderEntity order = new OrderEntity();
        LinkedBlockingQueue<OrderEntity> orders = new LinkedBlockingQueue<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            order = getFromDB(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orders.add(order);
        return orders;
    }

    @Override
    public OrderEntity getById(int orderId){
        OrderEntity order = new OrderEntity();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)){
            preparedStatement.setInt(1, orderId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                order = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public OrderEntity getByUserId(int userId) {
        OrderEntity order = new OrderEntity();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_ID)){
            preparedStatement.setInt(1, userId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                order = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public OrderEntity getByCarId(int carId) {
        OrderEntity order = new OrderEntity();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_CAR_ID)){
            preparedStatement.setInt(1, carId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                order = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void addOrUpdate(OrderEntity orderEntity) {
        if(orderEntity.getId() == 0) {
            saveToDB(ADD_ORDER);
        } else {
            saveToDB(UPDATE_ORDER);
        }
    }

    @Override
    public void remove(int id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }
}
