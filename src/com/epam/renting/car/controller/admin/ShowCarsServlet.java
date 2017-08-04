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

@WebServlet(name = "ShowCarsServlet", urlPatterns = "/cars")
public class ShowCarsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ShowCarsServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("admin")) {
            request.setAttribute("cars", DAOCars.getCars("SELECT id, brand, model, color, pricePerDay FROM cars"));
            logger.info("Admin with user_id " + session.getAttribute("user_id").toString() + " opened cars list");
            request.getRequestDispatcher("WEB-INF/cars.jsp").forward(request, response);
        } else {
            response.sendRedirect("/accessDenied");
        }
    }
}