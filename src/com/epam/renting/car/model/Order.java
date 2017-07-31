package com.epam.renting.car.model;

public class Order {
    private int id;
    private int carId;
    private int userId;
    private String passport;
    private String startDate;
    private String endingDate;
    private OrderState status;

    public Order() {
    }

    public Order(int id, int carId, int userId, String passport, String startDate, String endingDate, OrderState status) {
        this.id = id;
        this.carId = carId;
        this.userId = userId;
        this.passport = passport;
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "orderId=" + id +
            ", carId=" + carId +
            ", userId=" + userId +
            ", passport=" + passport +
            ", startDate=" + startDate +
            ", endingDate=" + endingDate;
    }
}