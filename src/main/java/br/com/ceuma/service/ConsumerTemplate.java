package br.com.ceuma.service;

import br.com.ceuma.exception.ConsumerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ConsumerTemplate implements Consumer {


    @Override
    public <T> T get(String url, String querySearch) throws ConsumerException {
        return null;
    }

    @Override
    public <T> List<T> getList(String url, String querySearch) throws ConsumerException {
        return null;
    }

    @Override
    public <T> T post(String url, Object objToSend) throws ConsumerException {
        return null;
    }


}
