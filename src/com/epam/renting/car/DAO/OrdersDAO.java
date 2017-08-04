package com.epam.renting.car.DAO;

import static com.epam.renting.car.connection.ConnectionSQL.getConnection;

import com.epam.renting.car.model.Order;
import com.epam.renting.car.model.OrderState;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class OrdersDAO {
    private static final Logger logger = LogManager.getLogger(OrdersDAO.class);

    public static Order getOrder(int id) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT car_id, user_id, passport, start_date, ending_date, status FROM orders WHERE id = ?")) {
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            resultSet.next();

            int carId = resultSet.getInt(1);
            int userId = resultSet.getInt(2);
            String passport = resultSet.getString(3);
            String startDate = resultSet.getString(4);
            String endingDate = resultSet.getString(5);
            OrderState status = OrderState.valueOf(resultSet.getString(6));

            return new Order(id, carId, userId, passport, startDate, endingDate, status);
        } catch (SQLException e) {
            logger.error("SQLException provided by 'SELECT' query in order to get order from orders table by id " + id, e);
        }

        return null;
    }

    public static List<Order> getOrders() {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT id, car_id, user_id, passport, start_date, ending_date, status FROM orders")) {
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int carId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                String passport = resultSet.getString(4);
                String startDate = resultSet.getString(5);
                String endingDate = resultSet.getString(6);
                OrderState status = OrderState.valueOf(resultSet.getString(7));

                orders.add(new Order(id, carId, userId, passport, startDate, endingDate, status));
            }

            return orders;
        } catch (SQLException e) {
            logger.error("SQLException provided by 'SELECT' query in order to get orders list", e);
        }

        return null;
    }

    public static List<Order> getOrders(int userId) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT id, car_id, passport, start_date, ending_date, status FROM orders WHERE user_id = ?")) {
            ps.setInt(1, userId);

            ResultSet resultSet = ps.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int carId = resultSet.getInt(2);
                String passport = resultSet.getString(3);
                String startDate = resultSet.getString(4);
                String endingDate = resultSet.getString(5);
                OrderState status = OrderState.valueOf(resultSet.getString(6));

                orders.add(new Order(id, carId, userId, passport, startDate, endingDate, status));
            }

            return orders;
        } catch (SQLException e) {
            logger.error("SQLException provided by 'SELECT' query in order to get orders list of user number " + userId, e);
        }

        return null;
    }

    public static void addOrder(int carId, int userId, String passport, String startDate, String endingDate) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO orders (car_id, user_id, passport, start_date, ending_date) VALUES (?, ?, ?, ?, ?)")) {
            ps.setInt(1, carId);
            ps.setInt(2, userId);
            ps.setString(3, passport);
            ps.setString(4, startDate);
            ps.setString(5, endingDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException provided by adding new order into orders table", e);
        }
    }

    public static void deleteOrder(int id) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM orders WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException provided by deleting order number " + id + " from orders table", e);
        }
    }

    public static void changeOrderStatus(int id, OrderState newStatus) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE orders SET status = ? WHERE id = ?")) {
            ps.setString(1, newStatus.toString());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException provided by updating order's number " + id + " status in orders table", e);
        }
    }
}