package com.pharmacy.services;

import com.pharmacy.data.models.Patient;
import com.pharmacy.data.repositories.PatientRepository;
import com.pharmacy.dtos.requests.AddPatientRequest;
import com.pharmacy.dtos.responses.AddPatientResponse;
import com.pharmacy.utils.PatientMapper;

public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public AddPatientResponse addPatient(AddPatientRequest addPatientRequest) {
        Patient patient = PatientMapper.toEntity(addPatientRequest);
        patientRepository.save(patient);


        return PatientMapper.toResonse(patient) ;
    }

    @Override
    public void deletePatient(int patientId) {

    }

    @Override
    public AddPatientResponse getPatient(int patientId) {
        return null;
    }
}
