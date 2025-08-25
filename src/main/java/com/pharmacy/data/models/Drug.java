package com.pharmacy.data.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Setter
public class Drug {

    @Getter
    private int drugId;
    @Getter
    private String drugName;
    private DrugCategory drugCategory;
    private DrugType drugtype;
    @Getter
    private java.sql.Date expiryDate;
    @Getter
    private java.sql.Date dateCreated = Date.valueOf(LocalDate.now());
    @Getter
    private java.sql.Date manufactureDate;
    @Getter
    private long quantity;

    public DrugCategory getDrugCategory() {
        return drugCategory != null ? drugCategory : DrugCategory.UNKNOWN;
    }

    public DrugType getDrugtype() {
        return drugtype != null ? drugtype : DrugType.UNKNOWN;
    }

    @Override
    public String toString() {
        return "Drug Id: " + getDrugId()+"\n"+getDrugName()+"\nCategory: "+getDrugCategory()+"\nType: "+getDrugtype()+"\nExpiry Date: "+getExpiryDate()+"\nDate Added To Record: "+ getDateCreated();
    }

}
