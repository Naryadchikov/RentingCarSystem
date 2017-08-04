package com.epam.renting.car.controller.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "ShowMyCabinetServlet", urlPatterns = "/myCabinet")
public class ShowMyCabinetServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ShowMyCabinetServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null) {
            logger.info("User number " + session.getAttribute("user_id").toString() + " opened his/her cabinet");
            request.getRequestDispatcher("WEB-INF/myCabinet.jsp").forward(request, response);
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}