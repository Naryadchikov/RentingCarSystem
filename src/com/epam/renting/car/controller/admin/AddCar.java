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

@WebServlet(name = "AddCar", urlPatterns = "/addCar")
public class AddCar extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddCar.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        int pricePerDay = Integer.parseInt(request.getParameter("pricePerDay"));

        DAOCars.addCar(brand, model, color, pricePerDay);
        logger.info("Admin with user_id " + session.getAttribute("user_id").toString() + " added new car with that parameters: " +
            brand + ", " + model + ", " + color + ", " + pricePerDay);
        response.sendRedirect("/cars");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}