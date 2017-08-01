package com.epam.renting.car.controller.admin;

import com.epam.renting.car.DAO.DAOReports;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Reports", urlPatterns = "/reports")
public class Reports extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("admin")) {
            request.setAttribute("reports", DAOReports.getReports());
            request.getRequestDispatcher("WEB-INF/reports.jsp").forward(request, response);
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}