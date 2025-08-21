package com.pharmacy.services;

import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

public interface DoctorService {

    AddPrescriptionResponse createPrescription(AddPrescriptionRequest request);
    void viewPrescriptions();

}
