package com.henrique.biograph.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.henrique.biograph.DTOs.FamilyPredictionDTO;
import com.henrique.biograph.DTOs.InfernalRequestDTO;
import com.henrique.biograph.DTOs.Response.ResponseToGetDataByRfamAndChain;
import com.henrique.biograph.DTOs.Response.ResponseToGetMoleculesByRfam;
import com.henrique.biograph.Service.PredictionService;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final WebClient webClient;

    @Autowired
    public PredictionServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public FamilyPredictionDTO[] analyzeSequence(InfernalRequestDTO sequenceRequest) {
        return webClient
                .post()
                .uri("/infernal")
                .bodyValue(sequenceRequest)
                .retrieve()
                .bodyToMono(FamilyPredictionDTO[].class)
                .block(); // <- Executa e retorna o resultado sincronicamente
    }

    @Override
    public ResponseToGetMoleculesByRfam[] getMoleculesByFamily(String rfam) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/get_molecules_by_rfam")
                        .queryParam("rfam_acc", rfam)
                        .build())
                .retrieve()
                .bodyToMono(ResponseToGetMoleculesByRfam[].class)
                .block();
    }

    @Override
    public ResponseToGetDataByRfamAndChain getDataByRfamAndChain(String rfam, String chain) {
        return webClient
            .get()
            .uri(uriBuilder -> uriBuilder
                    .path("/get_data_by_rfam_and_chain")
                    .queryParam("rfam_acc", rfam)
                    .queryParam("chain", chain)
                    .build())
            .retrieve()
            .bodyToMono(ResponseToGetDataByRfamAndChain.class)
            .block();
    }

}