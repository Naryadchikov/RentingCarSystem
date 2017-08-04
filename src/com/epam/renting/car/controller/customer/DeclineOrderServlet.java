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

@WebServlet(name = "DeclineOrderServlet", urlPatterns = "/declineOrder")
public class DeclineOrderServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeclineOrderServlet.class);

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
            PrintWriter out = response.getWriter();
            OrderState status = DAOOrders.getOrder(orderId).getStatus();

            if (status.equals(OrderState.UNDER_CONSIDERATION) || status.equals(OrderState.WAITING_FOR_PAYMENT)) {
                DAOOrders.deleteOrder(orderId);
                logger.info("User number " + session.getAttribute("user_id").toString() + " declined his/her order number " + orderId);
                out.println("Order is declined!");
            } else {
                out.println("Your order state must be 'UNDER_CONSIDERATION' or 'WAITING_FOR_PAYMENT', only in that case you can decline an order");
            }
            response.setHeader("Refresh", "3; URL=/myCabinet");
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}