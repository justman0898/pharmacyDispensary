package com.pharmacy.controllers;

import com.pharmacy.services.UserService;
import com.pharmacy.utils.DoctorsMainMenu;

public class DoctorController {

    private UserService userService;

   public void printDoctorsMainMenu(){
        new DoctorsMainMenu();
   }




}
