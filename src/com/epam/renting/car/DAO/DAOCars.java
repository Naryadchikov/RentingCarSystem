package com.epam.renting.car.DAO;

import static com.epam.renting.car.connection.ConnectionSQL.getConnection;

import com.epam.renting.car.model.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DAOCars {
    private static final Logger logger = LogManager.getLogger(DAOCars.class);

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
            logger.error("SQLException provided by this '" + SQLStatement + "' query in order to get cars list", e);
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
            logger.error("SQLException provided by adding new car into cars table", e);
        }
    }

    public static void deleteCar(int id) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM cars WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException provided by deleting car number " + id + " from cars table", e);
        }
    }

    public static void updatePrice(int id, int pricePerDay) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE cars SET pricePerDay = ? WHERE id = ?")) {
            ps.setInt(1, pricePerDay);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException provided by updating car's number " + id + " price per day in cars table", e);
        }
    }
}