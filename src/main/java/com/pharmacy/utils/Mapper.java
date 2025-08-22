package com.pharmacy.utils;

import com.pharmacy.data.models.Drug;
import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;

public class Mapper {

    public static Drug convertToDrug(AddDrugRequest addDrugRequest) {
        Drug drug = new Drug();
        drug.setDrugName(addDrugRequest.getDrugName());
        drug.setDrugtype(addDrugRequest.getDrugtype());
        drug.setDrugCategory(addDrugRequest.getDrugCategory());
        drug.setQuantity(addDrugRequest.getQuantity());
        drug.setDateCreated(addDrugRequest.getDateCreated());
        drug.setExpiryDate(addDrugRequest.getExpiryDate());
        return drug;
    }


    public static AddDrugResponse convertToResponse(Drug savedDrug){
        AddDrugResponse addDrugResponse =  new AddDrugResponse();
        addDrugResponse.setDrugId(savedDrug.getDrugId());
        addDrugResponse.setDrugName(savedDrug.getDrugName());
        addDrugResponse.setDrugType(savedDrug.getDrugtype());
        addDrugResponse.setQuantity(savedDrug.getQuantity());
        addDrugResponse.setDrugCategory(savedDrug.getDrugCategory().toString());
        addDrugResponse.setDateCreated(savedDrug.getDateCreated());
        addDrugResponse.setExpiryDate(savedDrug.getExpiryDate());
        return addDrugResponse;
    }

}
