package model.DAO;

import model.ConnectionPool;
import model.entity.Car;
import model.entity.Order;
import model.entity.User;

import java.sql.*;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderDAO {

    private final String GET_ALL_ORDERS = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID";

    private final String GET_ORDER_BY_ID = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE ORDER_ID = ?";

    private final String GET_ORDER_BY_USER_ID = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
            "ORDER_DETAIL.CAR_ID, ORDER_DETAIL.TARIF FROM ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON ORDER = ORDER_DETAIL.ORDER_ID " +
            "LEFT JOIN ORDER_DETAIL ON CAR = ORDER_DETAIL.CAR_ID " +
            "WHERE USER_ID = ?";

    private final String GET_ORDER_BY_CAR_ID = "SELECT * FROM ORDER AND SELECT ORDER_DETAIL.ORDER_ID, " +
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

    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();

    Order order = new Order();

    private Order collectingOrder(String sql){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                order.setOrderID(resultSet.getInt("ORDER_ID"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setUserID((User) resultSet.getObject("USER_ID"));
                order.setTotalCost(resultSet.getInt("TOTAL_COST"));
                order.setDiscount(resultSet.getInt("DISCOUNT"));
                order.setCarID((Car) resultSet.getObject("CAR_ID"));
                order.setTarif(resultSet.getInt("TARIF"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    private void creatingOrder(String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getOrderID());
            preparedStatement.setDate(2, (Date) order.getOrderDate());
            preparedStatement.setObject(3, order.getUserID());
            preparedStatement.setInt(4, order.getTotalCost());
            preparedStatement.setInt(5, order.getDiscount());
            preparedStatement.setObject(6, order.getCarID());
            preparedStatement.setInt(7, order.getTarif());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedBlockingQueue<Order> getAllOrders(){
        LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<>();

        return orders;
    }
}
