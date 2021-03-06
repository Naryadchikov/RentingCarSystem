package com.epam.renting.car.controller.admin;

import com.epam.renting.car.DAO.CarsDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "UpdateCarServlet", urlPatterns = "/updateCar")
public class UpdateCarServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UpdateCarServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = Integer.parseInt(request.getParameter("id"));
        int pricePerDay = Integer.parseInt(request.getParameter("pricePerDay"));

        CarsDAO.updatePrice(id, pricePerDay);
        logger.info("Admin with user_id " + session.getAttribute("user_id").toString() +
            " update price of car number " + id + "to " + pricePerDay + "$/day");
        response.sendRedirect("/cars");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}