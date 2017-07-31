package com.epam.renting.car.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionSQL {
    private static DataSource dataSource;

    static {
        try {
            Context init = new InitialContext();
            Context envContext = (Context) init.lookup("java:/comp/env");

            dataSource = (DataSource) envContext.lookup("jdbc/test");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1771");
        return dataSource.getConnection();
    }
}