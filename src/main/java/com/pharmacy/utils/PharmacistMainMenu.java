package com.pharmacy.utils;

import com.pharmacy.controllers.PharmacistController;
import com.pharmacy.dtos.responses.AddPrescriptionResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PharmacistMainMenu extends JFrame {
    private PharmacistController  pharmacistController =  new PharmacistController();

    public PharmacistMainMenu() {
        setTitle("Welcome Pharmacist");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));


        JButton presribeButton = new JButton("View All Prescriptions");
        JButton signupButton = new JButton("Mark Prescription as Dispensed");
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(20, 10));

        panel.add(presribeButton);
        panel.add(signupButton);
        panel.add(logoutButton);

        presribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // TODO: open LoginForm JFrame
                    List<AddPrescriptionResponse> allPrescriptions = pharmacistController.viewAllPrescriptions();
                    new PrescriptionViewer(allPrescriptions);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // TODO: open SignUpForm JFrame
                    List<AddPrescriptionResponse> unDispensedPrescription = pharmacistController.viewAllUnDispensedPrescription();

                    new UnDispensedPrescriptionsFrame(unDispensedPrescription, selected -> {
                        selected.forEach(addPrescriptionResponse -> {
                            pharmacistController.dispensePrescription(addPrescriptionResponse.getPrescriptionId());
                        });
                        JOptionPane.showMessageDialog(null, selected);
                    }).setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //TODO: open ChoiceForm
                    new ChoiceForm();
                    dispose();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        add(panel);
        setVisible(true);

    }
}
