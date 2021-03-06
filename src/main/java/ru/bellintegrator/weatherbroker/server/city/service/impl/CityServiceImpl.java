package ru.bellintegrator.weatherbroker.server.city.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.server.city.dao.CityDao;
import ru.bellintegrator.weatherbroker.server.city.model.City;
import ru.bellintegrator.weatherbroker.server.city.service.CityService;
import ru.bellintegrator.weatherbroker.server.city.view.CityView;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    /*
    * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(String cityName) {
        City city = new City(cityName);

        cityDao.save(city);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public City loadByName(String cityName) throws NotFoundException {
        return cityDao.loadByName(cityName);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CityView> getCitiesLikeName(String cityName) {
        List<City> cities = cityDao.citiesLikeName(cityName);
        List<CityView> cityViews = new ArrayList<CityView>(cities.size());

        for (City city: cities) {
            cityViews.add(CityView.mapToCityView(city));
        }

        return cityViews;
    }
}
