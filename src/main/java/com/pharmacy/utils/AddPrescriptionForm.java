package com.pharmacy.utils;

import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AddPrescriptionForm extends JFrame {
    private final JTextField patientIdField;
    private final JTextField patientNameField;
    private final JTextField doctorIdField;
    private final JTextField doctorNameField;
    private final JTextField diagnosisField;
    private final JList<AddDrugResponse> drugList;
    private final JButton submitButton;
    private final JButton cancelButton;

    public AddPrescriptionForm(List<AddDrugResponse> options, Consumer<AddPrescriptionRequest> onSubmit) {
        setTitle("Add Prescription");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        patientIdField = new JTextField();
        patientNameField = new JTextField();
        doctorIdField = new JTextField();
        doctorNameField = new JTextField();
        diagnosisField = new JTextField();

        formPanel.add(new JLabel("Patient ID:"));
        formPanel.add(patientIdField);
        formPanel.add(new JLabel("Patient Name:"));
        formPanel.add(patientNameField);
        formPanel.add(new JLabel("Doctor ID:"));
        formPanel.add(doctorIdField);
        formPanel.add(new JLabel("Doctor Name:"));
        formPanel.add(doctorNameField);
        formPanel.add(new JLabel("Diagnosis:"));
        formPanel.add(diagnosisField);

        DefaultListModel<AddDrugResponse> model = new DefaultListModel<>();
        for (AddDrugResponse d : options) {
            model.addElement(d);
        }
        drugList = new JList<>(model);
        drugList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane drugScrollPane = new JScrollPane(drugList);
        drugScrollPane.setBorder(BorderFactory.createTitledBorder("Select Drugs"));

        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            try {
                AddPrescriptionRequest req = new AddPrescriptionRequest();
                req.setPatientId(Integer.parseInt(patientIdField.getText().trim()));
                req.setPatientName(patientNameField.getText().trim());
                req.setDoctorId(Integer.parseInt(doctorIdField.getText().trim()));
                req.setDoctorName(doctorNameField.getText().trim());
                req.setDiagnosis(diagnosisField.getText().trim());

                List<AddDrugResponse> selectedDrugs = drugList.getSelectedValuesList();
                List<AddPrescriptionDrugRequest> selectDrugRequests = selectedDrugs.stream()
                        .map(d-> {
                            AddPrescriptionDrugRequest request = new AddPrescriptionDrugRequest();
                            request.setDrugId(d.getDrugId());
                            request.setQuantityPrescribed(1);
                        return request;
                        })
                                .collect(Collectors.toList());
                req.setDrugsPrescribed(selectDrugRequests);

                onSubmit.accept(req);

                JOptionPane.showMessageDialog(this, "Prescription added successfully");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);


        add(formPanel, BorderLayout.NORTH);
        add(drugScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


}
