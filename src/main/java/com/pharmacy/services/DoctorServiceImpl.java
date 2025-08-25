package com.pharmacy.services;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.DrugRepository;
import com.pharmacy.data.repositories.PrescriptionRepository;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;
import com.pharmacy.utils.Mapper;
import com.pharmacy.utils.PrescriptionDrugMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorServiceImpl implements DoctorService {
    private PrescriptionRepository prescriptionRepository;
    private DrugRepository drugRepository;

    public DoctorServiceImpl(PrescriptionRepository prescriptionRepository, DrugRepository drugRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.drugRepository = drugRepository;
    }

    @Override
    public AddPrescriptionResponse createPrescription(AddPrescriptionRequest request) {
        Prescription prescription = PrescriptionDrugMapper.toPrescriptionEntity(request);

        return PrescriptionDrugMapper.toResponse(prescriptionRepository.save(prescription));
    }

    @Override
    public List<AddPrescriptionResponse> viewPrescriptions(int id) {
        List<Prescription> doctorsPrescription = prescriptionRepository.findPrescriptionByDocId(id);
        doctorsPrescription.forEach(prescription -> {
            prescription.setDrugsPrescribed(getCompleteDrugs(prescription));
        });

        return doctorsPrescription.stream()
                .map((PrescriptionDrugMapper::toResponse))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddDrugResponse> drugList() {
        List<Drug> drugs = drugRepository.findAll();
        return drugs.stream().map(Mapper::convertToResponse).collect(Collectors.toList());
    }

    public List<Drug> getCompleteDrugs(Prescription savedPrescription) {
        List<Drug> drugs = savedPrescription.getDrugsPrescribed();
        List<Drug> completedDrugs =  drugs.stream()
                .map(drug -> {
                    Drug foundDrug =  drugRepository.findById(drug.getDrugId()).get();
                    foundDrug.setQuantity(drug.getQuantity());
                    return foundDrug;
                }).collect(Collectors.toList());
        savedPrescription.setDrugsPrescribed(completedDrugs);
        return completedDrugs;
    }
}
