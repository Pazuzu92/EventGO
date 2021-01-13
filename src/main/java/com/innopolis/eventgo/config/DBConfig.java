package com.innopolis.eventgo.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean
    public DataSource dataSource() {
        try {
            DriverManagerDataSource dbBuilder = new DriverManagerDataSource();
            dbBuilder.setDriverClassName("org.postgresql.Driver");
            dbBuilder.setUrl("jdbc:postgresql://localhost:5432/eventgo");
            dbBuilder.setUsername("postgres");
            dbBuilder.setPassword("postgres");
            return dbBuilder;
        } catch (Exception e) {
            return null;
        }
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.use_sql_comments", true);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.max_fetch_depth", 3);
        properties.put("hibernate.jdbc_batch_size", 10);
        properties.put("hibernate.jdbc_fetch_size", 50);
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.innopolis.eventgo.db.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }
}
