package ru.bellintegrator.weatherbroker.requestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.weatherbroker.weather.view.Query;
import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

@Component
public class RestTemplateManager {

    private Logger log = LoggerFactory.getLogger(RestTemplateManager.class);

    private final static String urlFirstPart = "https://query.yahooapis.com/v1/public/yql?q=select item.condition " +
            "from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"";
    private final static String urlLastPart = "\")&format=json&u=c";

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherView sendRequest(String cityName) {
        Query query = restTemplate.getForObject(urlFirstPart + cityName + urlLastPart, Query.class);

        log.info("Загружена погода города " + cityName);

        return WeatherView.mapToWeather(query, cityName);
    }
}
