package ru.bellintegrator.weatherbroker.server.weather.dao;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.weather.model.Weather;

import java.util.List;

public interface WeatherDao {
    /*
    * Добавляет полученный Entity-объект в базу данных
    *
    * @param weather - Entity-объект погоды
     */
    void save(Weather weather);

    /*
    * Возвращает Entity-объект погоды, найденный в базе данных по названию города
    *
    * @param cityName - название города
    * @return возвращает объект представления погоды конкретного города
    *
    * @throws NotFoundException Если погода или город не найден
     */
    Weather getWeatherByCity(String cityName) throws NotFoundException;
}
