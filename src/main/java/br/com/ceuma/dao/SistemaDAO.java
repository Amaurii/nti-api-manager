package br.com.ceuma.dao;

import br.com.ceuma.model.Sistema;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by marcus on 26/07/17.
 */
@Repository
@Transactional
public class SistemaDAO extends AbstractDAO<Sistema,Integer> implements ISistemaDAO{


    @Override
    public List<Sistema> recuperarSistemas() throws Exception {
        return createEntityCriteria().list();
    }
}
