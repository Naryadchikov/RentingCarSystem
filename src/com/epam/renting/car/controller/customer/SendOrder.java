package com.epam.renting.car.controller.customer;

import com.epam.renting.car.DAO.DAOOrders;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SendOrder", urlPatterns = "/sendOrder")
public class SendOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null) {
            String passport = request.getParameter("passport");
            String startDate = request.getParameter("startDate");
            String endingDate = request.getParameter("endingDate");

            DAOOrders.addOrder(Integer.parseInt(session.getAttribute("car_id").toString()),
                Integer.parseInt(session.getAttribute("user_id").toString()),passport, startDate, endingDate);

            PrintWriter out = response.getWriter();

            out.println("Your order sent to administrator, please stand by!");
            response.setHeader("Refresh", "3; URL=/myCabinet");
        } else {
            response.sendRedirect("/accessDenied");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}