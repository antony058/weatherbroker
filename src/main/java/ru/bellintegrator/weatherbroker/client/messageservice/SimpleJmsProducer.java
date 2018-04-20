package ru.bellintegrator.weatherbroker.client.messageservice;

import org.springframework.stereotype.Component;

@Component
public class SimpleJmsProducer {
    private static final String CONNECTION_FACTORY = "/jms/RemoteConnectionFactory";
    private static final String QUEUE_DESTINATION = "/jms/queue/messageBoxQueue";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

    /*public static void main(String[] args) throws NamingException {
        Context namingContext = null;
        JMSContext context = null;

        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        env.put(Context.SECURITY_PRINCIPAL, "appuser");
        env.put(Context.SECURITY_CREDENTIALS, "password");

        try {
            namingContext = new InitialContext(env);

            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
            Queue destination = (Queue) namingContext.lookup(QUEUE_DESTINATION);

            context = connectionFactory.createContext("appuser", "password"); // again, don't do this in production

            context.createProducer().send(destination, "hello, hello");
            System.out.println("Message sent.");

        } finally {
            if (namingContext != null) {
                namingContext.close();
            }
            if (context != null) {
                context.close();
            }
        }
    }*/
}