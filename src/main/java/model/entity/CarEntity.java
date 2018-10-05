package model.entity;

import java.util.Objects;

public class CarEntity extends AbstractEntity {

    private String name;
    private String model;
    private String gosNo;
    private String color;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
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
                Objects.equals(model, carEntity.model) &&
                Objects.equals(gosNo, carEntity.gosNo) &&
                Objects.equals(color, carEntity.color) &&
                Objects.equals(status, carEntity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, gosNo, color, status);
    }
}
