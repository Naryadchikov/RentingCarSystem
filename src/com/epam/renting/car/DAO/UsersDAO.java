package com.epam.renting.car.DAO;

import static com.epam.renting.car.connection.ConnectionSQL.getConnection;

import com.epam.renting.car.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UsersDAO {
    private static final Logger logger = LogManager.getLogger(UsersDAO.class);

    public static User getUser(String email, String pass) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * from users WHERE email = ? and pass = ?")) {
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet resultSet = ps.executeQuery();
            int dbId = 0;
            String dbEmail = null;
            String dbPass = null;
            String dbRole = null;

            while (resultSet.next()) {
                dbId = resultSet.getInt(1);
                dbEmail = resultSet.getString(2);
                dbPass = resultSet.getString(3);
                dbRole = resultSet.getString(4);
            }
            if (dbEmail != null && dbPass != null && dbEmail.equals(email) && dbPass.equals(pass)) {
                return new User(dbId, dbEmail, dbPass, dbRole);
            }
        } catch (SQLException e) {
            logger.error("SQLException provided by 'SELECT' query in order to get user from users table by email: " + email + " and password", e);
        }

        return null;
    }

    public static void addUser(String email, String pass) {
        try (Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO users (email, pass) VALUES (?, ?)")) {
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException provided by adding new user into users table", e);
        }
    }
}