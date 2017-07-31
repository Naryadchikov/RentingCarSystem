package com.epam.renting.car.model;

import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String pass;
    private String role;

    public User() {
    }

    public User(int id, String email, String pass, String role) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId() &&
            Objects.equals(getEmail(), user.getEmail()) &&
            Objects.equals(getPass(), user.getPass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPass());
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", pass='" + pass + '\'' +
            ", role='" + role + '\'' +
            '}';
    }
}