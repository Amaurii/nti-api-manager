package br.com.ceuma.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonAutoDetect
@Data
public class Response {

    @JsonProperty
    private Integer codigo;

    @JsonProperty
    private String mensagem;

    public Response(Integer codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}
