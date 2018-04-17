package ru.bellintegrator.weatherbroker.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.*;

@Component
public class JmsMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(mappedName = "java:jboss/exported/jms/queue/messageBoxQueue")
    private Queue queue;

    @Resource(mappedName = "/ConnectionFactory")
    private ConnectionFactory cf;

    private Connection connection;
    private MessageProducer producer;
    private Session session;

    public void sendMessage(final String messageText) {
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(messageText);
            }
        });

        /*try {
            connection = cf.createConnection();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            producer = session.createProducer((Destination) queue);

            connection.start();
            TextMessage textMessage = session.createTextMessage(messageText);
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }*/
    }

    private void findJndiNames(NamingEnumeration<NameClassPair> list, Context ctx, String path, int spaces) {
        try {
            while (list.hasMore()) {
                String element = list.next().getName();
                System.out.println(getSpaces(spaces) + element);

                try {
                    list = ctx.list(path + "/" + element);
                    findJndiNames(list, ctx, path + "/" + element, spaces + 2);
                } catch (Exception ex) {

                }
            }
        } catch (NamingException ex) {

        }
    }

    private String getSpaces(int amount) {
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<amount;i++)
            builder.append(" ");

        return builder.toString();
    }
}
