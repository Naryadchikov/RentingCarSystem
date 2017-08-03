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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "MakeReport", urlPatterns = "/makeReport")
public class MakeReport extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MakeReport.class);

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

            if (DAOOrders.getOrder(orderId).getStatus().equals(OrderState.REGISTRATION_OF_RETURN)) {
                session.setAttribute("order_id", orderId);
                logger.info("Admin with user_id " + session.getAttribute("user_id").toString() + " is going to make report of order number " + orderId);
                request.getRequestDispatcher("WEB-INF/fillReport.jsp").forward(request, response);
            } else {
                out.println("Order state must be 'REGISTRATION_OF_RETURN', only in that case you can make report");
                response.setHeader("Refresh", "3; URL=/adminCabinet");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}