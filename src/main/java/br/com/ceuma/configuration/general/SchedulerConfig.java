package br.com.ceuma.configuration.general;


import liquibase.integration.spring.SpringLiquibase;
import org.quartz.spi.JobFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {


    @Bean
    public JobFactory jobFactory(ApplicationContext context, SpringLiquibase springLiquibase){
        Autowirin
    }
}
