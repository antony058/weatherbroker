package ru.bellintegrator.weatherbroker.city.service;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.city.model.City;

public interface CityService {
    void save(String cityName);
    City loadByName(String cityName) throws NotFoundException;
}
