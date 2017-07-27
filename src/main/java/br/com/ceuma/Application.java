package br.com.ceuma;

import br.com.ceuma.configuration.general.Context;
import br.com.ceuma.configuration.general.ContextProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableConfigurationProperties(ContextProperties.class)
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ContextProperties contextProperties;

    @Autowired
    private Context context;


    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @PostConstruct
    private void init(){
        logger.info("Spring Boot - active profile: " + context.getContext());
        logger.info(contextProperties.toString());

    }

}
