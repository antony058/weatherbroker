package weather;

import org.junit.Before;
import org.junit.Test;
import ru.bellintegrator.weatherbroker.server.weather.dao.WeatherDao;
import ru.bellintegrator.weatherbroker.server.weather.dao.impl.WeatherDaoImpl;
import ru.bellintegrator.weatherbroker.server.weather.model.Weather;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;

public class WeatherDaoTest {

    private EntityManager em;
    private WeatherDao dao;

    @Before
    public void setUp() {
        em = mock(EntityManager.class);
        dao = new WeatherDaoImpl(em);
    }

    @Test
    public void saveWeatherSuccessTest() {
        Weather weather = new Weather();
        dao.save(weather);

        verify(em).persist(weather);
    }
}
