package com.pharmacy.services;

import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

import java.util.List;

public interface DoctorService {

    AddPrescriptionResponse createPrescription(AddPrescriptionRequest request);
    List<AddPrescriptionResponse> viewPrescriptions(int id);
    List<AddDrugResponse> drugList();

}
