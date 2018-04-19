package ru.bellintegrator.weatherbroker.servicemanager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherbroker.messageservice.service.MessageService;
import ru.bellintegrator.weatherbroker.requestservice.RestTemplateManager;
import ru.bellintegrator.weatherbroker.servicemanager.ServiceManager;
import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

@Service
public class ServiceManagerImpl implements ServiceManager {

    private final RestTemplateManager restTemplateManager;
    private final MessageService messageService;

    @Autowired
    public ServiceManagerImpl(RestTemplateManager restTemplateManager, MessageService messageService) {
        this.restTemplateManager = restTemplateManager;
        this.messageService = messageService;
    }

    @Override
    public void pushCity(String cityName) {
        WeatherView weatherView = restTemplateManager.sendRequest(cityName);
        messageService.sendMessageToTopic(weatherView);
    }
}
