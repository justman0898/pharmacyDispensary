package com.pharmacy.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorsMainMenu extends JFrame {
    private final DoctorControllerUtils doctorControllerUtils = new DoctorControllerUtils();
    public DoctorsMainMenu() {

        setTitle("Welcome Doctor");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));


        JButton presribeButton = new JButton("Prescribe Drug");
        JButton signupButton = new JButton("View All Prescriptions");
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(20, 10));

        panel.add(presribeButton);
        panel.add(signupButton);
        panel.add(logoutButton);

        presribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: open LoginForm JFrame
                doctorControllerUtils.prescribeDrugs();

            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: open SignUpForm JFrame
                doctorControllerUtils.viewAllPrescriptions();

            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: open ChoiceForm
                new ChoiceForm();
                dispose();
            }
        });

        add(panel);
        setVisible(true);


    }
}
