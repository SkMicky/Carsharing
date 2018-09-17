package model.entity;

public class CarEntity extends AbstractEntity {

    private String name;
    private String model;
    private String gosNo;
    private String color;
    private double cost;

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

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
