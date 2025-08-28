package com.pharmacy.utils;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PrescriptionDrugMapper {

    public static Drug toEntity(AddPrescriptionDrugRequest req) {
        Drug drug = new Drug();
        drug.setDrugId(req.getDrugId());
        drug.setQuantity(req.getQuantityPrescribed());
        return drug;
    }

    public static Prescription toPrescriptionEntity(AddPrescriptionRequest request) {
        Prescription prescription = new Prescription();
        prescription.setPatientId(request.getPatientId());
        prescription.setPatientName(request.getPatientName());
        prescription.setDoctorId(request.getDoctorId());
        prescription.setDoctorName(request.getDoctorName());
        prescription.setDiagnosis(request.getDiagnosis());
        List<AddPrescriptionDrugRequest> prescribedDrugs = request.getDrugsPrescribed();
        List<Drug> drugs = prescribedDrugs.stream().map(PrescriptionDrugMapper::toEntity).collect(Collectors.toList());
        prescription.setDrugsPrescribed(drugs);
        return prescription;
    }

    public static AddPrescriptionResponse toResponse(Prescription prescription) {
        AddPrescriptionResponse response = new AddPrescriptionResponse();
        response.setPrescriptionId(prescription.getPrescriptionId());
        response.setPatientId(prescription.getPatientId());
        response.setPatientName(prescription.getPatientName());
        response.setDoctorId(prescription.getDoctorId());
        response.setDoctorName(prescription.getDoctorName());
        response.setDiagnosis(prescription.getDiagnosis());
        response.setDateCreated(prescription.getDateCreated());
        response.setResolved(prescription.isResolved());
        response.setDrugsPrescribed(prescription.getDrugsPrescribed());
        return response;
    }


}
