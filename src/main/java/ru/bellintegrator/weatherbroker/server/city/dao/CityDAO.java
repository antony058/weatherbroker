package ru.bellintegrator.weatherbroker.server.city.dao;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.city.model.City;

import java.util.List;

public interface CityDAO {
    void save(City city);
    City loadByName(String cityName) throws NotFoundException;
    List<City> citiesByName(String cityName);
}
