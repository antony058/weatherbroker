package ru.bellintegrator.weatherbroker.messageservice.service;

import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

public interface MessageService {
    void sendMessageToTopic(WeatherView view);
}
