package ru.bellintegrator.weatherbroker.server.city.service;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.city.model.City;
import ru.bellintegrator.weatherbroker.server.city.view.CityView;

import java.util.List;

public interface CityService {
    void save(String cityName);
    City loadByName(String cityName) throws NotFoundException;
    List<CityView> getCitiesLikeName(String cityName);
}
