package br.com.ceuma.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
@Data
public class Usuario {

    @JsonProperty
    private Integer codigo;

    @JsonProperty
    private String nome;

    @JsonProperty
    private String senha;


    private List<String> role;

    public Usuario(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Usuario() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public List<String> getRole() {
        role = new ArrayList<>();
        role.add("N_USER");
        return role;
    }
}
