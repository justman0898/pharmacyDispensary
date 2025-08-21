package com.pharmacy.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorsMainMenu extends JFrame {

    public DoctorsMainMenu() {

        setTitle("Welcome Doctors");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));


        JButton loginButton = new JButton("Prescribe Drug");
        JButton signupButton = new JButton("View All Prescriptions");

        panel.add(loginButton);
        panel.add(signupButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: open LoginForm JFrame


            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: open SignUpForm JFrame
            }
        });

        add(panel);
        setVisible(true);

    }
}
