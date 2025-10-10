package com.henrique.biograph.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.biograph.DTOs.FamilyPredictionDTO;
import com.henrique.biograph.DTOs.InfernalRequestDTO;
import com.henrique.biograph.Service.PredictionService;

@RestController
public class SequenceController {

    private final PredictionService predictionService;

    @Autowired
    public SequenceController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/analyze")
    public FamilyPredictionDTO[] analyzeSequence(@RequestBody InfernalRequestDTO sequence) {
        return predictionService.analyzeSequence(sequence);
    }
}
