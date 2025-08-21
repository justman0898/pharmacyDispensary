package com.pharmacy.dtos.requests;

public class BuyDrugRequest {
    private String drugName;
    private int quantity;
    private int DrugId;


    public int getDrugId() {
        return DrugId;
    }

    public void setDrugId(int drugId) {
        DrugId = drugId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }




}
