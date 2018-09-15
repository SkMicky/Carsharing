package model.DAO;

import model.ConnectionPool;
import model.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderDAOImpl {

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

    private Order collectingOrder(String sql, Order order){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                order.setOrderID(resultSet.getInt("ORDER_ID"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.getUserID().setUserID(resultSet.getInt("USER_ID"));
                order.setTotalCost(resultSet.getInt("TOTAL_COST"));
                order.setDiscount(resultSet.getInt("DISCOUNT"));
                order.getCarID().setCarID(resultSet.getInt("CAR_ID"));
                order.setTarif(resultSet.getInt("TARIF"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return order;
    }

    private void creatingOrder(String sql, Order order) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getOrderID());
            preparedStatement.setDate(2, (Date) order.getOrderDate());
            preparedStatement.setObject(3, order.getUserID().getUserID());
            preparedStatement.setInt(4, order.getTotalCost());
            preparedStatement.setInt(5, order.getDiscount());
            preparedStatement.setObject(6, order.getCarID().getCarID());
            preparedStatement.setInt(7, order.getTarif());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    public LinkedBlockingQueue<Order> getAllOrders(){
        Order order = new Order();
        LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<>();
        orders.add(collectingOrder(GET_ALL_ORDERS, order));
        return orders;
    }

    public void getOrderByID(int orderID){
        Order order = new Order();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement.setInt(1, orderID);
            collectingOrder(GET_ORDER_BY_ID, order);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getOrderByUserID(int userID){
        Order order = new Order();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement.setInt(1, userID);
            collectingOrder(GET_ORDER_BY_USER_ID, order);
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getOrderByCarID(int carID){
        Order order = new Order();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement.setInt(1, carID);
            collectingOrder(GET_ORDER_BY_CAR_ID, order);
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createOrder(Order order){
        if(order == null) {
            creatingOrder(ADD_ORDER, order);
        } else {
            System.out.println("ERROR ADDING ORDER!!!\nAlready have such order!");
        }
    }

    public void updateOrder(Order order){
        if(order != null) {
            creatingOrder(UPDATE_ORDER, order);
        } else {
            System.out.println("ERROR UPDATING ORDER!!!\nThere is nothing to update!");
        }
    }

    public void removeOrder(int orderID){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)){
            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }
}
