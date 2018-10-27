package model.DAO;

import model.ConnectionPool;
import model.entity.CarEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private final String GET_ALL_CARS = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR";

    private final String GET_CAR_BY_ID = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR WHERE CAR_ID = ?";

    private final String GET_CAR_BY_COLOR = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR WHERE CAR_COLOR = ?";

    private final String GET_CAR_BY_GOSNo = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR WHERE CAR_GOSNo = ?";

    private final String GET_CAR_BY_STATUS = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR WHERE CAR_STATUS = ?";

    private final String CHANGE_CAR_STATUS = "UPDATE CAR SET CAR_STATUS = ? WHERE CAR_ID = ?";

    private final String ADD_CAR = "INSERT INTO CAR(CAR_NAME, CAR_GOSNo, CAR_COLOR, CAR_STATUS) VALUES(?,?,?,?)";

    private final String UPDATE_CAR = "UPDATE CAR SET CAR_NAME = ?, CAR_GOSNo = ?, CAR_COLOR = ?, " +
            "CAR_STATUS = ? WHERE CAR_ID = ?";

    private final String DELETE_CAR = "DELETE FROM CAR WHERE CAR_ID LIKE ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();


    private CarEntity getFromDB(ResultSet resultSet){
        CarEntity car = new CarEntity();
        try{
            while(resultSet.next()){
                car.setId(resultSet.getLong("CAR_ID"));
                car.setName(resultSet.getString("CAR_NAME"));
                car.setGosNo(resultSet.getString("CAR_GOSNo"));
                car.setColor(resultSet.getString("CAR_COLOR"));
                car.setStatus(resultSet.getInt("CAR_STATUS"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }

    private void saveToDB(String sql){
        CarEntity car = new CarEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, car.getName());
            preparedStatement.setString(2, car.getGosNo());
            preparedStatement.setString(3, car.getColor());
            preparedStatement.setInt(4, car.getStatus());
            preparedStatement.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    @Override
    public List<CarEntity> getAll() {
        List<CarEntity> cars = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CARS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                cars.add(getFromDB(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        System.out.println(cars);
        return cars;
    }

    @Override
    public CarEntity getById(Long id) {
        CarEntity car = new CarEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                car = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        System.out.println(car);
        return car;
    }

    @Override
    public List<CarEntity> getByColor(String color) {
        List<CarEntity> cars = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setString(1, color);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cars.add(getFromDB(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        System.out.println(cars);
        return cars;
    }

    @Override
    public CarEntity getByGosNo(String gosNo) {
        CarEntity car = new CarEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setString(1, gosNo);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                car = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        System.out.println(car);
        return car;
    }

    @Override
    public List<CarEntity> getByStatus(int status){
        CarEntity car;
        List<CarEntity> cars = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_STATUS)){
            preparedStatement.setInt(1, status);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    car = getFromDB(resultSet);
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        System.out.println(cars);
        return cars;
    }

    @Override
    public void changeStatus(long carId, int status){
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_CAR_STATUS)){
            preparedStatement.setLong(1, carId);
            preparedStatement.setInt(2, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    @Override
    public void saveOrUpdate(CarEntity carEntity) {
        if(carEntity.getId() == 0) {
            saveToDB(ADD_CAR);
        } else {
            saveToDB(UPDATE_CAR);
        }
    }
    @Override
    public void remove(Long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
    }

    public static void main(String[] args) {
        CarDAOImpl carDAO = new CarDAOImpl();
        carDAO.getAll();
    }
}
