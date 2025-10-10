package com.henrique.biograph.Service;

import com.henrique.biograph.DTOs.FamilyPredictionDTO;
import com.henrique.biograph.DTOs.InfernalRequestDTO;

public interface PredictionService {
    
    FamilyPredictionDTO[] analyzeSequence(InfernalRequestDTO sequence);
}
