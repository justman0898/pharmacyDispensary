package com.pharmacy.dtos.responses;


import java.sql.Date;
import java.time.LocalDate;

public class AddDrugResponse {
    private int drugId;
    private String drugName;
    private String drugCategory;
    private String drugType;
    private java.sql.Date expiryDate;
    private java.sql.Date dateCreated = Date.valueOf(LocalDate.now());
    private java.sql.Date manufactureDate;
    private long quantity;


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

    public String getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(String drugCategory) {
        this.drugCategory = drugCategory;
    }

    public String getDrugtype() {
        return drugType;
    }

    public void setDrugtype(String drugType) {
        this.drugType = drugType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}

