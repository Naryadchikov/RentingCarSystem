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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet(name = "ShowAvailableCarsServlet", urlPatterns = "/availableCars")
public class ShowAvailableCarsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ShowAvailableCarsServlet.class);

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

            logger.info("User number " + session.getAttribute("user_id").toString() + " opened 'Available Cars' list");

            if (cars != null) {
                request.setAttribute("cars", cars);
                request.getRequestDispatcher("WEB-INF/availableCars.jsp").forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("No cars available at the moment");
                response.setHeader("Refresh", "3; URL=/myCabinet");
            }
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}