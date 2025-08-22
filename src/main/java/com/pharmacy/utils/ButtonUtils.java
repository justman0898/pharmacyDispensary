package com.pharmacy.utils;

import com.pharmacy.data.models.Drug;
import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionDrugRequest;
import com.pharmacy.dtos.requests.AddPrescriptionRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonUtils extends JFrame {

    private List<JCheckBox> checkBoxes = new ArrayList<>();
    private List<AddPrescriptionDrugRequest> selectedItems = new ArrayList<>();
    private List<AddDrugResponse> drugs;

    public ButtonUtils(List<AddDrugResponse> drugs) {
        this.drugs = drugs;

        setTitle("Select Drugs");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));

        for (AddDrugResponse drug : drugs) {
            JCheckBox checkBox = new JCheckBox(drug.getDrugName());
            checkBoxes.add(checkBox);
            panel.add(checkBox);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            selectedItems.clear();
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    AddDrugResponse response = drugs.get(i);

                    AddPrescriptionDrugRequest req = new AddPrescriptionDrugRequest();
                    req.setDrugId(response.getDrugId());
                    req.setQuantityPrescribed(1);

                    selectedItems.add(req);
                }
            }
            dispose();
        });

        panel.add(submitButton);
        add(new JScrollPane(panel));
        setVisible(true);
    }



    public List<AddPrescriptionDrugRequest> getSelectedItems() {
        return selectedItems;
    }
}
