package ru.bellintegrator.weatherbroker.server.weather.dao;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.weather.model.Weather;

import java.util.List;

public interface WeatherDao {
    void save(Weather weather);

    Weather getWeatherByCity(String cityName) throws NotFoundException;
}
