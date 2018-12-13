package model.DAO;

import model.ConnectionPool;
import model.entity.BaseEntity;
import model.entity.Car;
import model.entity.enumeration.CarStatus;
import model.entity.enumeration.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private final String GET_FROM_ORDER_BY_USER= "SELECT `ORDER`.CAR_ID FROM `ORDER` " +
            "LEFT JOIN CAR ON `ORDER`.CAR_ID = CAR.CAR_ID WHERE `ORDER`.USER_ID = ?";

    private final String CHANGE_CAR_STATUS = "UPDATE CAR SET CAR_STATUS = ? WHERE CAR_ID = ?";

    private final String ADD_CAR = "INSERT INTO CAR(CAR_NAME, CAR_GOSNo, CAR_COLOR, CAR_STATUS) VALUES(?,?,?,?)";

    private final String UPDATE_CAR = "UPDATE CAR SET CAR_NAME = ?, CAR_GOSNo = ?, CAR_COLOR = ?, " +
            "CAR_STATUS = ? WHERE CAR_ID = ?";

    private final String DELETE_CAR = "DELETE FROM CAR WHERE CAR_ID = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(CarDAOImpl.class.getName());


    private Car getFromDB(ResultSet resultSet) throws SQLException{
        Car car = new Car();
        car.setId(resultSet.getLong("CAR_ID"));
        car.setName(resultSet.getString("CAR_NAME"));
        car.setGosNo(resultSet.getString("CAR_GOSNo"));
        car.setColor(Color.getColors(resultSet.getString("CAR_COLOR")));
        car.setStatus(CarStatus.getCarStatus(resultSet.getInt("CAR_STATUS")));
        return car;
    }

    private void saveToDB(String sql, Car car){
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, car.getName());
            preparedStatement.setString(2, car.getGosNo());
            preparedStatement.setString(3, car.getColor().getColorName());
            preparedStatement.setInt(4, car.getStatus().getId());
            preparedStatement.execute();
        } catch(SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CARS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                cars.add(getFromDB(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return cars;
    }

    @Override
    public Car getById(long id) {
        Car car = new Car();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    car = getFromDB(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return car;
    }

    @Override
    public List<Car> getByColor(String color) {
        List<Car> cars = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setString(1, color);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    cars.add(getFromDB(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return cars;
    }

    @Override
    public Car getByGosNo(String gosNo) {
        Car car = new Car();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)){
            preparedStatement.setString(1, gosNo);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    car = getFromDB(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return car;
    }

    @Override
    public List<Car> getByStatus(int status){
        List<Car> cars = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_STATUS)){
            preparedStatement.setInt(1, status);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    cars.add(getFromDB(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return cars;
    }

    @Override
    public Car getFromOrder(long userId){
        Connection connection = connectionPool.getConnection();
        Car car = new Car();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_FROM_ORDER_BY_USER)){
            preparedStatement.setLong(1, userId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    car.setId(resultSet.getLong("CAR_ID"));
                }
                car = getById(car.getId());
            }
        } catch (SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
        return car;
    }

    @Override
    public void changeStatus(int status, long carId){
        Connection connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_CAR_STATUS)){
            preparedStatement.setInt(1, status);
            preparedStatement.setLong(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public void saveOrUpdate(Car car) {
        if(car.getId() == 0) {
            saveToDB(ADD_CAR, car);
        } else {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR)){
                preparedStatement.setString(1, car.getName());
                preparedStatement.setString(2, car.getGosNo());
                preparedStatement.setString(3, car.getColor().getColorName());
                preparedStatement.setInt(4, car.getStatus().getId());
                preparedStatement.setLong(5, car.getId());
                preparedStatement.execute();
            } catch(SQLException e){
                LOGGER.error(e);
            } finally{
                connectionPool.putBackConnection(connection);
            }
        }
    }
    @Override
    public void remove(long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            LOGGER.error(e);
        } finally{
            connectionPool.putBackConnection(connection);
        }
    }
}
