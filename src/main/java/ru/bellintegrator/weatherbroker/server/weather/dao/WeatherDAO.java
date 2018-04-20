package ru.bellintegrator.weatherbroker.server.weather.dao;

import ru.bellintegrator.weatherbroker.server.weather.model.Weather;

import java.util.List;

public interface WeatherDAO {
    void save(Weather weather);

    List<Weather> getWeatherList();

    Weather getWeatherByCity(String cityName);
}
