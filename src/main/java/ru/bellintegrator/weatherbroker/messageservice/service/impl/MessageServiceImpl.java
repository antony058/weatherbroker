package ru.bellintegrator.weatherbroker.messageservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherbroker.messageservice.JmsMessageProducer;
import ru.bellintegrator.weatherbroker.messageservice.service.MessageService;
import ru.bellintegrator.weatherbroker.weather.view.WeatherView;

@Service
public class MessageServiceImpl implements MessageService {
    private Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final JmsMessageProducer jmsMessageProducer;

    @Autowired
    public MessageServiceImpl(JmsMessageProducer jmsMessageProducer) {
        this.jmsMessageProducer = jmsMessageProducer;
    }

    @Override
    public void sendMessageToTopic(WeatherView view) {
        jmsMessageProducer.sendMessage(view);
        log.info("Данные о погоде отправлены в topic");
    }
}
