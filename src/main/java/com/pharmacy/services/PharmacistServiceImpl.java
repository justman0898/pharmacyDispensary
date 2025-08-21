package com.pharmacy.services;

import com.pharmacy.data.models.Pharmacist;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.PrescriptionRepository;
import com.pharmacy.exceptions.InvalidDetailsException;
import com.pharmacy.exceptions.InvalidDrugQuantityException;

import java.util.ArrayList;
import java.util.List;

public class PharmacistServiceImpl implements PharmacistService {
    private PrescriptionRepository prescriptionRepository;
    private List<Pharmacist> pharmacists = new ArrayList<>();
    private  List<Prescription> dispensedPrescriptions = new ArrayList<>();
    private boolean loggedIn;
    private int currentPharmacistId;

    public PharmacistServiceImpl(PrescriptionRepository prescriptionRepository, List<Pharmacist> pharmacists) {
        this.prescriptionRepository = prescriptionRepository;
        this.pharmacists = pharmacists;
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
        if (!loggedIn) throw new InvalidDetailsException("Pharmacist is not logged in");
        return prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new InvalidDetailsException("Prescription not found"));
    }

    @Override
    public Prescription dispensePrescription(int prescriptionId) {
        Prescription prescription = verifyPrescription(prescriptionId);
        prescription.setResolved(true);
        prescription.setPrescriptionId(currentPharmacistId);
        prescriptionRepository.save(prescription);

        dispensedPrescriptions.add(prescription);
        return prescription;
    }

    @Override
    public List<Prescription> viewDispensedHistory(int pharmacistId) {
        List<Prescription> allPrescriptions = prescriptionRepository.findAll();
        List<Prescription> history = new ArrayList<>();
        for (Prescription prescription : allPrescriptions) {
            if (prescription.isResolved()) history.add(prescription);
        }
        return history;
    }
}
