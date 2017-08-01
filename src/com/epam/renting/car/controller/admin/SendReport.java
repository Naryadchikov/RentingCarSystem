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

@WebServlet(name = "SendReport", urlPatterns = "/sendReport")
public class SendReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("admin")) {
            int orderId = Integer.parseInt(session.getAttribute("order_id").toString());
            int fine = Integer.parseInt(request.getParameter("fine"));
            String comment = request.getParameter("comment");
            Order order = DAOOrders.getOrder(orderId);

            DAOReports.addReport(orderId, order.getUserId(), fine, comment + "; Your order information: " + order.toString());
            DAOOrders.deleteOrder(orderId);

            PrintWriter out = response.getWriter();

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