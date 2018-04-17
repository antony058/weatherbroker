package ru.bellintegrator.weatherbroker.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import ru.bellintegrator.weatherbroker.messageservice.JmsMessageProducer;
import ru.bellintegrator.weatherbroker.weather.utils.RestTemplateManager;
import ru.bellintegrator.weatherbroker.weather.view.Weather;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("weather")
public class WeatherServlet implements HttpRequestHandler {

    @Autowired
    RestTemplateManager restTemplateManager;

    @Autowired
    JmsMessageProducer jmsMessageProducer;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String city = httpServletRequest.getParameter("city");
        Weather weather = restTemplateManager.sendRequest(city);
        System.out.println(weather);

        jmsMessageProducer.sendMessage("City is " + city);

        httpServletRequest.setAttribute("city", city);

        httpServletRequest.getRequestDispatcher("/result.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }
}
