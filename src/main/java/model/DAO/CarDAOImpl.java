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
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR" +
            "LEFT JOIN CAR ON STATUS.STATUS_ID = CAR.CAR_STATUS";

    private final String ADD_CAR = "INSERT INTO CAR CAR_NAME = ?, " +
            "CAR_GOSNo = ?, CAR_COLOR = ?, CAR_STATUS = ?";

    private final String DELETE_CAR = "DELETE FROM CAR WHERE CAR_ID LIKE ?";

    private final String GET_CAR_BY_ID = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR" +
            "LEFT JOIN CAR ON STATUS.STATUS_ID = CAR.CAR_STATUS" +
            "WHERE CAR.CAR_ID LIKE ?";

    private final String GET_CAR_BY_COLOR = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR" +
            "LEFT JOIN CAR ON STATUS.STATUS_ID = CAR.CAR_STATUS" +
            "WHERE CAR.CAR_COLOR LIKE ?";

    private final String GET_CAR_BY_GOSNo = "SELECT CAR.CAR_ID, CAR.CAR_NAME, CAR.CAR_GOSNo, " +
            "CAR.CAR_COLOR, CAR.CAR_STATUS FROM CAR" +
            "LEFT JOIN CAR ON STATUS.STATUS_ID = CAR.CAR_STATUS" +
            "WHERE CAR.CAR_GOSNo LIKE ?";

    private final String UPDATE_CAR = "UPDATE CAR SET CAR_NAME = ?\n" +
            "CAR_GOSNo = ?, CAR_COLOR = ?, CAR_STATUS" +
            "WHERE CAR_ID LIKE ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();


    private CarEntity getFromDB(ResultSet resultSet){
        CarEntity car = new CarEntity();
        try{
            while(resultSet.next()){
                car.setId(resultSet.getLong("CAR_ID"));
                car.setName(resultSet.getString("CAR_NAME"));
                car.setModel(resultSet.getString("CAR_MODEL"));
                car.setGosNo(resultSet.getString("CAR_GOSNo"));
                car.setColor(resultSet.getString("CAR_COLOR"));
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
            preparedStatement.setLong(1, car.getId());
            preparedStatement.setString(2, car.getName());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setString(4, car.getGosNo());
            preparedStatement.setString(5, car.getColor());
            preparedStatement.executeUpdate();
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
        return car;
    }

    @Override
    public CarEntity getCarByColor(String color) {
        CarEntity car = new CarEntity();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setString(1, color);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                car = getFromDB(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.putBackConnection(connection);
        return car;
    }

    @Override
    public CarEntity getCarByGosNo(String gosNo) {
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
        return car;
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
}
