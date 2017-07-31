package com.epam.renting.car.controller.admin;

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

@WebServlet(name = "Orders", urlPatterns = "/orders")
public class Orders extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("admin")) {
            List<Order> orders = DAOOrders.getOrders();

            if (orders != null) {
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("WEB-INF/orders.jsp").forward(request, response);
            } else {
                PrintWriter out = response.getWriter();

                out.println("No active orders right now");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}