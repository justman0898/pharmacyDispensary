package com.pharmacy.services;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;
import com.pharmacy.utils.PrescriptionDrugMapper;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Drug> drugs = prescribedDrugs.stream().map(PrescriptionDrugMapper::toEntity).collect(Collectors.toList());
        prescription.setDrugsPrescribed(drugs);
        return
    }

    @Override
    public List<AddPrescriptionResponse> viewPrescriptions() {


    }

    @Override
    public List<AddDrugResponse> drugList() {
        return List.of();
    }
}
