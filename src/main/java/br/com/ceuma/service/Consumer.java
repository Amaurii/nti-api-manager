package br.com.ceuma.service;

import br.com.ceuma.exception.ConsumerException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by markiing on 26/07/17.
 */
public interface Consumer {

    Object get(String url, String querySearch, Class typeReturnExpected) throws ConsumerException;
    List<Object> getList(String url, String querySearch, Class typeReturnExpected) throws ConsumerException;
    ResponseEntity post(String url, Object objToSend, Class typeReturnExpected) throws ConsumerException;
}
