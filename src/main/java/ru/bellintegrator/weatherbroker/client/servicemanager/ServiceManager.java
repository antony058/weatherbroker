package ru.bellintegrator.weatherbroker.client.servicemanager;

import javassist.NotFoundException;

public interface ServiceManager {
    void pushCity(String cityName) throws NotFoundException;
}
