package com.pharmacy.utils;

import com.pharmacy.data.repositories.UserRepository;
import com.pharmacy.data.repositories.UserRepositoryImpl;
import com.pharmacy.dtos.requests.AddUserRequest;
import com.pharmacy.dtos.responses.UserResponse;
import com.pharmacy.services.UserService;
import com.pharmacy.services.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChoiceForm extends JFrame {
    UserRepository repo;
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    public ChoiceForm() {

        setTitle("Welcome");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));


        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        panel.add(loginButton);
        panel.add(signupButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: open LoginForm JFrame
                new Tools().setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();

            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: open SignUpForm JFrame
                try {
                    AddUserRequest newUser = UserSignUp.signUp();
                    UserResponse response = userService.signUp(newUser);
                    DoctorControllerUtils.print(response.toString());
                    ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                    new Tools().setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ChoiceForm.this, "Login failed: " + ex.getMessage());

                    ChoiceForm.this.dispose();

                    new ChoiceForm().setVisible(true);
                }

            }
        });

        add(panel);
        setVisible(true);
    }


}


