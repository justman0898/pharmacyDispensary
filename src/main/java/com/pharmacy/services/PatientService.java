package com.pharmacy.services;

import com.pharmacy.data.models.Patient;
import com.pharmacy.dtos.requests.AddPatientRequest;
import com.pharmacy.dtos.responses.AddPatientResponse;

import java.util.ArrayList;
import java.util.List;

public interface PatientService {

    AddPatientResponse addPatient(AddPatientRequest addPatientRequest);
    void deletePatient(int patientId);
    AddPatientResponse getPatient(int patientId);
    List<Patient> patient = new ArrayList<Patient>();





}
