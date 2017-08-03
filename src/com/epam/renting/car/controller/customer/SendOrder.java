package com.epam.renting.car.controller.customer;

import com.epam.renting.car.DAO.DAOOrders;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "SendOrder", urlPatterns = "/sendOrder")
public class SendOrder extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SendOrder.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null) {
            int carId = Integer.parseInt(session.getAttribute("car_id").toString());
            int userId = Integer.parseInt(session.getAttribute("user_id").toString());
            String passport = request.getParameter("passport");
            String startDate = request.getParameter("startDate");
            String endingDate = request.getParameter("endingDate");

            DAOOrders.addOrder(carId, userId, passport, startDate, endingDate);

            PrintWriter out = response.getWriter();

            logger.info("User number " + userId + " created new order to rent car number " + carId);
            out.println("Your order sent to administrator, please stand by!");
            response.setHeader("Refresh", "3; URL=/myCabinet");
        } else {
            response.sendRedirect("/accessDenied");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}