package ru.bellintegrator.weatherbroker.city.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.city.dao.CityDAO;
import ru.bellintegrator.weatherbroker.city.model.City;
import ru.bellintegrator.weatherbroker.city.service.CityService;
import ru.bellintegrator.weatherbroker.weather.model.Weather;

@Service
public class CityServiceImpl implements CityService {

    private final CityDAO cityDAO;

    @Autowired
    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    @Transactional
    public void save(String cityName) {
        City city = new City(cityName);

        cityDAO.save(city);
    }

    @Override
    @Transactional(readOnly = true)
    public City loadByName(String cityName) throws NotFoundException {
        return cityDAO.loadByName(cityName);
    }
}
