package weather.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.weatherbroker.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WeatherControllerTest {
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private static final String baseUrl = "http://127.0.0.1:8080";

    @Test
    public void getCityWeatherSuccessTest() {
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                baseUrl + "/weather/Пенза",
                HttpMethod.GET, httpEntity, String.class
        );

        String response = responseEntity.getBody();
        String correctResult = "{\"temp\":49,\"text\":\"Breezy\",\"city\":\"Пенза\"}";

        Assert.assertEquals(response, correctResult);
    }

    @Test
    public void getCityWeatherNotFoundTest() {
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                baseUrl + "/weather/SomeNotExistingCity",
                HttpMethod.GET, httpEntity, String.class
        );

        int responseCode = responseEntity.getStatusCodeValue();

        Assert.assertEquals(404, responseCode);
    }
}
