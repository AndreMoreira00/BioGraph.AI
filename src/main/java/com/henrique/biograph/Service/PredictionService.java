package com.henrique.biograph.Service;

import com.henrique.biograph.DTOs.FamilyPredictionDTO;
import com.henrique.biograph.DTOs.InfernalRequestDTO;
import com.henrique.biograph.DTOs.Response.ResponseToGetDataByRfamAndChain;
import com.henrique.biograph.DTOs.Response.ResponseToGetMoleculesByRfam;

public interface PredictionService {
    
    FamilyPredictionDTO[] analyzeSequence(InfernalRequestDTO sequence);

    ResponseToGetMoleculesByRfam[] getMoleculesByFamily(String rfam);

    ResponseToGetDataByRfamAndChain getDataByRfamAndChain(String rfam, String chain);
}
