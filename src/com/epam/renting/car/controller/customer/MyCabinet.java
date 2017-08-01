package com.epam.renting.car.controller.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MyCabinet", urlPatterns = "/myCabinet")
public class MyCabinet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null) {
            request.getRequestDispatcher("WEB-INF/myCabinet.jsp").forward(request, response);
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}