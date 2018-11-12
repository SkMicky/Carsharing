package model.entity;

import model.entity.enumeration.CarStatus;

import java.util.Objects;

public class Car extends BaseEntity {

    private String name;
    private String gosNo;
    private String color;
    private CarStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGosNo() {
        return gosNo;
    }

    public void setGosNo(String gosNo) {
        this.gosNo = gosNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", gosNo='" + gosNo + '\'' +
                ", color='" + color + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) &&
                Objects.equals(gosNo, car.gosNo) &&
                Objects.equals(color, car.color) &&
                Objects.equals(status, car.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gosNo, color, status);
    }
}
