package com.epam.renting.car.DAO;

import static com.epam.renting.car.connection.ConnectionSQL.getConnection;

import com.epam.renting.car.model.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOReports {
    public static void addReport(int orderId, int userId, int fine, String comment) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO reports (order_id, user_id, fine, comment) VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, orderId);
            ps.setInt(2, userId);
            ps.setInt(3, fine);
            ps.setString(4, comment);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Report> getReports(int userId) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT id, order_id, fine, comment FROM reports WHERE user_id = ?")) {
            ps.setInt(1, userId);

            ResultSet resultSet = ps.executeQuery();
            ArrayList<Report> reports = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int orderId = resultSet.getInt(2);
                int fine = resultSet.getInt(3);
                String comment = resultSet.getString(4);

                reports.add(new Report(id, orderId, userId, fine, comment));
            }

            return reports;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}