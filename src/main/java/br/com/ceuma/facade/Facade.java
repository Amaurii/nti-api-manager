package br.com.ceuma.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Facade implements IFacade {


    @Autowired
    private ApplicationContext context;

    @Override
    public <T> T get(Class<T> clazz) throws Exception {
        try {
            return context.getBean(clazz);
        } catch (SecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}
