package com.epam.renting.car.controller.customer;

import com.epam.renting.car.DAO.DAOReports;
import com.epam.renting.car.model.Report;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MyReports", urlPatterns = "/myReports")
public class MyReports extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null) {
            List<Report> reports = DAOReports.getReports(Integer.parseInt(session.getAttribute("user_id").toString()));

            if (reports != null) {
                request.setAttribute("reports", reports);
                request.getRequestDispatcher("WEB-INF/myReports.jsp").forward(request, response);
            } else {
                PrintWriter out = response.getWriter();

                out.println("You don't have active reports");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}