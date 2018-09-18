package model.DAO;

import model.entity.CarEntity;

public interface CarDAO extends AbstractDAO<CarEntity> {
    CarEntity getCarByColor(String color);
    CarEntity getCarByGosNo(String gosNo);
}
