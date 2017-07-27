package br.com.ceuma.configuration.general;

import br.com.ceuma.facade.Facade;
import br.com.ceuma.facade.IFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SpringConfiguration {

    @Bean
    public IFacade facade(){
        return new Facade();
    }

    @Bean(name = "HEADER_AUTHORIZATION")
    public String authorizationHeader(){
        return new String("Authorization");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}



