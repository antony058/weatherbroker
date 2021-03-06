package ru.bellintegrator.weatherbroker.client.messageservice;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.bellintegrator.weatherbroker.server.weather.service.WeatherService;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public class JmsMessageListener {
    private Logger log = LoggerFactory.getLogger(JmsMessageListener.class);

    @Autowired
    private WeatherService weatherService;

    /*
    * Слушатель сообщений из Topic. Сообщением являются данные о погоде определенного города.
    * Полученные данные о погоде записываются в базу данных
    *
    * @param message - сообщение из Topic'а
     */
    @JmsListener(destination = "testTopic", containerFactory = "containerFactory")
    public void gotMessage(Message message) throws JMSException, NotFoundException {
        if (message instanceof ObjectMessage) {
            WeatherView view = (WeatherView) ((ObjectMessage) message).getObject();

            log.info("Данные о погоде получены из topic");
            weatherService.save(view);
        }
    }
}
