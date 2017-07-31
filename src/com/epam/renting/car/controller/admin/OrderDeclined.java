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

@WebServlet(name = "OrderDeclined", urlPatterns = "/orderDeclined")
public class OrderDeclined extends HttpServlet {

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
                session.setAttribute("order_id", orderId);
                request.getRequestDispatcher("WEB-INF/fillDeclinedReport.jsp").forward(request, response);
            } else {
                out.println("Order state must be 'UNDER_CONSIDERATION', only in that case you can declined it");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}