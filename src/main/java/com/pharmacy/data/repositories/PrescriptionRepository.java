package com.pharmacy.data.repositories;

import com.pharmacy.data.models.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    Prescription save(Prescription prescription);
    Optional<Prescription> findById(int id);
    int deleteById(int id);
    List<Prescription> findAll();
    void deleteAll();
    List<Prescription> findUnresolvedPrescriptions();
}

