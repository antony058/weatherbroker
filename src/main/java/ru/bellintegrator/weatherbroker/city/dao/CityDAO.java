package ru.bellintegrator.weatherbroker.city.dao;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.city.model.City;

public interface CityDAO {
    void save(City city);
    City loadByName(String cityName) throws NotFoundException;
}
