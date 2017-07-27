package br.com.ceuma.configuration.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

    @Autowired
    private Environment enviroment;


    /* CONFIGURAÇAO DO HIBERNATE */
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setHibernateProperties(hibernateProperties());
        factory.setDataSource(dataSource());
        factory.setPackagesToScan(new String[]{"br.com.ceuma"});
        return factory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(enviroment.getProperty("jdbc.driver.className"));
        dataSource.setUrl(enviroment.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(enviroment.getRequiredProperty("datasource.username"));
        dataSource.setPassword(enviroment.getRequiredProperty("datasource.password"));
        return dataSource;
    }

    private Properties hibernateProperties(){
        Properties prop = new Properties();
        prop.put("hibernate.dialect", enviroment.getRequiredProperty("properties.hibernate.dialect"));
        prop.put("hibernate.show_sql", enviroment.getRequiredProperty("properties.hibernate.show_sql"));
        prop.put("hibernate.hbm2ddl.auto", enviroment.getRequiredProperty("properties.hibernate.hbm2ddl.auto"));
        prop.put("hibernate.format_sql", enviroment.getRequiredProperty("properties.hibernate.format_sql"));
        prop.put("hibernate.transaction.auto_close_session",enviroment.getRequiredProperty("properties.hibernate.transaction.auto_close_session"));
        return prop;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
