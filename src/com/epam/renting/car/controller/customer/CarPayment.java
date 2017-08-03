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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "CarPayment", urlPatterns = "/carPayment")
public class CarPayment extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CarPayment.class);

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
           if (DAOOrders.getOrder(orderId).getStatus().equals(OrderState.WAITING_FOR_PAYMENT)) {
               DAOOrders.changeOrderStatus(orderId, OrderState.CAR_IS_USED);
               logger.info("User number " + session.getAttribute("user_id").toString() + " payed for his/her order number " + orderId);
               response.sendRedirect("/myCurrentOrders");
            } else {
               PrintWriter out = response.getWriter();

               out.println("Your order state must be 'WAITING_FOR_PAYMENT', only in that case you can pay an order");
               response.setHeader("Refresh", "3; URL=/myCabinet");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}