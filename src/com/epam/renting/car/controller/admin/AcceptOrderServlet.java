package com.epam.renting.car.controller.admin;

import com.epam.renting.car.DAO.OrdersDAO;
import com.epam.renting.car.model.OrderState;
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

@WebServlet(name = "AcceptOrderServlet", urlPatterns = "/orderAccepted")
public class AcceptOrderServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AcceptOrderServlet.class);

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

            if (OrdersDAO.getOrder(orderId).getStatus().equals(OrderState.UNDER_CONSIDERATION)) {
                OrdersDAO.changeOrderStatus(orderId, OrderState.WAITING_FOR_PAYMENT);
                logger.info("Admin with user_id " + session.getAttribute("user_id").toString() + " accepted order number " + orderId);
                response.sendRedirect("/orders");
            } else {
                out.println("Order state must be 'UNDER_CONSIDERATION', only in that case you can accept it");
                response.setHeader("Refresh", "3; URL=/adminCabinet");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}