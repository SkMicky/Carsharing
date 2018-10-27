package model.DAO;

import model.entity.CarEntity;

import java.util.List;

public interface CarDAO extends AbstractDAO<CarEntity> {
    List<CarEntity> getByColor(String color);
    CarEntity getByGosNo(String gosNo);
    List<CarEntity> getByStatus(int status);
    void changeStatus(long carId, int status);
}
