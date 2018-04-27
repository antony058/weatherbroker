package ru.bellintegrator.weatherbroker.server.city.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.server.city.dao.CityDAO;
import ru.bellintegrator.weatherbroker.server.city.model.City;
import ru.bellintegrator.weatherbroker.server.city.service.CityService;
import ru.bellintegrator.weatherbroker.server.city.view.CityView;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<CityView> getCitiesLikeName(String cityName) {
        List<City> cities = cityDAO.citiesLikeName(cityName);
        List<CityView> cityViews = new ArrayList<CityView>(cities.size());

        for (City city: cities) {
            cityViews.add(CityView.mapToCityView(city));
        }

        return cityViews;
    }
}
