package com.pharmacy.data.repositories;

import com.pharmacy.data.models.Prescription;

import java.util.*;

public class MockPrescriptionRepository implements PrescriptionRepository {
    private final List<Prescription> store = new ArrayList<>();
    private int counter = 1;

    @Override
    public Prescription save(Prescription prescription) {
        if (prescription.getPrescriptionId() == 0) {
            prescription.setPrescriptionId(counter++);
        }
        // Replace if already exists
        store.removeIf(p -> p.getPrescriptionId() == prescription.getPrescriptionId());
        store.add(prescription);
        return prescription;
    }

    @Override
    public Optional<Prescription> findById(int id) {
        return store.stream()
                .filter(p -> p.getPrescriptionId() == id)
                .findFirst();
    }

    @Override
    public int deleteById(int id) {
        boolean removed = store.removeIf(p -> p.getPrescriptionId() == id);
        return removed ? 1 : 0;
    }

    @Override
    public List<Prescription> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public void deleteAll() {
        store.clear();
    }
}
