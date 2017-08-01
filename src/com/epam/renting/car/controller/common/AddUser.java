package com.epam.renting.car.controller.common;

import com.epam.renting.car.DAO.DAOUsers;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddUser", urlPatterns = "/addUser")
public class AddUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        DAOUsers.addUser(email, pass);

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