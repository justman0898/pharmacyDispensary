package com.pharmacy.utils;

import com.pharmacy.data.repositories.DrugRepositoryImpl;
import com.pharmacy.data.repositories.PrescriptionRepository;
import com.pharmacy.data.repositories.PrescriptionRepositoryImpl;

import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;
import com.pharmacy.services.DoctorService;
import com.pharmacy.services.DoctorServiceImpl;

import javax.swing.*;
import java.util.List;
import java.util.Objects;


public class DoctorControllerUtils {
    private final PrescriptionRepository prescriptionRepository = new PrescriptionRepositoryImpl();
    private final DrugRepositoryImpl drugRepository = new DrugRepositoryImpl();
    private final DoctorService doctorService = new DoctorServiceImpl(prescriptionRepository, drugRepository);

    public static void print(String msg) {

        JOptionPane.showMessageDialog(null, msg);
    }

    public static String input(String msg) {
        String value;
        do {
            value = JOptionPane.showInputDialog(null, msg);
            if (value == null) return null; // user pressed Cancel
            value = value.trim();
            if (value.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Input cannot be empty or spaces only!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (value.isEmpty());

        return value;    }

    public void prescribeDrugs(){
        try {
            List<AddDrugResponse> options = doctorService.drugList();
            new AddPrescriptionForm(options, doctorService::createPrescription);
        }catch (Exception e) {
            print(e.getMessage());
        }
    }

    public  void viewAllPrescriptions(){
        try {
            List<AddPrescriptionResponse> prescriptions = doctorService.viewPrescriptions(Integer.parseInt(Objects.requireNonNull(input("Please Enter your Id"))));

            new PrescriptionViewer(prescriptions);
       } catch (Exception e) {
            print(e.getMessage());
           new DoctorsMainMenu();
        }
    }







}
