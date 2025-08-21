package com.pharmacy.data.repositories;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    Patient save (Patient patient);
    void deleteById(int id);
    void deleteAll();
    Optional<Patient> findById(int id);
    List<Patient> findAll();

}
