package com.pharmacy.utils;

import com.pharmacy.data.models.Patient;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

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

    public static AddPrescriptionRequest toEntity(Prescription prescribe) {
        AddPrescriptionRequest entity = new AddPrescriptionRequest();

       entity.setPatientId(prescribe.getPatientId());
       entity.setPatientName(prescribe.getPatientName());
       entity.setDiagnosis(prescribe.getDiagnosis());
       entity.setDoctorId(prescribe.getDoctorId());
       entity.setDiagnosis(prescribe.getDiagnosis());
       entity.setDrugsPrescribed(prescribe.getDrugsPrescribed());

       return entity;

    }


}
