package com.pharmacy.dtos.responses;

import java.time.LocalDate;

public class AvailableDrugResponse {
    private String name;
    private int quantity;
    private boolean isExpired;

    public boolean isExpired(LocalDate expiryDate) {
        return isExpired;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }
}
