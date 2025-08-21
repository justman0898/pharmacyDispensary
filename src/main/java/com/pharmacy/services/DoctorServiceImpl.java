package com.pharmacy.services;

import com.pharmacy.data.models.Prescription;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

public class DoctorServiceImpl implements DoctorService {
    @Override
    public AddPrescriptionResponse createPrescription(AddPrescriptionRequest request) {
        Prescription prescription = new Prescription();

        return null;
    }

    @Override
    public void viewPrescriptions() {

    }
}
