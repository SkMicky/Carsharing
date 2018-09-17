package model.DAO;

import model.entity.OrderEntity;

interface OrderDAO extends AbstractDAO<OrderEntity> {
    OrderEntity getByUserId(int userId);
    OrderEntity getByCarId(int carId);
}
