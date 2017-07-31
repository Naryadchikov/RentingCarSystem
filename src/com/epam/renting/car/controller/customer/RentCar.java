package com.epam.renting.car.controller.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RentCar", urlPatterns = "/rentCar")
public class RentCar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int carId = Integer.parseInt(request.getParameter("id"));

        if (session != null && session.getAttribute("Role") != null) {
            session.setAttribute("car_id", carId);
            request.getRequestDispatcher("WEB-INF/fillOrder.jsp").forward(request, response);
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}