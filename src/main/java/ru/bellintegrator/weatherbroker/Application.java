package ru.bellintegrator.weatherbroker;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.atomikos.jms.AtomikosConnectionFactoryBean;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.transaction.SystemException;
import java.util.Properties;

@SpringBootApplication
@EnableJms
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

    @Bean
    public ActiveMQXAConnectionFactory connectionFactory() {
        ActiveMQXAConnectionFactory connectionFactory = new ActiveMQXAConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);

        return connectionFactory;
    }

    @Bean
    public ActiveMQTopic activeMQTopic() {
        return new ActiveMQTopic("testTopic");
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactoryBean());
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setDefaultDestination(activeMQTopic());
        jmsTemplate.setSessionTransacted(true);

        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory containerFactory() throws SystemException {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactoryBean());
        factory.setTransactionManager(transactionManager());
        factory.setConcurrency("1-1");
        factory.setPubSubDomain(true);
        factory.setSessionTransacted(true);

        return factory;
    }

    @Bean
    public AtomikosConnectionFactoryBean connectionFactoryBean() {
        AtomikosConnectionFactoryBean connectionFactoryBean = new AtomikosConnectionFactoryBean();
        connectionFactoryBean.setUniqueResourceName("atomikosConnectionFactoryCustomBean");
        connectionFactoryBean.setXaConnectionFactory(connectionFactory());
        connectionFactoryBean.setMaxPoolSize(10);

        return connectionFactoryBean;
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("serverName", "localhost");
        properties.setProperty("portNumber", "5432");
        properties.setProperty("databaseName", "postgres");
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "password");

        return properties;
    }

    @Bean
    public AtomikosDataSourceBean dataSource() {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        dataSource.setXaProperties(properties());
        dataSource.setUniqueResourceName("postgresDatasourceCustomBean");
        dataSource.setPoolSize(1);

        return dataSource;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager jtaTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);

        return userTransactionManager;
    }

    @Bean
    public UserTransactionImp jtaUserTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);

        return userTransactionImp;
    }

    @Bean
    public JtaTransactionManager transactionManager() throws SystemException {
        JtaTransactionManager transactionManager = new JtaTransactionManager();
        transactionManager.setTransactionManager(jtaTransactionManager());
        transactionManager.setUserTransaction(jtaUserTransaction());

        return transactionManager;
    }
}
