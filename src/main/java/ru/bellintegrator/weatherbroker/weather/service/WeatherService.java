package ru.bellintegrator.weatherbroker.weather.service;

import ru.bellintegrator.weatherbroker.weather.view.Weather;

public interface WeatherService {
    Weather sendWeatherRequest(String city);
}
