package com.henrique.biograph.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.biograph.DTOs.FamilyPredictionDTO;
import com.henrique.biograph.DTOs.InfernalRequestDTO;
import com.henrique.biograph.DTOs.Response.ResponseToGetDataByRfamAndChain;
import com.henrique.biograph.DTOs.Response.ResponseToGetMoleculesByRfam;
// import com.henrique.biograph.DTOs.Response.ResponseToAnalyzeSequenceFromUserInDotBrecket;
import com.henrique.biograph.Service.PredictionService;

@RestController
public class SequenceController {

    private final PredictionService predictionService;

    @Autowired
    public SequenceController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    // @PostMapping("/analyze_sequence_all")
    // public ResponseToAnalyzeSequenceFromUserInDotBrecket(@RequestBody InfernalRequestDTO sequence) {
    //     FamilyPredictionDTO[] familyPredictionDTOs = analyzeSequence(sequence);
    //     ChainDTO[] moleculeSelected = getMoleculesByFamily(familyPredictionDTOs[]);
    //     return null;
    // }

    @PostMapping("/analyze")
    public FamilyPredictionDTO[] analyzeSequence(@RequestBody InfernalRequestDTO sequence) {
        return predictionService.analyzeSequence(sequence);
    }

    @GetMapping("/get_molecules_by_rfam/{id}")
    public ResponseToGetMoleculesByRfam[] getMoleculesByFamily(@PathVariable("id") String rfam) {
        return predictionService.getMoleculesByFamily(rfam);
    }

    @GetMapping("/get_data_by_rfam_and_chain/{rfam}/{chain}")
    public ResponseToGetDataByRfamAndChain getDataByRfamAndChain(@PathVariable String rfam, @PathVariable String chain) {
        return predictionService.getDataByRfamAndChain(rfam, chain);
    }


}
