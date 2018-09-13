package model.entity;

public class Car {

    private int carID;
    private String carName;
    private String carModel;
    private String carGosNo;
    private String carColor;
    private double carCost;

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

    public String getCarGosNo() {
        return carGosNo;
    }

    public void setCarGosNo(String carGosNo) {
        this.carGosNo = carGosNo;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public double getCarCost() {
        return carCost;
    }

    public void setCost(int carCost) {
        this.carCost = carCost;
    }
}
