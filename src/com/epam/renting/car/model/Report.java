package com.epam.renting.car.model;

public class Report {
    private int id;
    private int orderId;
    private int userId;
    private int fine;
    private String comment;

    public Report() {
    }

    public Report(int id, int orderId, int userId, int fine, String comment) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.fine = fine;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", userId=" + userId +
            ", fine=" + fine +
            ", comment='" + comment + '\'' +
            '}';
    }
}