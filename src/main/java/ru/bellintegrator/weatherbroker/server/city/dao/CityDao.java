package ru.bellintegrator.weatherbroker.server.city.dao;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.city.model.City;

import java.util.List;

public interface CityDao {
    /*
    * Добавляет полученный Entity класс города в базу данных
    *
    * @param city - Entity объект города
     */
    void save(City city);

    /*
    * Загружает город из базы данных по точному названию
    *
    * @param cityName - название города
    * @return возвращает Entity города
    * @throws NotFoundException если запрашиваемый город не найден
     */
    City loadByName(String cityName) throws NotFoundException;

    /*
    * Загружает и возвращает список Entity объектов городов из базы данных,
    * которые содержат в своем названии принимаему строку названия города
    *
    * @param cityName - название города
    * @return возвращает список Entity объектов городов
     */
    List<City> citiesLikeName(String cityName);
}
