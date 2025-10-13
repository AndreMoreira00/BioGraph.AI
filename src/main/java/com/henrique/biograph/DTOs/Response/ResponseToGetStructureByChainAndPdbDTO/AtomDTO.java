package com.henrique.biograph.DTOs.Response.ResponseToGetStructureByChainAndPdbDTO;

import java.util.List;

public class AtomDTO {
    private List<Double> coordinates;
    private String element;

    public AtomDTO() {
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

}
