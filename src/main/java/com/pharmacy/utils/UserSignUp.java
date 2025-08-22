package com.pharmacy.utils;

import com.pharmacy.dtos.requests.AddUserRequest;

import javax.swing.*;
import java.util.Objects;

public class UserSignUp {

    public static AddUserRequest signUp(){
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUserName(DoctorControllerUtils.input("Enter user name: "));
        addUserRequest.setPassword(DoctorControllerUtils.input("Enter password: "));

        String role;
        do {
            role = DoctorControllerUtils.input("Enter role (doctor/pharmacist):");
            if (role == null) return null;
            role = role.trim().toLowerCase();
            if (!role.equals("doctor") && !role.equals("pharmacist")) {
                JOptionPane.showMessageDialog(null, "Invalid role! Please enter 'doctor' or 'pharmacist'.");
            }
        } while (!role.equals("doctor") && !role.equals("pharmacist"));

        addUserRequest.setRole(role.toUpperCase());
        return addUserRequest;

    }
}
