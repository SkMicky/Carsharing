package model.DAO;

import model.ConnectionPool;
import model.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class CarDAO {

    private final String GET_ALL_CARS = "SELECT * FROM CAR AND SELECT CAR_DETAIL.CAR_MODEL, " +
            "CAR_DETAIL.CAR_GOSNo," +
            "CAR_DETAIL.CAR_COLOR, CAR_DETAIL.CAR_COST FROM CAR_DETAIL" +
            "LEFT JOIN CAR_DETAIL ON CAR.CAR_ID = CAR_DETAIL.CAR_ID";

    private final String ADD_CAR = "INSERT INTO CAR(CAR_NAME) VALUES(?) " +
            "AND INSERT INTO CAR_DETAIL(CAR_MODEL, CAR_GOSNo, CAR_COLOR, CAR_COST) VALUES(?, ?, ?, ?)";

    private final String DELETE_CAR = "DELETE FROM CAR WHERE CAR_ID = ?";

    private final String GET_CAR_BY_ID = "SELECT * FROM CAR WHERE CAR.CAR_ID =? " +
            "AND SELECT * FROM CAR_DETAIL LEFT JOIN CAR_DETAIL ON CAR.CAR_ID = CAR_DETAIL.CAR_ID " +
            "WHERE CAR_DETAIL.CAR_ID = ?";

    private final String UPDATE_CAR = "UPDATE CAR SET CAR_NAME = ?\n" +
            "UPDATE CAR_DETAIL SET CAR_MODEL =?, CAR_GOSNo = ?, CAR_COLOR = ?, CAR_COST = ? " +
            "WHERE CAR_ID = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection = connectionPool.getConnection();
    private Car car = new Car();

    private Car grabbingCar(String sql){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                car.setCarID(resultSet.getInt("CAR_ID"));
                car.setCarName(resultSet.getString("CAR_NAME"));
                car.setCarModel(resultSet.getString("CAR_MODEL"));
                car.setCarGosNo(resultSet.getString("CAR_GOSNo"));
                car.setCarColor(resultSet.getString("CAR_COLOR"));
                car.setCost(resultSet.getInt("CAR_COST"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return car;
    }

    private void constructingCar(String sql){
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, car.getCarID());
            preparedStatement.setString(2, car.getCarName());
            preparedStatement.setString(3, car.getCarModel());
            preparedStatement.setString(4, car.getCarGosNo());
            preparedStatement.setString(5, car.getCarColor());
            preparedStatement.setDouble(6, car.getCarCost());
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    public LinkedBlockingQueue<Car> getAllCars(){
        LinkedBlockingQueue<Car> cars = new LinkedBlockingQueue<>();
        grabbingCar(GET_ALL_CARS);
        cars.add(car);
        return cars;
    }

    public void getCarById(int carID) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement.setInt(1, carID);
            grabbingCar(GET_CAR_BY_ID);
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCar(){
        if(car != null) {
            constructingCar(UPDATE_CAR);
        } else {
            System.out.println("ERROR UPDATING CAR!!!\nThere is nothing to update ");
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

    public void createCar(){
        if(car == null) {
            constructingCar(ADD_CAR);
        } else {
            System.out.println("ERROR ADDING CAR!!!\nAlready have such a car!");
        }
    }
}
