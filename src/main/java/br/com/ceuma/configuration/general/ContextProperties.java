package br.com.ceuma.configuration.general;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class ContextProperties {

    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "ContextProperties{" +
                "context='" + context + '\'' +
                '}';
    }
}
