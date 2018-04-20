package ru.bellintegrator.weatherbroker.client.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import ru.bellintegrator.weatherbroker.client.servicemanager.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("weather")
public class WeatherServlet implements HttpRequestHandler {

    @Autowired
    private ServiceManager serviceManager;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String city = httpServletRequest.getParameter("city");

        serviceManager.pushCity(city);

        httpServletRequest.setAttribute("city", city);

        httpServletRequest.getRequestDispatcher("/result.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }
}
