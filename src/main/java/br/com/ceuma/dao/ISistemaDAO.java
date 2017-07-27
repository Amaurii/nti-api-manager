package br.com.ceuma.dao;

import br.com.ceuma.model.Sistema;

import java.util.List;

/**
 * Created by marcus on 26/07/17.
 */
public interface ISistemaDAO {

    List<Sistema> recuperarSistemas() throws Exception;
}
