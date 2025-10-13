package com.henrique.biograph.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseToGetDataByRfamAndChainDTO {
    
    @JsonProperty("bit_score")
    private Double bitScore;

    @JsonProperty("chain")
    private String chain;
    
    @JsonProperty("cm_end")
    private Double cmEnd;
    
    @JsonProperty("cm_start")
    private Double cmStart;
    
    @JsonProperty("evalue_score")
    private Double eValueScore;
    
    @JsonProperty("hex_colour")
    private String hexColor;
    
    @JsonProperty("pdb_end")
    private Integer pdbEnd;
    
    @JsonProperty("pdb_id")
    private String pdbId;
    
    @JsonProperty("pdb_start")
    private Integer pdbStart;
    
    @JsonProperty("rfam_acc")
    private String rfamAcc;

    public ResponseToGetDataByRfamAndChainDTO() {
    }

    public Double getBitScore() {
        return bitScore;
    }

    public void setBitScore(Double bitScore) {
        this.bitScore = bitScore;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public Double getCmEnd() {
        return cmEnd;
    }

    public void setCmEnd(Double cmEnd) {
        this.cmEnd = cmEnd;
    }

    public Double getCmStart() {
        return cmStart;
    }

    public void setCmStart(Double cmStart) {
        this.cmStart = cmStart;
    }

    public Double geteValueScore() {
        return eValueScore;
    }

    public void seteValueScore(Double eValueScore) {
        this.eValueScore = eValueScore;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public Integer getPdbEnd() {
        return pdbEnd;
    }

    public void setPdbEnd(Integer pdbEnd) {
        this.pdbEnd = pdbEnd;
    }

    public String getPdbId() {
        return pdbId;
    }

    public void setPdbId(String pdbId) {
        this.pdbId = pdbId;
    }

    public Integer getPdbStart() {
        return pdbStart;
    }

    public void setPdbStart(Integer pdbStart) {
        this.pdbStart = pdbStart;
    }

    public String getRfamAcc() {
        return rfamAcc;
    }

    public void setRfamAcc(String rfamAcc) {
        this.rfamAcc = rfamAcc;
    }

    
}

/*
 *  "bit_score": 77.2,
    "chain": "A",
    "cm_end": 119,
    "cm_start": 1,
    "evalue_score": 3.2e-17,
    "hex_colour": "c0af92",
    "pdb_end": 117,
    "pdb_id": "2j28",
    "pdb_start": 2,
    "rfam_acc": "RF00001"
 */