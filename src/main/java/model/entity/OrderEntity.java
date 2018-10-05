package model.entity;

import java.util.Date;
import java.util.Objects;

public class OrderEntity extends AbstractEntity {

    private Date date;
    private UserEntity user;
    private int totalCost;
    private CarEntity car;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Order {" +
                "date='" + date + '\'' +
                ", user='" + user + '\'' +
                ", totalCost='" + totalCost + '\'' +
                ", car='" + car + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return totalCost == that.totalCost &&
                Objects.equals(date, that.date) &&
                Objects.equals(user, that.user) &&
                Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, user, totalCost, car);
    }
}
