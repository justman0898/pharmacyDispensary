package com.pharmacy.services;

import com.pharmacy.data.models.Prescription;

import java.util.List;

public interface PharmacistService {
    boolean login(int pharmacistId, String password);
    Prescription verifyPrescription(int prescriptionId);
    Prescription dispensePrescription(int prescriptionId);
    List<Prescription> viewDispensedHistory(int pharmacistId);
}
