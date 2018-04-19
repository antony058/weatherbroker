package ru.bellintegrator.weatherbroker.weather.service;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

public interface WeatherService {
    void save(WeatherView weatherView) throws NotFoundException;
    WeatherView getCityWeather(String cityName);
}
