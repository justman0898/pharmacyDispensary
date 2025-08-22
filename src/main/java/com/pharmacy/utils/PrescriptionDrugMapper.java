package com.pharmacy.utils;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

public class PrescriptionDrugMapper {

    public static Drug toEntity(AddPrescriptionDrugRequest req) {
        Drug drug = new Drug();
        drug.setDrugId(req.getDrugId());
        drug.setQuantity(req.getQuantityPrescribed());
        return drug;
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
