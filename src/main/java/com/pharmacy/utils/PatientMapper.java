package com.pharmacy.utils;

import com.pharmacy.data.models.Gender;
import com.pharmacy.data.models.Patient;
import com.pharmacy.dtos.requests.AddPatientRequest;
import com.pharmacy.dtos.responses.AddPatientResponse;

public class PatientMapper {

    public static AddPatientResponse toResonse(Patient patient) {
        AddPatientResponse response = new AddPatientResponse();
        response.setPatientId(patient.getPatientId());
        response.setPatientName(patient.getName());
        response.setAge(patient.getAge());
        response.setGender(patient.getGender().toString());
        response.setContactInfo(patient.getContactInfo());
        response.setMedicalCondition(patient.getMedicalCondition());
        response.setDateCreated(patient.getDateCreated());

        return response;
    }

    public static Patient toEntity(AddPatientRequest patient) {
        Patient entity = new Patient();
        entity.setName(patient.getName());
        entity.setAge(patient.getAge());
        entity.setGender(Gender.valueOf(patient.getGender()));
        entity.setContactInfo(patient.getContactInfo());
        entity.setMedicalCondition(patient.getMedicalCondition());
        return entity;
    }
}
