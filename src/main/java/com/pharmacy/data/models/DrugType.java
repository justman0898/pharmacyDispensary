package com.pharmacy.data.models;

public enum DrugType {

    SYRUP(50), CAPSULE(11), INJECTION(20),
    IV(10), TABLET(11), UNKNOWN(-1),;

    private int amount;

    DrugType(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
