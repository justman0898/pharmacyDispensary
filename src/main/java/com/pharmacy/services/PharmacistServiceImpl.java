package com.pharmacy.services;

import com.pharmacy.data.models.Pharmacist;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.PrescriptionRepository;
import com.pharmacy.exceptions.InvalidDetailsException;
import com.pharmacy.exceptions.InvalidDrugQuantityException;

import java.util.ArrayList;
import java.util.List;

public class PharmacistServiceImpl implements PharmacistService {
    private final PrescriptionRepository prescriptionRepository;
    private final List<Pharmacist> pharmacists = new ArrayList<>();
    private final List<Prescription> dispensedPrescriptions = new ArrayList<>();
    private boolean loggedIn = false;
    private int currentPharmacistId;

    public PharmacistServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;

        pharmacists.add(new Pharmacist(1, "John Doe", "password123"));
        pharmacists.add(new Pharmacist(2, "Jane Smith", "securePass"));
    }

    @Override
    public boolean login(int pharmacistId, String password) {
        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getPharmacistId() == pharmacistId &&
                    pharmacist.getPassword().equals(password)) {
                loggedIn = true;
                currentPharmacistId = pharmacistId;
                return true;
            }
        }
        return false;
    }

    @Override
    public Prescription verifyPrescription(int prescriptionId) {
        if (!loggedIn) throw new InvalidDetailsException("Pharcist is not logged in");
        return prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new InvalidDetailsException(""));
    }

    @Override
    public Prescription dispensePrescription(int prescriptionId) {
        Prescription prescription = verifyPrescription(prescriptionId);
        prescription.setResolved(true);
        prescriptionRepository.save(prescription);

        dispensedPrescriptions.add(prescription);
        return prescription;
    }

    @Override
    public List<Prescription> viewDispensedHistory(int pharmacistId) {
        List<Prescription> history = new ArrayList<>();
        for (Prescription prescription : dispensedPrescriptions) {
            history.add(prescription);
        }
        return history;
    }
}
