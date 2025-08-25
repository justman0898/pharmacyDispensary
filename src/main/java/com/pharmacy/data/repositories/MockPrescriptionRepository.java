package com.pharmacy.data.repositories;

import com.pharmacy.data.models.Prescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockPrescriptionRepository implements PrescriptionRepository {
    private List<Prescription> store = new ArrayList<>();
    private int counter = 1;

    @Override
    public Prescription save(Prescription prescription) {
        if (prescription.getPrescriptionId() == 0) {
            prescription.setPrescriptionId(counter++);
        }

        Prescription toRemove = null;
        for (Prescription p : store) {
            if (p.getPrescriptionId() == prescription.getPrescriptionId()) {
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            store.remove(toRemove);
        }

        store.add(prescription);
        return prescription;
    }

    @Override
    public Optional<Prescription> findById(int id) {
        for (Prescription p : store) {
            if (p.getPrescriptionId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public int deleteById(int id) {
        Prescription toRemove = null;
        for (Prescription p : store) {
            if (p.getPrescriptionId() == id) {
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            store.remove(toRemove);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Prescription> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public void deleteAll() {
        store.clear();
    }


    @Override
    public List<Prescription> findPrescriptionByDocId(int docId) {
        return List.of();
    }

    @Override
    public List<Prescription> findUnResolvedPrescriptions() {
        return List.of();
    }
}
