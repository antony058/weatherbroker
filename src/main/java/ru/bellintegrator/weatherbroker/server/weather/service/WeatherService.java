package ru.bellintegrator.weatherbroker.server.weather.service;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

public interface WeatherService {
    void save(WeatherView weatherView) throws NotFoundException;
    WeatherView getCityWeather(String cityName) throws NotFoundException;
}
