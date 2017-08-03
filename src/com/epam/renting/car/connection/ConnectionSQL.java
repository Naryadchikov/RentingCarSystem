package com.epam.renting.car.connection;

import com.epam.renting.car.DAO.DAOUsers;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConnectionSQL {
    private static final Logger logger = LogManager.getLogger(DAOUsers.class);
    private static DataSource dataSource;

    static {
        try {
            Context init = new InitialContext();
            Context envContext = (Context) init.lookup("java:/comp/env");

            dataSource = (DataSource) envContext.lookup("jdbc/test");
        } catch (NamingException e) {
            logger.error("Resource described in context.xml wasn't found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}