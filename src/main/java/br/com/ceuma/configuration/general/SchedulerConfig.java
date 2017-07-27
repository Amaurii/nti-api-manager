package br.com.ceuma.configuration.general;


import br.com.ceuma.job.CheckAPIJob;
import liquibase.integration.spring.SpringLiquibase;
import org.h2.jdbcx.JdbcDataSource;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {

    @Bean
    public JobFactory jobFactory(ApplicationContext context, SpringLiquibase springLiquibase){
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(context);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource,
                                                      JobFactory jobFactory,
                                                      @Qualifier("checkJobTrigger")Trigger checkJobTrigger) throws IOException{
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(checkJobTrigger);

        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {

        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz-configuration.yml"));
        propertiesFactoryBean.afterPropertiesSet();;

        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobDetailFactoryBean checkApiJob(){
        return createJobDetail(CheckAPIJob.class);
    }

    @Bean(name="checkJobTrigger")
    public SimpleTriggerFactoryBean checkJobTrigger(@Qualifier("checkApiJob") JobDetail jobDetail,
                                                    @Value("${job.frequency}") long frequency){
        return createTrigger(jobDetail,frequency);
    }

    private JobDetailFactoryBean createJobDetail(Class jobClazz) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(jobClazz);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }


    private static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long frequency) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        return factoryBean;
    }


}
