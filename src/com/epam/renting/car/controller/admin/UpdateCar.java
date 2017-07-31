package com.epam.renting.car.controller.admin;

import com.epam.renting.car.DAO.DAOCars;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateCar", urlPatterns = "/updateCar")
public class UpdateCar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int pricePerDay = Integer.parseInt(request.getParameter("pricePerDay"));

        DAOCars.updatePrice(id, pricePerDay);
        response.sendRedirect("/cars");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}