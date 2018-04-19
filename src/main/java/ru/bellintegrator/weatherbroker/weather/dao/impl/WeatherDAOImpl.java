package ru.bellintegrator.weatherbroker.weather.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.weather.dao.WeatherDAO;
import ru.bellintegrator.weatherbroker.weather.model.Weather;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class WeatherDAOImpl implements WeatherDAO {

    private final EntityManager em;

    @Autowired
    public WeatherDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Weather weather) {
        em.persist(weather);
    }

    @Override
    public List<Weather> getWeatherList() {
        return em.createQuery("select w from Weather w", Weather.class).getResultList();
    }

    @Override
    public Weather getWeatherByCity(String cityName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Weather.class);

        Root<Weather> weatherRoot = criteriaQuery.from(Weather.class);
        criteriaQuery.where(
                criteriaBuilder.equal(weatherRoot.join("city").get("cityName"), cityName)
        );

        TypedQuery<Weather> query = em.createQuery(criteriaQuery);

        /*
        * N.B. В БД может быть несколько записей о погоде для конкретного города
        * Соответственно, в строке ниже может вывалится ошибка.
        *
        * Это нужно исправить.
         */
        return query.getSingleResult();
    }
}
