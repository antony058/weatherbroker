package ru.bellintegrator.weatherbroker.city.dao.impl;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.weatherbroker.city.dao.CityDAO;
import ru.bellintegrator.weatherbroker.city.model.City;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class CityDAOImpl implements CityDAO {
    private Logger log = LoggerFactory.getLogger(CityDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public CityDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void save(City city) {
        em.persist(city);
    }

    @Override
    public City loadByName(String cityName) throws NotFoundException {
        TypedQuery<City> query = em.createQuery("SELECT c from City c WHERE c.cityName='" + cityName + "'", City.class);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            log.error("Город " + cityName + " не найден");
            throw new NotFoundException("City not founded");
        }
    }
}
