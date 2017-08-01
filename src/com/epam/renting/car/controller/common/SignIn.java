package com.epam.renting.car.controller.common;

import com.epam.renting.car.DAO.DAOUsers;
import com.epam.renting.car.model.User;
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

@WebServlet(name = "SignIn", urlPatterns = "/signIn")
public class SignIn extends HttpServlet {
    private Logger logger = LogManager.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        User user = DAOUsers.getUser(email, pass);

        if (user != null) {
            String role = user.getRole();
            HttpSession session = request.getSession(true);
            PrintWriter out = response.getWriter();

            session.setAttribute("Role", role);
            session.setAttribute("user_id", user.getId());

            logger.info("checking is logger working?");

            out.println("You have successfully signed in");

            if (role.equals("admin")) {
                response.setHeader("Refresh", "3; URL=/adminCabinet");
            } else {
                response.setHeader("Refresh", "3; URL=/myCabinet");
            }
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}