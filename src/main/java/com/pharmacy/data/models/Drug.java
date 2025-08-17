package com.pharmacy.data.models;

import java.time.LocalDate;

public class Drug {

    private int drugId;
    private String drugName;
    private DrugCategory drugCategory;
    private DrugType drugtype;
    private LocalDate expiryDate;
    private LocalDate dateCreated =  LocalDate.now();
    private long quantity;

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public DrugCategory getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(DrugCategory drugCategory) {
        this.drugCategory = drugCategory;
    }

    public DrugType getDrugtype() {
        return drugtype;
    }

    public void setDrugtype(DrugType drugtype) {
        this.drugtype = drugtype;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
