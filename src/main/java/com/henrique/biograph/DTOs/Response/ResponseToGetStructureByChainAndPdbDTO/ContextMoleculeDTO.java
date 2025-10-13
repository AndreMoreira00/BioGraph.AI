package com.henrique.biograph.DTOs.Response.ResponseToGetStructureByChainAndPdbDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContextMoleculeDTO {
    private List<AtomDTO> atoms;

    @JsonProperty("chain_id")
    private String chainId;

    public ContextMoleculeDTO() {
    }

    public List<AtomDTO> getAtoms() {
        return atoms;
    }

    public void setAtoms(List<AtomDTO> atoms) {
        this.atoms = atoms;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

}
