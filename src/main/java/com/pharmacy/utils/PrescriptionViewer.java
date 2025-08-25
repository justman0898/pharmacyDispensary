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
        setSize(500, 250);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        showPrescriptionList();
        setVisible(true);
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
        pack();
    }

    private void showPrescriptionDetails(AddPrescriptionResponse prescription) {
        getContentPane().removeAll();

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout(10, 10));

        JTextArea textArea = new JTextArea(prescription.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);


        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> {
            getContentPane().removeAll();
            showPrescriptionList();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeBtn);
        detailsPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(detailsPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 200));
        pack();
        revalidate();
        repaint();
    }
}
