package com.apoia.nfse.services;

import com.apoia.nfse.models.MunicipioModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    //private Map<Long, String> municipios;
    private Map<Long, MunicipioModel> municipios;

    @PostConstruct
    public void carregarMunicipios() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = new ClassPathResource("/bases/municipios.json").getInputStream();

            List<MunicipioModel> lista = mapper.readValue(
                    is,
                    new TypeReference<List<MunicipioModel>>() {}
            );

            this.municipios = lista.stream()
                    .collect(Collectors.toMap(
                            MunicipioModel::getCodigoIbge,
                            m -> m
                    ));

        } catch (Exception e) {
            throw new IllegalStateException("Erro ao carregar municipios.json", e);
        }
    }

    public MunicipioModel getMunicipio(long codigoIbge) {
        return municipios.get(codigoIbge);
    }
}