package br.com.ceuma.business;

import br.com.ceuma.exception.ConsumerException;

/**
 * Created by marcus on 26/07/17.
 */
public interface ICheckAPIBO {

    void checar() throws ConsumerException, Exception;
}
