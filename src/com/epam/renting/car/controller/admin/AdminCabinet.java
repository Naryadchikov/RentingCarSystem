package com.epam.renting.car.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminCabinet", urlPatterns = "/adminCabinet")
public class AdminCabinet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("admin")) {
            request.getRequestDispatcher("WEB-INF/adminCabinet.jsp").forward(request, response);
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}