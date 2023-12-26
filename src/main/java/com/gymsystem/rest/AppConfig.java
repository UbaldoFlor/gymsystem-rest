package com.gymsystem.rest;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gymsystem.rest.dao.TraineeDao;
import com.gymsystem.rest.dao.TrainerDao;
import com.gymsystem.rest.dao.TrainingDao;
import com.gymsystem.rest.dao.TrainingTypeDao;
import com.gymsystem.rest.dao.UserDao;
import com.gymsystem.rest.dao.impl.TraineeDaoImpl;
import com.gymsystem.rest.dao.impl.TrainerDaoImpl;
import com.gymsystem.rest.dao.impl.TrainingDaoImpl;
import com.gymsystem.rest.dao.impl.TrainingTypeDaoImpl;
import com.gymsystem.rest.dao.impl.UserDaoImpl;

@Configuration
@ComponentScan(basePackages = "com.gymsystem.rest, " +
							  "com.gymsystem.rest.service, " +
							  "com.gymsystem.rest.dao, " +
							  "com.gymsystem.rest.facade")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.gymsystem.rest.dao.impl")
@PropertySource("classpath:application.properties")
public class AppConfig {
	
	@Bean
    public UserDao userDao(LocalSessionFactoryBean sessionFactory) {
        return new UserDaoImpl(sessionFactory);
    }
	@Bean
    public TraineeDao traineeDao(LocalSessionFactoryBean sessionFactory) {
        return new TraineeDaoImpl(sessionFactory);
    }
	@Bean
    public TrainerDao trainerDao(LocalSessionFactoryBean sessionFactory) {
        return new TrainerDaoImpl(sessionFactory);
    }
	@Bean
    public TrainingDao trainingDao(LocalSessionFactoryBean sessionFactory) {
        return new TrainingDaoImpl(sessionFactory);
    }
	@Bean
    public TrainingTypeDao trainingTypeDao(LocalSessionFactoryBean sessionFactory) {
        return new TrainingTypeDaoImpl(sessionFactory);
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.gymsystem.rest.model");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName,
                                 @Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }
    
    @Bean
    public Properties hibernateProperties(@Value("${spring.jpa.database-platform}") String dialect,
                                          @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        return properties;
    }
}