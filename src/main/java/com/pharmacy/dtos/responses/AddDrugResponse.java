package com.pharmacy.dtos.responses;


import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class AddDrugResponse {
    private int drugId;
    private String drugName;
    private String drugCategory;
    private String drugType;
    private java.sql.Date expiryDate;
    private java.sql.Date dateCreated = Date.valueOf(LocalDate.now());
    private java.sql.Date manufactureDate;
    private long quantity;


    @Override
    public String toString() {
        return getDrugId() +" | "+ getDrugName()+" | "+ getDrugType()+" | "+ getDrugCategory()+" | "+ getExpiryDate()+" | "+ getManufactureDate()+" | ";
    }
}

