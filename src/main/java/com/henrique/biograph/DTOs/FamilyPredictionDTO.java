package com.henrique.biograph.DTOs;

public class FamilyPredictionDTO {
    private double bias;
    private double bit_score;
    private double e_value;
    private String rfam_acc;
    private String target_name;    

    public FamilyPredictionDTO() {
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getBit_score() {
        return bit_score;
    }

    public void setBit_score(double bit_score) {
        this.bit_score = bit_score;
    }

    public double getE_value() {
        return e_value;
    }

    public void setE_value(double e_value) {
        this.e_value = e_value;
    }

    public String getRfam_acc() {
        return rfam_acc;
    }

    public void setRfam_acc(String rfam_acc) {
        this.rfam_acc = rfam_acc;
    }

    public String getTarget_name() {
        return target_name;
    }

    public void setTarget_name(String target_name) {
        this.target_name = target_name;
    }

}
