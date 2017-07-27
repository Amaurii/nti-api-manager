package br.com.ceuma.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by marcus on 26/07/17.
 */

@Entity
@Table(name = "SIS02PILART")
@Data
public class Pilar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SIS02CODIGO")
    private Integer codigo;

    @Column(name="SIS02DESCPILAR")
    private String descricao;
}
