package ru.bellintegrator.weatherbroker.weather.service.impl;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.city.model.City;
import ru.bellintegrator.weatherbroker.city.service.CityService;
import ru.bellintegrator.weatherbroker.weather.dao.WeatherDAO;
import ru.bellintegrator.weatherbroker.weather.model.Weather;
import ru.bellintegrator.weatherbroker.weather.service.WeatherService;
import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

@Service
public class WeatherServiceImpl implements WeatherService {

    private Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private final WeatherDAO weatherDAO;
    private final CityService cityService;

    @Autowired
    public WeatherServiceImpl(WeatherDAO weatherDAO, CityService cityService) {
        this.weatherDAO = weatherDAO;
        this.cityService = cityService;
    }

    @Override
    @Transactional
    public void save(WeatherView weatherView) throws NotFoundException {
        City city = cityService.loadByName(weatherView.getCity());

        Weather weather = new Weather(weatherView.getTemp(), weatherView.getText());
        weather.setCity(city);

        weatherDAO.save(weather);

        log.info("Погода добавлена в бд");
    }

    @Override
    @Transactional(readOnly = true)
    public WeatherView getCityWeather(String cityName) {
        Weather weather = weatherDAO.getWeatherByCity(cityName);

        return WeatherView.mapToWeatherView(weather, cityName);
    }
}
