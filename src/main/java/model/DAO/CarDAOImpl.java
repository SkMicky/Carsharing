package model.DAO;

import model.ConnectionPool;
import model.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class CarDAOImpl {

    private final String GET_ALL_CARS = "SELECT CAR.CAR_ID, CAR.CAR_NAME FROM CAR AND SELECT CAR_DETAIL.CAR_MODEL, " +
            "CAR_DETAIL.CAR_GOSNo," +
            "CAR_DETAIL.CAR_COLOR, CAR_DETAIL.CAR_COST FROM CAR_DETAIL" +
            "LEFT JOIN CAR_DETAIL ON CAR.CAR_ID = CAR_DETAIL.CAR_ID";

    private final String ADD_CAR = "INSERT INTO CAR CAR_NAME = ? " +
            "AND INSERT INTO CAR_DETAIL CAR_MODEL = ?, CAR_GOSNo = ?, CAR_COLOR = ?, CAR_COST = ?";

    private final String DELETE_CAR = "DELETE FROM CAR WHERE CAR_ID = ?";

    private final String GET_CAR_BY_ID = "SELECT CAR.CAR_ID, CAR.CAR_NAME FROM CAR WHERE CAR.CAR_ID =? " +
            "AND SELECT * FROM CAR_DETAIL LEFT JOIN CAR_DETAIL ON CAR.CAR_ID = CAR_DETAIL.CAR_ID " +
            "WHERE CAR_DETAIL.CAR_ID = ?";

    private final String UPDATE_CAR = "UPDATE CAR SET CAR_NAME = ?\n" +
            "UPDATE CAR_DETAIL SET CAR_MODEL =?, CAR_GOSNo = ?, CAR_COLOR = ?, CAR_COST = ? " +
            "WHERE CAR_ID = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection = connectionPool.getConnection();

    private Car getEntityFromDB(ResultSet resultSet, Car car){
        try{
            while(resultSet.next()){
                car.setID(resultSet.getInt("CAR_ID"));
                car.setName(resultSet.getString("CAR_NAME"));
                car.setModel(resultSet.getString("CAR_MODEL"));
                car.setGosNo(resultSet.getString("CAR_GOSNo"));
                car.setColor(resultSet.getString("CAR_COLOR"));
                car.setCost(resultSet.getInt("CAR_COST"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return car;
    }

    private void saveEntityToDB(String sql, Car car){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, car.getID());
            preparedStatement.setString(2, car.getName());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setString(4, car.getGosNo());
            preparedStatement.setString(5, car.getColor());
            preparedStatement.setDouble(6, car.getCost());
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    public LinkedBlockingQueue<Car> getAllCars(){
        Car car = new Car();
        LinkedBlockingQueue<Car> cars = new LinkedBlockingQueue<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CARS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            getEntityFromDB(resultSet, car);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cars.add(car);
        return cars;
    }

    public void getCarById(int carID) {
        Car car = new Car();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setInt(1, carID);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                getEntityFromDB(resultSet, car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateOrder(Car car){
        if(car.getID() == 0) {
            saveEntityToDB(ADD_CAR, car);
        } else {
            saveEntityToDB(UPDATE_CAR, car);
        }
    }

    public void deleteCar(int carID){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR)){
            preparedStatement.setInt(1, carID);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }
}
