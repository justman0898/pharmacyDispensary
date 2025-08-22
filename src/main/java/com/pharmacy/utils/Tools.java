package com.pharmacy.utils;

import com.pharmacy.controllers.DoctorController;
import com.pharmacy.controllers.PharmacistController;
import com.pharmacy.data.models.User;
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

public class Tools extends JFrame {
    private DoctorController doctorController = new DoctorController();
    private PharmacistController  pharmacistController = new PharmacistController();
    UserRepository userRepository = new UserRepositoryImpl();
    UserService userService = new UserServiceImpl(userRepository);

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
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.trim().isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username and password cannot be empty or spaces only!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                AddUserRequest  loginRequest = new AddUserRequest();
                loginRequest.setUserName(username);
                loginRequest.setPassword(password);

                try {
                    UserResponse user = userService.login(loginRequest);
                    if(user.getRole().equalsIgnoreCase("DOCTOR")){
                        doctorController.printDoctorsMainMenu();
                    } else if (user.getRole().equalsIgnoreCase("PHARMACIST")) {
                        pharmacistController.printPharmacistsMainMenu();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Tools.this, "Login failed: " + ex.getMessage());
                    Tools.this.dispose();
                    new Tools().setVisible(true);
                }




//                if ("doctor".equals(username) && "1234".equals(password)) {
//                    JOptionPane.showMessageDialog(Tools.this, "Login successful!");
//                } else {
//                    JOptionPane.showMessageDialog(Tools.this, "Invalid credentials");
//                }
            }
        });

        add(panel);
        setVisible(true);
    }




}
