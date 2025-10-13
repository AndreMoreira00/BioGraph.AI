package com.henrique.biograph.DTOs.Response.ResponseToGetStructureByChainAndPdbDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TargetMoleculeDTO {
    @JsonProperty("chain_id")
    private String chainId;
    private List<ResidueDTO> residues;

    public TargetMoleculeDTO() {
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public List<ResidueDTO> getResidues() {
        return residues;
    }

    public void setResidues(List<ResidueDTO> residues) {
        this.residues = residues;
    }

}
