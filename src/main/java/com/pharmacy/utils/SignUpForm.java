package com.pharmacy.utils;

import com.pharmacy.dtos.requests.AddUserRequest;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;

public class SignUpForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private JButton submitButton, cancelButton;

    public SignUpForm(Consumer<AddUserRequest> onSubmit) {
        setTitle("Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        roleField = new JTextField();

        submitButton = new JButton("Sign Up");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Role:"));
        add(roleField);
        add(submitButton);
        add(cancelButton);

        submitButton.addActionListener(e -> {
            AddUserRequest req = new AddUserRequest();
            req.setUserName(usernameField.getText());
            req.setPassword(new String(passwordField.getPassword()));
            req.setRole(roleField.getText().toUpperCase());

            onSubmit.accept(req);
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());
    }




//    public static AddUserRequest signUp(){
//        AddUserRequest addUserRequest = new AddUserRequest();
//        addUserRequest.setUserName(DoctorControllerUtils.input("Enter user name: "));
//        addUserRequest.setPassword(DoctorControllerUtils.input("Enter password: "));
//        addUserRequest.setRole(Objects.requireNonNull(DoctorControllerUtils.input("Enter role: ")).toUpperCase());
//
//        return addUserRequest;
//
//    }
}
