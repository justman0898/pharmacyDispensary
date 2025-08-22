package com.pharmacy.utils;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Patient;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {

    public static AddPrescriptionResponse toResonse(Prescription prescribe) {
        AddPrescriptionResponse response = new AddPrescriptionResponse();
        response.setPrescriptionId(prescribe.getPrescriptionId());
        response.setPatientId(prescribe.getPatientId());
        response.setPatientName(prescribe.getPatientName());
        response.setDiagnosis(prescribe.getDiagnosis());
        response.setDateCreated(prescribe.getDateCreated());
        response.setDoctorId(prescribe.getDoctorId());
        response.setDoctorName(prescribe.getDoctorName());
        response.setPrescriptionId(prescribe.getPrescriptionId());
        response.setResolved(prescribe.isResolved());

        return response;

    }

    public static Prescription toEntity(AddPrescriptionRequest prescribe) {
        Prescription prescription = new Prescription();
        prescription.setPatientId(prescribe.getPatientId());
        prescription.setDoctorId(prescribe.getDoctorId());
        prescription.setPatientName(prescribe.getPatientName());
        prescription.setDiagnosis(prescribe.getDiagnosis());
        prescription.setDoctorName(prescribe.getDoctorName());
        List<AddPrescriptionDrugRequest> drugRequests = prescribe.getDrugsPrescribed();
        List<Drug> drugs = drugRequests.stream()
                .map(PrescriptionDrugMapper::toEntity)
                .collect(Collectors.toList());
        prescription.setDrugsPrescribed(drugs);
        return prescription;

    }


}
