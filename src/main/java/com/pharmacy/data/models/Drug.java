package com.pharmacy.data.models;

import java.sql.Date;
import java.time.LocalDate;

public class Drug {

    private int drugId;
    private String drugName;
    private DrugCategory drugCategory;
    private DrugType drugtype;
    private java.sql.Date expiryDate;
    private java.sql.Date dateCreated = Date.valueOf(LocalDate.now());
    private java.sql.Date manufactureDate;
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

    public Date getExpiryDate() {
        return expiryDate.toLocalDate();
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDateCreated() {
        return dateCreated.toLocalDate();
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Date getManufactureDate() {
        return manufactureDate.toLocalDate();
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }
}
