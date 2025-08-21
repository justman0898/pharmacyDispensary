package com.pharmacy.services;

import com.pharmacy.data.repositories.MockPatientRepository;
import com.pharmacy.data.repositories.PatientRepository;
import com.pharmacy.dtos.requests.AddPatientRequest;
import com.pharmacy.dtos.responses.AddPatientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientServiceImplTest {
    private PatientService  patientService;
    private MockPatientRepository patientRepository;

    @BeforeEach
    public void setup() {
        patientRepository = new MockPatientRepository();
        patientService = new PatientServiceImpl(patientRepository);

    }

    @Test
    void addPatient() {
        AddPatientRequest addPatientRequest = new AddPatientRequest();
        addPatientRequest.setName("John Doe");
        addPatientRequest.setGender("MALE");
        AddPatientResponse response =  patientService.addPatient(addPatientRequest);
        assertTrue(patientRepository.wasSaveCalled());

    }

    @Test
    void deletePatient() {
        AddPatientRequest addPatientRequest = new AddPatientRequest();
        addPatientRequest.setName("John Doe");
        addPatientRequest.setGender("MALE");
        addPatientRequest.setPatientId(1);
        AddPatientResponse response = patientService.addPatient(addPatientRequest);

        assertTrue(patientRepository.wasSaveCalled());
        patientService.deletePatient(response.getPatientId());
        assertTrue(patientRepository.wasDeletedCalled());


    }

}