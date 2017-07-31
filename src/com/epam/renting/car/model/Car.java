package com.epam.renting.car.model;

public class Car {
    private int id;
    private String brand;
    private String model;
    private String color;
    private int pricePerDay;

    public Car() {
    }

    public Car(int id, String brand, String model, String color, int pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.pricePerDay = pricePerDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", color='" + color + '\'' +
            ", pricePerDay=" + pricePerDay +
            '}';
    }
}