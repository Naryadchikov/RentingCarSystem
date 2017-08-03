package com.epam.renting.car.controller.admin;

import com.epam.renting.car.DAO.DAOOrders;
import com.epam.renting.car.DAO.DAOReports;
import com.epam.renting.car.model.Order;
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

@WebServlet(name = "SendDeclinedReport", urlPatterns = "/sendDeclinedReport")
public class SendDeclinedReport extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SendDeclinedReport.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("admin")) {
            int orderId = Integer.parseInt(session.getAttribute("order_id").toString());
            String comment = request.getParameter("comment");
            Order order = DAOOrders.getOrder(orderId);

            DAOReports.addReport(orderId, order.getUserId(), 0, comment + "; Your order information: " + order.toString());
            DAOOrders.deleteOrder(orderId);

            PrintWriter out = response.getWriter();

            logger.info("Admin with user_id " + session.getAttribute("user_id").toString() + " declined order number " + orderId + " and created report");
            out.println("You have successfully created report");
            response.setHeader("Refresh", "3; URL=/adminCabinet");
        } else {
            response.sendRedirect("/accessDenied");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}