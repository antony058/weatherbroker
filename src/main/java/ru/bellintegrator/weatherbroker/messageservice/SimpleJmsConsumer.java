package ru.bellintegrator.weatherbroker.messageservice;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class SimpleJmsConsumer {
    private static final String CONNECTION_FACTORY = "/jms/RemoteConnectionFactory";
    private static final String QUEUE_DESTINATION = "/jms/queue/messageBoxQueue";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

    /*public static void main(String[] args) {
        Context namingContext = null;
        JMSContext context = null;

        final Properties prop = new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        prop.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        prop.put(Context.SECURITY_PRINCIPAL, "appuser");
        prop.put(Context.SECURITY_CREDENTIALS, "password");

        try {
            namingContext = new InitialContext(prop);

            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
            Queue destination = (Queue) namingContext.lookup(QUEUE_DESTINATION);

            context = connectionFactory.createContext("appuser", "password");

            JMSConsumer consumer = context.createConsumer(destination);
            String messageText = consumer.receiveBodyNoWait(String.class);
            System.out.println("Received message: " + messageText);

        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (namingContext != null)
                    namingContext.close();

                if (context != null)
                    context.close();
            } catch (NamingException ex) {
                ex.printStackTrace();
            }
        }
    }*/
}
