package model.entity;

import java.util.Date;
import java.util.Objects;

public class OrderEntity extends AbstractEntity {

    private Date date;
    private long userId;
    private long carId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "Order {" +
                "date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                ", carId='" + carId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return  Objects.equals(date, that.date) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(carId, that.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, userId, carId);
    }
}
