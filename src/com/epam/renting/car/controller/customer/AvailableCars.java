package com.epam.renting.car.controller.customer;

import com.epam.renting.car.DAO.DAOCars;
import com.epam.renting.car.model.Car;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AvailableCars", urlPatterns = "/availableCars")
public class AvailableCars extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null) {
            List<Car> cars = DAOCars.getCars("SELECT id, brand, model, color, pricePerDay FROM cars WHERE cars.id NOT IN (SELECT orders.car_id FROM orders)");

            if (cars != null) {
                request.setAttribute("cars", cars);
                request.getRequestDispatcher("WEB-INF/availableCars.jsp").forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("No cars available at the moment");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}