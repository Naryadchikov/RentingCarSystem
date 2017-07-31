package com.epam.renting.car.controller.customer;

import com.epam.renting.car.DAO.DAOOrders;
import com.epam.renting.car.model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MyCurrentOrders", urlPatterns = "/myCurrentOrders")
public class MyCurrentOrders extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null) {
            List<Order> orders = DAOOrders.getOrders(Integer.parseInt(session.getAttribute("user_id").toString()));

            if (orders != null) {
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("WEB-INF/myCurrentOrders.jsp").forward(request, response);
            } else {
                PrintWriter out = response.getWriter();

                out.println("You don't have active orders");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}