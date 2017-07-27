package br.com.ceuma.configuration.ceuma;

import br.com.ceuma.configuration.general.Context;
import br.com.ceuma.configuration.general.SpringConfiguration;
import br.com.ceuma.configuration.hibernate.HibernateConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

@Configuration
@Service
@Import({SpringConfiguration.class, HibernateConfiguration.class})
@ComponentScan(basePackages = {"br.com.ceuma"})
@PropertySource(value = {"classpath:application-dev-ceuma.yml","classpath:application.yml"})
@Profile({"dev-ceuma"})
public class CeumaDevelopment implements Context {

    @Override
    @Bean(name="CONTEXT")
    public String getContext() {
        return "CEUMA DEVELOPMENT";
    }
}
