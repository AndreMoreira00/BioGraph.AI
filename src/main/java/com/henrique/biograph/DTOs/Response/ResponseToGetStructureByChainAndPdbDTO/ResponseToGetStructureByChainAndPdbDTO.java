package com.henrique.biograph.DTOs.Response.ResponseToGetStructureByChainAndPdbDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseToGetStructureByChainAndPdbDTO {
    @JsonProperty("context_molecules")
    private List<ContextMoleculeDTO> contextMolecules;
    @JsonProperty("target_molecule")
    private TargetMoleculeDTO targetMolecule;

    public ResponseToGetStructureByChainAndPdbDTO() {
    }

    public List<ContextMoleculeDTO> getContextMolecules() {
        return contextMolecules;
    }

    public void setContextMolecules(List<ContextMoleculeDTO> contextMolecules) {
        this.contextMolecules = contextMolecules;
    }

    public TargetMoleculeDTO getTargetMolecule() {
        return targetMolecule;
    }

    public void setTargetMolecule(TargetMoleculeDTO targetMolecule) {
        this.targetMolecule = targetMolecule;
    }

}
