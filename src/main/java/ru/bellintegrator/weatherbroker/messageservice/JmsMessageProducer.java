package ru.bellintegrator.weatherbroker.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import ru.bellintegrator.weatherbroker.weather.dao.WeatherDAO;
import ru.bellintegrator.weatherbroker.weather.model.Weather;
import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

import javax.jms.*;

@Component
public class JmsMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final WeatherView weatherView) {
        jmsTemplate.send("java:jboss/exported/jms/topic/messageBoxTopic", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(weatherView);
            }
        });
    }
}
