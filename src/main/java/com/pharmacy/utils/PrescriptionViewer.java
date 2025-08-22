package com.pharmacy.utils;

import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PrescriptionViewer extends JFrame {

    private List<AddPrescriptionResponse> prescriptions;

    public PrescriptionViewer(List<AddPrescriptionResponse> prescriptions) {
        this.prescriptions = prescriptions;

        setTitle("Prescription List");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showPrescriptionList();
    }

    private void showPrescriptionList() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        for (AddPrescriptionResponse p : prescriptions) {
            JButton btn = new JButton(p.getPatientName()+" Prescription" );
            btn.addActionListener((ActionEvent e) -> showPrescriptionDetails(p));
            panel.add(btn);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
        setVisible(true);
    }

    private void showPrescriptionDetails(AddPrescriptionResponse prescription) {
        getContentPane().removeAll();

        JTextArea textArea = new JTextArea(prescription.toString());
        textArea.setEditable(false);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> showPrescriptionList());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeBtn);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
