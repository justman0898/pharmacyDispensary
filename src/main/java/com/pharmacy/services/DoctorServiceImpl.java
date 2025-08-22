package com.pharmacy.services;

import com.pharmacy.data.models.Prescription;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {


    @Override
    public AddPrescriptionResponse createPrescription(AddPrescriptionRequest request) {
        Prescription prescription = new Prescription();
        prescription.setPatientId(request.getPatientId());
        prescription.setPatientName(request.getPatientName());
        prescription.setDoctorId(request.getDoctorId());
        prescription.setDoctorName(request.getDoctorName());
        prescription.setDiagnosis(request.getDiagnosis());
        List<AddPrescriptionDrugRequest> prescribedDrugs = request.getDrugsPrescribed();

        return null;
    }

    @Override
    public List<AddPrescriptionResponse> viewPrescriptions() {


    }

    @Override
    public List<AddDrugResponse> drugList() {
        return List.of();
    }
}
