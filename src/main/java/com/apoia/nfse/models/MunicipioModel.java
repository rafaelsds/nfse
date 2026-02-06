package com.apoia.nfse.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipioModel {

    @JsonProperty("codigo_ibge")
    private long codigoIbge;

    private String nome;
    private String uf;
    private String nomeUf;

}
