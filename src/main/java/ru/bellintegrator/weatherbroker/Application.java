package ru.bellintegrator.weatherbroker;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

@SpringBootApplication
@EnableJms
@ComponentScan
public class Application extends SpringBootServletInitializer {
    private static final String BROKER_URL = "tcp://127.0.0.1:61616";
    private static final String TOPIC_DESTINATION_NAME = "testTopic";

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(applicationClass);
    }

    private static Class<Application> applicationClass = Application.class;

    /*@Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestinationName("");

        return jmsTemplate;
    }*/

    @Bean
    public ActiveMQXAConnectionFactory connectionFactory() {
        ActiveMQXAConnectionFactory connectionFactory = new ActiveMQXAConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setDefaultDestinationName(TOPIC_DESTINATION_NAME);

        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory containerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        factory.setPubSubDomain(true);
        factory.setSessionTransacted(true);

        return factory;
    }
}
