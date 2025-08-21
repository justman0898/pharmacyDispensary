package com.pharmacy.data.repositories;

import com.pharmacy.data.models.Prescription;

import java.util.List;
import java.util.Optional;

public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    private
    @Override
    public Prescription save(Prescription prescription) {
        return null;
    }

    @Override
    public Optional<Prescription> findById(int id) {
        return Optional.empty();
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public List<Prescription> findAll() {
        return List.of();
    }

    @Override
    public void deleteAll() {

    }
}
