package city;

import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import ru.bellintegrator.weatherbroker.server.city.dao.CityDao;
import ru.bellintegrator.weatherbroker.server.city.dao.impl.CityDaoImpl;
import ru.bellintegrator.weatherbroker.server.city.model.City;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import static org.mockito.Mockito.*;

public class CityDaoTest {
    private EntityManager em;
    private CityDao dao;

    @Before
    public void setUp() {
        em = mock(EntityManager.class);
        dao = new CityDaoImpl(em);
    }

    @Test
    public void saveCitySuccessTest() {
        final City city = new City("Penza");

        dao.save(city);

        verify(em, times(1)).persist(city);
    }

    @Test(expected = NoResultException.class)
    public void loadByNameSuccessTest() throws NotFoundException {
        final String cityName = "Penza";

        when(em.createQuery("SELECT c from City c WHERE c.cityName='" + cityName + "'", City.class))
                .thenThrow(new NoResultException(""));

        dao.loadByName(cityName);
    }
}
