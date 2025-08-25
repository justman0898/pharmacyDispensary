package com.pharmacy.utils;

import com.pharmacy.dtos.responses.AddPrescriptionResponse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UnDispensedPrescriptionsFrame extends JFrame {
    private JTable table;
    private JButton submitButton;
    private List<AddPrescriptionResponse> prescriptions;

    public UnDispensedPrescriptionsFrame(List<AddPrescriptionResponse> prescriptions,
                                         Consumer<List<AddPrescriptionResponse>> onSubmit) {
        this.prescriptions = prescriptions;

        setTitle("Un-Dispensed Prescriptions");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table Model
        String[] columns = {"Select", "Prescription ID", "Patient", "Doctor", "Diagnosis", "Resolved"};
        Object[][] data = new Object[prescriptions.size()][6];
        for (int i = 0; i < prescriptions.size(); i++) {
            AddPrescriptionResponse p = prescriptions.get(i);
            data[i][0] = false;
            data[i][1] = p.getPrescriptionId();
            data[i][2] = p.getPatientName();
            data[i][3] = p.getDoctorName();
            data[i][4] = p.getDiagnosis();
            data[i][5] = p.isResolved();
        }

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        submitButton = new JButton("Mark as Dispensed");
        submitButton.addActionListener(e -> {
            List<AddPrescriptionResponse> selected = new ArrayList<>();
            for (int i = 0; i < table.getRowCount(); i++) {
                Boolean checked = (Boolean) table.getValueAt(i, 0);
                if (checked != null && checked) {
                    selected.add(prescriptions.get(i));
                }
            }

            onSubmit.accept(selected);
            dispose();
        });

        add(submitButton, BorderLayout.SOUTH);
    }

}
