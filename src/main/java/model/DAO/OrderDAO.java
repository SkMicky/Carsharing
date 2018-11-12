package model.DAO;

import model.entity.Order;

import java.util.List;

public interface OrderDAO extends BaseDAO<Order> {
    List<Order> getAll();
    List<Order> getByUserId(long userId);
    List<Order> getByCarId(long carId);
    void changeStatus(int status, long orderId);
    void removeByUserId(long userId);
}
