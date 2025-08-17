package com.pharmacy.dtos.requests;

import com.pharmacy.data.models.DrugCategory;
import com.pharmacy.data.models.DrugType;

import java.sql.Date;
import java.time.LocalDate;

public class AddDrugRequest {

    private String drugName;
    private DrugCategory drugCategory;
    private DrugType drugType;
    private java.sql.Date expiryDate;
    private java.sql.Date dateCreated = Date.valueOf(LocalDate.now());
    private java.sql.Date manufactureDate;
    private long quantity;


    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public DrugType getDrugtype() {
        return drugType;
    }

    public void setDrugType(DrugType drugType) {
        this.drugType = drugType;
    }

    public DrugCategory getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(DrugCategory drugCategory) {
        this.drugCategory = drugCategory;
    }

    public LocalDate getDateCreated() {
        return dateCreated.toLocalDate();
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getExpiryDate() {
        return expiryDate.toLocalDate();
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate.toLocalDate();
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

}

