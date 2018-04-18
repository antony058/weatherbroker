package ru.bellintegrator.weatherbroker.messageservice;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class JmsMessageListener {

    @JmsListener(destination = "java:jboss/exported/jms/queue/messageBoxQueue")
    public void gotMessage(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            System.out.println("I got a new message: " + ((TextMessage) message).getText());
        }
    }
}
