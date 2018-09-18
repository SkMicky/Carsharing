package model.DAO;

import model.entity.OrderEntity;

interface OrderDAO extends AbstractDAO<OrderEntity> {
    OrderEntity getByUserId(Long userId);
    OrderEntity getByCarId(Long carId);
}
