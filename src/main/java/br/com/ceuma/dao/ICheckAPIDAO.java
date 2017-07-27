package br.com.ceuma.dao;


import br.com.ceuma.exception.ConsumerException;
import br.com.ceuma.model.Response;
import br.com.ceuma.model.Sistema;

public interface ICheckAPIDAO {

    Response getAPiResponse(Sistema sistema) throws ConsumerException;

}
