package com.epam.renting.car.controller.admin;

import com.epam.renting.car.DAO.DAOOrders;
import com.epam.renting.car.model.OrderState;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OrderAccepted", urlPatterns = "/orderAccepted")
public class OrderAccepted extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int orderId = Integer.parseInt(request.getParameter("id"));

        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("admin")) {
            PrintWriter out = response.getWriter();

            if (DAOOrders.getOrder(orderId).getStatus().equals(OrderState.UNDER_CONSIDERATION)) {
                DAOOrders.changeOrderStatus(orderId, OrderState.WAITING_FOR_PAYMENT);
                response.sendRedirect("/orders");
            } else {
                out.println("Order state must be 'UNDER_CONSIDERATION', only in that case you can accept it");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}