package model.entity;

import java.util.Objects;

public class CarEntity extends AbstractEntity {

    private String name;
    private String gosNo;
    private String color;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(name, carEntity.name) &&
                Objects.equals(gosNo, carEntity.gosNo) &&
                Objects.equals(color, carEntity.color) &&
                Objects.equals(status, carEntity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gosNo, color, status);
    }
}
