package ru.bellintegrator.weatherbroker.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JmsMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String messageText) {
        jmsTemplate.send("java:jboss/exported/jms/queue/messageBoxQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(messageText);
            }
        });
    }
}
