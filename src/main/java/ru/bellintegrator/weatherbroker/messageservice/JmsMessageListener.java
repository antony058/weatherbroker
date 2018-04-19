package ru.bellintegrator.weatherbroker.messageservice;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.bellintegrator.weatherbroker.weather.dao.WeatherDAO;
import ru.bellintegrator.weatherbroker.weather.model.Weather;
import ru.bellintegrator.weatherbroker.weather.service.WeatherService;
import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public class JmsMessageListener {
    private Logger log = LoggerFactory.getLogger(JmsMessageListener.class);

    @Autowired
    private WeatherService weatherService;

    @JmsListener(destination = "java:jboss/exported/jms/topic/messageBoxTopic")
    public void gotMessage(Message message) throws JMSException, NotFoundException {
        if (message instanceof ObjectMessage) {
            WeatherView view = (WeatherView) ((ObjectMessage) message).getObject();

            log.info("Данные о погоде получены из topic");
            weatherService.save(view);
        }
    }
}
