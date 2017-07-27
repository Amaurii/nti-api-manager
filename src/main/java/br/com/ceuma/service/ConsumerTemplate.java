package br.com.ceuma.service;

import br.com.ceuma.exception.ConsumerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ConsumerTemplate implements Consumer {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object get(String url, String querySearch, Class typeReturnExpected) throws ConsumerException {
        Object forObject = restTemplate.getForObject(url+querySearch, typeReturnExpected);
        return forObject;
    }

    @Override
    public List<Object> getList(String url, String querySearch, Class typeReturnExpected) throws ConsumerException {
        Object forObject = restTemplate.getForObject(url + querySearch, typeReturnExpected);
        return (List<Object>) forObject;
    }

    @Override
    public ResponseEntity post(String url, Object objToSend, Class typeReturnExpected) throws ConsumerException {
        ResponseEntity responseEntity = restTemplate.postForEntity(url, objToSend, typeReturnExpected);
        return responseEntity;
    }
}
