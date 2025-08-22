package com.pharmacy.data.models;

public class Pharmacist {
    private int pharmacistId;
    private String name;
    private String password;

    public Pharmacist(int pharmacistId, String name, String password) {
        this.pharmacistId = pharmacistId;
        this.name = name;
        this.password = password;
    }

    public int getPharmacistId() {
        return pharmacistId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
