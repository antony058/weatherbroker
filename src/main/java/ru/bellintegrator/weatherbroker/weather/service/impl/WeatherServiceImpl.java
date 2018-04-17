package ru.bellintegrator.weatherbroker.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherbroker.weather.service.WeatherService;
import ru.bellintegrator.weatherbroker.weather.utils.RestTemplateManager;
import ru.bellintegrator.weatherbroker.weather.view.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final RestTemplateManager restTemplateManager;
    //private final MessageService messageService;

    @Autowired
    public WeatherServiceImpl(RestTemplateManager restTemplateManager) {
        this.restTemplateManager = restTemplateManager;
        //this.messageService = messageService;
    }

    @Override
    public Weather sendWeatherRequest(String city) {
        Weather weather = restTemplateManager.sendRequest(city);

        //messageService.sendMessage(weather);

        return weather;
    }
}
