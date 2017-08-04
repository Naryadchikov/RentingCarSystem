package com.epam.renting.car.controller.admin;

import com.epam.renting.car.DAO.DAOCars;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "DeleteCarServlet", urlPatterns = "/deleteCar")
public class DeleteCarServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteCarServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = Integer.parseInt(request.getParameter("id"));

        DAOCars.deleteCar(id);
        logger.info("Admin with user_id " + session.getAttribute("user_id").toString() + " deleted car number " + id);
        response.sendRedirect("/cars");
    }
}