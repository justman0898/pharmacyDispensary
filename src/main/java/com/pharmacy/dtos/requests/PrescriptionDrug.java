package com.pharmacy.dtos.requests;

import lombok.Data;

@Data
public class PrescriptionDrug {
    private int drugId;
    private long quantityPrescribed;
}
