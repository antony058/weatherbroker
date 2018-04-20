package ru.bellintegrator.weatherbroker.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.weatherbroker.client.requestservice.RestTemplateManager;

@Configuration
public class AppConfig {
    private static final String CONNECTION_FACTORY = "/jms/RemoteConnectionFactory";
    private static final String QUEUE_DESTINATION = "java:jboss/exported/jms/queue/messageBoxQueue";

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplateManager getRestTemplateManager() {
        return new RestTemplateManager(getRestTemplate());
    }

    /*@Bean
    public InitialContext getInitialContext() throws NamingException {
        return new InitialContext();
    }

    @Bean
    public ConnectionFactory getConnectionFactory() throws NamingException {
        return (ConnectionFactory) getInitialContext().lookup(CONNECTION_FACTORY);
    }

    @Bean
    public Queue getQueue() throws NamingException {
        return (Queue) getInitialContext().lookup(QUEUE_DESTINATION);
    }

    @Bean
    public JmsMessageListener getJmsMessageListener() {
        return new JmsMessageListener();
    }

    @Bean
    public DefaultMessageListenerContainer getDefaultMessageListenerContainer() throws NamingException {
        DefaultMessageListenerContainer jmsContainer = new DefaultMessageListenerContainer();
        jmsContainer.setConnectionFactory(getConnectionFactory());
        jmsContainer.setDestination(getQueue());
        jmsContainer.setMessageListener(getJmsMessageListener());

        return jmsContainer;
    }*/
}