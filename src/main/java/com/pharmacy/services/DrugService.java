package com.pharmacy.services;

import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;

public interface DrugService {

    AddDrugResponse addDrug(AddDrugRequest addDrugRequest);
    void removeById(int id);
    void removeAll();
    AddDrugResponse findDrugById(int id);

    }

