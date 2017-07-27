package br.com.ceuma.model;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

/**
 * Created by marcus on 26/07/17.
 */

@Entity
@Table(name="SIS01SISTET")
@Data
public class Sistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SIS01CODIGO")
    private Integer codigo;

    @Column(name="SIS01NOMESIS")
    private String nome;

    @Column(name="SIS01URLMASTER")
    private String urlMaster;

    @Column(name="SIS01TESTLINK")
    private String testLink;

    @Column(name="SIS01DOCURL")
    private String docUrl;

    @ManyToOne(targetEntity = Pilar.class)
    @JoinColumn(name="FK0201CODPILAR",referencedColumnName = "SIS02CODIGO")
    private Pilar pilar;

    @Column(name="SIS01DESCR")
    private String descricao;
}
