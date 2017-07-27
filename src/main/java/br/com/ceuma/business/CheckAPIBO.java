package br.com.ceuma.business;

import br.com.ceuma.dao.ICheckAPIDAO;
import br.com.ceuma.dao.ISistemaDAO;
import br.com.ceuma.exception.BrokenAPIException;
import br.com.ceuma.exception.ConsumerException;
import br.com.ceuma.model.Monitor;
import br.com.ceuma.model.Response;
import br.com.ceuma.model.Sistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by marcus on 26/07/17.
 */
@Component
public class CheckAPIBO implements ICheckAPIBO {

    @Autowired
    private ICheckAPIDAO checkAPIDAO;

    @Autowired
    private ISistemaDAO sistemaDAO;

    /**
     * MÉTODO PARA RECUPERAÇÃO DOS SISTEMAS CADASTRADOS NA BASE E A VERIFICAÇÃO DOS MESMOS ATRAVÉS DE UM LOOP
     * A CADA ITERAÇÃO, É VERIFICADO SE O 'RESPONSE' POSSUI O STATUS 200 DO ENUM HTTPStatus DO SPRING
     *
     * INDEPENDENTEMENTE DE EXCEÇÕES CAPTURADAS, PRECISA-SE REGISTRAR UM LOG NA BASE DE DADOS DA ULTIMA REQUISIÇÃO FEITA
     *
     * CASO A EXCEÇÃO SEJA DO TIPO BrokenApiException, O SISTEMA TENTARÁ REUPAR A APLICAÇÃO. CASO HAJA FALHA, O REUP DEVE SER FEITO VIA INTERFACE WEB
     *
     * @see Sistema
     * @see BrokenAPIException
     * @see ConsumerException
     *
     * @since 26/07/2017 23:39
     * @throws Exception
     */
    @Override
    public void checar() throws Exception{
        List<Sistema> sistemas = sistemaDAO.recuperarSistemas();
        Monitor monitor = null;

        for (Sistema sistema : sistemas) {
            try {
                Response apiResponse = checkAPIDAO.getAPiResponse(sistema);
                sistema.sistemaEstaDisponivel(apiResponse);

                monitor = new Monitor(apiResponse,sistema);
            } catch (Exception e) {
                System.out.println("Devo notificar as partes !");
                if (e instanceof BrokenAPIException)
                    System.out.println("Devo subir a aplicação novamente");
                else if (e instanceof ConsumerException)
                    System.out.println("Erro ao verificar disponibilidade");
            }
            //TODO @AMAURI, AQUI DEVE-SE INSERIR OS REGISTROS DO MONITOR (ESTADO DA NOVA REQUISICAO)
            System.out.println(monitor);
        }

    }
}
