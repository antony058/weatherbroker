package ru.bellintegrator.weatherbroker.weather.dao;

import ru.bellintegrator.weatherbroker.weather.model.Weather;

import java.util.List;

public interface WeatherDAO {
    void save(Weather weather);

    List<Weather> getWeatherList();

    Weather getWeatherByCity(String cityName);
}
