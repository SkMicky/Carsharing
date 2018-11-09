package model.DAO;

import model.entity.CarEntity;

import java.util.List;

public interface CarDAO extends AbstractDAO<CarEntity> {
    List<CarEntity> getAll();
    List<CarEntity> getByColor(String color);
    CarEntity getByGosNo(String gosNo);
    CarEntity getFromOrder(long userId);
    List<CarEntity> getByStatus(int status);
    void changeStatus(int status, long carId);
}
