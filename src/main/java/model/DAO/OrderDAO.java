package model.DAO;

import model.entity.OrderEntity;

import java.util.List;

interface OrderDAO extends AbstractDAO<OrderEntity> {
    List<OrderEntity> getAll();
    List<OrderEntity> getByUserId(long userId);
    List<OrderEntity> getByCarId(long carId);
    void changeStatus(int status, long orderId);
    void removeByUserId(long userId);
}
