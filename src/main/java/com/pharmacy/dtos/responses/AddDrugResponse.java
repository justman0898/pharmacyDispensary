package com.pharmacy.dtos.responses;

public class AddDrugResponse {
    private int drugId;
    private String drugName;

    public int getDrugId() { return drugId;}

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}

