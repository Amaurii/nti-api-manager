package br.com.ceuma.configuration.ceuma;


import br.com.ceuma.configuration.general.Context;
import br.com.ceuma.configuration.general.SpringConfiguration;
import br.com.ceuma.configuration.hibernate.HibernateConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@Import({SpringConfiguration.class, HibernateConfiguration.class})
@ComponentScan(basePackages = {"br.com.ceuma"})
@PropertySource(value = {"classpath:application-prod-ceuma.yml"})
@Profile("prod-ceuma")
public class CeumaProduction implements Context {

    @Override
    @Bean(name = "CONTEXT")
    public String getContext() {
        return "CEUMA PRODUCTION";
    }
}
