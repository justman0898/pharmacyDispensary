package com.pharmacy.controllers;

import com.pharmacy.data.models.Pharmacist;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.PrescriptionRepository;
import com.pharmacy.data.repositories.PrescriptionRepositoryImpl;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;
import com.pharmacy.services.PharmacistService;
import com.pharmacy.services.PharmacistServiceImpl;
import com.pharmacy.utils.PrescriptionDrugMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacistController {
    private PrescriptionRepository prescriptionRepository = new PrescriptionRepositoryImpl();

    private final PharmacistService pharmacistService = new PharmacistServiceImpl(prescriptionRepository, new ArrayList<>() {
    });

    public AddPrescriptionResponse dispensePrescription(int prescriptionId) {
        Prescription prescription = pharmacistService.dispensePrescription(prescriptionId);
        return PrescriptionDrugMapper.toResponse(prescription);
    }

    public List<AddPrescriptionResponse> viewAllPrescriptions() {
        return pharmacistService.allPrescriptions();
    }

    public void verifyPrescription(int prescriptionId) {
        pharmacistService.verifyPrescription(prescriptionId);
    }

    public List<AddPrescriptionResponse> viewSettledPrescription() {
        List<Prescription> prescriptions = pharmacistService.viewDispensedHistory(1);
        return prescriptions.stream()
                .map(PrescriptionDrugMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AddPrescriptionResponse> viewAllUnDispensedPrescription() {
        List<AddPrescriptionResponse> allUndispensed =  pharmacistService.allUnDispensedPrescriptions();
        checkIfAvailable(allUndispensed);
        return allUndispensed;
    }



    private void checkIfAvailable(List<AddPrescriptionResponse> allUndispensed) {
        if(allUndispensed.isEmpty()) {
            throw new RuntimeException("No undispensed prescriptions found");
        }

    }



}
