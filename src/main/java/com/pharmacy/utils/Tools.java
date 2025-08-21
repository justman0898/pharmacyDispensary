package com.pharmacy.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tools extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Tools() {

        setTitle("Login Form");
        setSize(300, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen


        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));


        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);


        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);


        panel.add(new JLabel(""));


        JButton loginButton = new JButton("Login");
        panel.add(loginButton);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if ("doctor".equals(username) && "1234".equals(password)) {
                    JOptionPane.showMessageDialog(Tools.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(Tools.this, "Invalid credentials");
                }
            }
        });

        add(panel);
        setVisible(true);
    }




}
