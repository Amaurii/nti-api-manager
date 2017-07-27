package br.com.ceuma.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="SIS03MONITT")
@Data
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SIS03CODIGO")
    private Integer codigo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="SIS03DATREQ")
    private Calendar dataRequisicao;

    @Column(name="SIS03STATUSATUAL")
    private String statusAtual;

    @Column(name="SIS03REQRES")
    private String respostaDaUltimaRequisicao;

    @ManyToOne(targetEntity = Sistema.class)
    @JoinColumn(name="FK0103CODSISTEMA", referencedColumnName = "SIS01CODIGO")
    private Sistema sistema;

    public Monitor(Response response, Sistema sistema) {
        this.statusAtual = response.getCodigo().equals(HttpStatus.OK)?"OK":"BROKEN";
        this.respostaDaUltimaRequisicao = response.getMensagem();
        this.sistema = sistema;
        this.dataRequisicao = Calendar.getInstance();
    }
}
