package model.DAO;

import model.entity.Car;

import java.util.List;

public interface CarDAO extends BaseDAO<Car> {
    List<Car> getAll();
    List<Car> getByColor(String color);
    Car getByGosNo(String gosNo);
    Car getFromOrder(long userId);
    List<Car> getByStatus(int status);
    void changeStatus(int status, long carId);
}
