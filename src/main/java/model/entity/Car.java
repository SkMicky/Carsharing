package main.java.model.entity;

public class Car {

    private int carID;
    private String carName;
    private String carModel;
    private String carGosNumber;
    private String carColor;
    private int cost;

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarGosNumber() {
        return carGosNumber;
    }

    public void setCarGosNumber(String carGosNumber) {
        this.carGosNumber = carGosNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}