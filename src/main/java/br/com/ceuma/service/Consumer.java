package br.com.ceuma.service;

import br.com.ceuma.exception.ConsumerException;

import java.util.List;

/**
 * Created by markiing on 26/07/17.
 */
public interface Consumer {

    <T> T get(String url, String querySearch) throws ConsumerException;
    <T> List<T> getList(String url, String querySearch) throws ConsumerException;
    <T> T post(String url, Object objToSend) throws ConsumerException;
}
