package com.epam.renting.car.controller.customer;

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

@WebServlet(name = "CarReturning", urlPatterns = "/carReturning")
public class CarReturning extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int orderId = Integer.parseInt(request.getParameter("id"));

        if (session != null && session.getAttribute("Role") != null) {
            if (DAOOrders.getOrder(orderId).getStatus().equals(OrderState.CAR_IS_USED)) {
                DAOOrders.changeOrderStatus(orderId, OrderState.REGISTRATION_OF_RETURN);
                response.sendRedirect("/myCurrentOrders");
            } else {
                PrintWriter out = response.getWriter();

                out.println("Your order state must be 'CAR_IS_USED', only in that case you can return it");
                response.setHeader("Refresh", "3; URL=/myCabinet");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}