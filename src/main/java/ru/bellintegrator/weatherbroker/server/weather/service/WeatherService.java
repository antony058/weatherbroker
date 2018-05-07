package ru.bellintegrator.weatherbroker.server.weather.service;

import javassist.NotFoundException;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

public interface WeatherService {
    /*
    * Сохраняет погоду для конкретного города в базу данных
    *
    * @param weatherView - объект представления погоды конкретного города
    *
    * @throws NotFoundException Если город, для которого пытаемся
     *                          добавить погоду не найден
     */
    void save(WeatherView weatherView) throws NotFoundException;

    /*
    * Ищет в базе данных погоду для заданного города и возвращает
    * объект представления {@link WeatherView}
    *
    * @param cityName - название города
    * @return возвращает объект представления погоды
    *
    * @throws NotFoundException Если погода для заданного города не найдена в БД
     */
    WeatherView getCityWeather(String cityName) throws NotFoundException;
}
