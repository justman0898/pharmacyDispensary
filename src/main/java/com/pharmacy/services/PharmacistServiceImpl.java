package com.pharmacy.services;

import com.pharmacy.data.models.Pharmacist;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.PrescriptionRepository;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;
import com.pharmacy.exceptions.InvalidDetailsException;
import com.pharmacy.exceptions.InvalidPrescriptionException;
import com.pharmacy.utils.PrescriptionDrugMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacistServiceImpl implements PharmacistService {
    private PrescriptionRepository prescriptionRepository;
    private List<Pharmacist> pharmacists;
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
                .orElseThrow(() -> new InvalidPrescriptionException("Prescription not found"));
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
    public List<AddPrescriptionResponse> viewDispensedHistory() {
        return prescriptionRepository.findAll().stream()
                .filter(prescription -> !prescription.isResolved())
                .map(PrescriptionDrugMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddPrescriptionResponse> viewUnresolvedHistory() {
        return prescriptionRepository.findUnresolvedPrescriptions().stream()
                .map(PrescriptionDrugMapper::toResponse)
                .collect(Collectors.toList());
    }
}
