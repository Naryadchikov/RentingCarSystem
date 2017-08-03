package com.epam.renting.car.controller.common;

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

@WebServlet(name = "SignOut", urlPatterns = "/signOut")
public class SignOut extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SignOut.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();

        if (session != null && session.getAttribute("Role") != null) {
            logger.info("User number " + session.getAttribute("user_id").toString() + " signed out");
            out.println("You have successfully signed out");
            response.setHeader("Refresh", "3; URL=/login");
            session.invalidate();
        } else {
            out.println("You need to sign in first");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}