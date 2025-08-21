package com.pharmacy.exceptions;

public class DrugNotFoundException extends RuntimeException {
    public DrugNotFoundException (int id){
        super("drug with id "+id+" not found");
    }

}
