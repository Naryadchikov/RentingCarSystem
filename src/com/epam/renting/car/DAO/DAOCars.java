package com.epam.renting.car.DAO;

import static com.epam.renting.car.connection.ConnectionSQL.getConnection;

import com.epam.renting.car.model.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCars {
    public static List<Car> getCars(String SQLStatement) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(SQLStatement)) {
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String brand = resultSet.getString(2);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                int pricePerDay = resultSet.getInt(5);

                cars.add(new Car(id, brand, model, color, pricePerDay));
            }

            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void addCar(String brand, String model, String color, int pricePerDay) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO cars (brand, model, color, pricePerDay) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setString(3, color);
            ps.setInt(4, pricePerDay);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCar(int id) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM cars WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePrice(int id, int pricePerDay) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE cars SET pricePerDay = ? WHERE id = ?")) {
            ps.setInt(1, pricePerDay);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}