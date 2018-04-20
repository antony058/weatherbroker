package ru.bellintegrator.weatherbroker.client.messageservice.service;

import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

public interface MessageService {
    void sendMessageToTopic(WeatherView view);
}
