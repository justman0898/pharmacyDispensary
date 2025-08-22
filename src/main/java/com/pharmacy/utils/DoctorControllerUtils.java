package com.pharmacy.utils;

import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;
import com.pharmacy.services.DoctorService;
import com.pharmacy.services.DoctorServiceImpl;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class DoctorControllerUtils {
    DoctorService doctorService = new DoctorServiceImpl();

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

        AddPrescriptionRequest req = new AddPrescriptionRequest();
        try {
            req.setPatientId(Integer.parseInt(Objects.requireNonNull(input("Please input patient id: "))));
            req.setPatientName(input("Please input patient name: "));
            req.setDoctorId(Integer.parseInt(Objects.requireNonNull(input("Please input doctor id: "))));
            req.setDoctorName(input("Please input doctor name: "));
            req.setDiagnosis(input("Please input diagnosis: "));
            List<AddDrugResponse> drugChoices = doctorService.drugList();
            ButtonUtils form = new ButtonUtils(drugChoices);
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    List<AddPrescriptionDrugRequest> prescribedDrugs = form.getSelectedItems();
                    req.setDrugsPrescribed(prescribedDrugs);
                    doctorService.createPrescription(req);
                }
            });
        }catch (Exception e){
            print(e.getMessage());
        }
    }

    public  void viewAllPrescriptions(){
        List<AddPrescriptionResponse> prescriptions =  doctorService.viewPrescriptions();
        new PrescriptionViewer(prescriptions);
    }







}
