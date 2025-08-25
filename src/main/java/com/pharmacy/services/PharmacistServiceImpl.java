package com.pharmacy.services;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Pharmacist;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.DrugRepository;
import com.pharmacy.data.repositories.DrugRepositoryImpl;
import com.pharmacy.data.repositories.PrescriptionRepository;
import com.pharmacy.data.repositories.PrescriptionRepositoryImpl;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;
import com.pharmacy.exceptions.InvalidDetailsException;
import com.pharmacy.exceptions.InvalidDrugQuantityException;
import com.pharmacy.utils.PrescriptionDrugMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacistServiceImpl implements PharmacistService {
    private PrescriptionRepository prescriptionRepository;
    private List<Pharmacist> pharmacists = new ArrayList<>();
    private  List<Prescription> dispensedPrescriptions = new ArrayList<>();
    private boolean loggedIn = true;
    private int currentPharmacistId;
    private DrugRepository drugRepository = new DrugRepositoryImpl();
    private DoctorServiceImpl doctorService ;

    public PharmacistServiceImpl(PrescriptionRepository prescriptionRepository, List<Pharmacist> pharmacists) {
        this.prescriptionRepository = prescriptionRepository;
        this.pharmacists = pharmacists;
        doctorService = new DoctorServiceImpl(prescriptionRepository, drugRepository);
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
        //prescription.setPrescriptionId(currentPharmacistId);
        prescriptionRepository.save(prescription);

        //dispensedPrescriptions.add(prescription);
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

    @Override
    public List<AddPrescriptionResponse> allPrescriptions() {
        List<Prescription> allPrescriptions = prescriptionRepository.findAll();
        List<Prescription> completedPrescription = allPrescriptions.stream().map(prescription -> {
            Prescription newPrescription = new Prescription();
            List<Drug> completedDrug = doctorService.getCompleteDrugs(prescription);
            newPrescription.setDrugsPrescribed(completedDrug);
            newPrescription.setPrescriptionId(prescription.getPrescriptionId());
            newPrescription.setDiagnosis(prescription.getDiagnosis());
            newPrescription.setResolved(prescription.isResolved());

            return newPrescription;
        }).collect(Collectors.toList());

        return allPrescriptions.stream()
                .map(PrescriptionDrugMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<AddPrescriptionResponse> allUnDispensedPrescriptions() {
        List<Prescription> allPrescriptions = prescriptionRepository.findUnResolvedPrescriptions();
        List<Prescription> completedPrescription = allPrescriptions.stream().map(prescription -> {
            Prescription newPrescription = new Prescription();
            List<Drug> completedDrug = doctorService.getCompleteDrugs(prescription);
            newPrescription.setDrugsPrescribed(completedDrug);
            newPrescription.setPrescriptionId(prescription.getPrescriptionId());
            newPrescription.setDiagnosis(prescription.getDiagnosis());
            newPrescription.setResolved(prescription.isResolved());
            newPrescription.setPatientName(prescription.getPatientName());
            newPrescription.setDoctorId(prescription.getDoctorId());
            newPrescription.setDoctorName(prescription.getDoctorName());
            return newPrescription;
        }).collect(Collectors.toList());
        return completedPrescription.stream()
                .map(PrescriptionDrugMapper::toResponse).collect(Collectors.toList());
    }


}
