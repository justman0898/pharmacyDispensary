package com.pharmacy.utils;

import com.pharmacy.data.models.Drug;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;

public class PrescriptionDrugMapper {

    public static Drug toEntity(AddPrescriptionDrugRequest req) {
        Drug drug = new Drug();
        drug.setDrugId(req.getDrugId());
        drug.setQuantity(req.getQuantityPrescribed());
        return drug;


    }
}
