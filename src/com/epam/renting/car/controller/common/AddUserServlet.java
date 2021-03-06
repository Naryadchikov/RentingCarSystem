package com.epam.renting.car.controller.common;

import com.epam.renting.car.DAO.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "AddUserServlet", urlPatterns = "/addUser")
public class AddUserServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        UsersDAO.addUser(email, pass);

        logger.info("New user with email " + email + " was added");
        PrintWriter out = response.getWriter();

        out.println("You have successfully registered");
        response.setHeader("Refresh", "3; URL=/login");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
    }
}