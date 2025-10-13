package com.henrique.biograph.Service;

import com.henrique.biograph.DTOs.FamilyPredictionDTO;
import com.henrique.biograph.DTOs.InfernalRequestDTO;
import com.henrique.biograph.DTOs.Response.ResponseToGetDataByRfamAndChainDTO;
import com.henrique.biograph.DTOs.Response.ResponseToGetMoleculesByRfamDTO;
import com.henrique.biograph.DTOs.Response.ResponseToGetStructureByChainAndPdbDTO.ResponseToGetStructureByChainAndPdbDTO;

public interface PredictionService {
    
    FamilyPredictionDTO[] analyzeSequence(InfernalRequestDTO sequence);

    ResponseToGetMoleculesByRfamDTO[] getMoleculesByFamily(String rfam);

    ResponseToGetDataByRfamAndChainDTO getDataByRfamAndChain(String rfam, String chain);

    ResponseToGetStructureByChainAndPdbDTO getStructureByChainAndPdb(String chain, String pdbId, Integer pdbStart, Integer pdbEnd);
}
