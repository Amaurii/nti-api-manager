package br.com.ceuma.dao;

import br.com.ceuma.business.ICheckAPIBO;
import br.com.ceuma.exception.ConsumerException;
import br.com.ceuma.model.Response;
import br.com.ceuma.model.Sistema;
import br.com.ceuma.service.ConsumerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by marcus on 26/07/17.
 */
@Repository
public class CheckAPIDAO implements ICheckAPIDAO {

    @Autowired
    private ConsumerTemplate consumerTemplate;

    @Override
    public Response getAPiResponse(Sistema sistema) throws ConsumerException {

        String url = sistema.getUrlMaster()+sistema.getTestLink();
        Response response = (Response) consumerTemplate.get(url, null, Response.class);

        return response;
    }
}
