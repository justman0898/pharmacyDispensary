package com.pharmacy.data.models;


import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
@Data
public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String contactInfo;
    private String medicalCondition;
    private Gender gender;
    private java.sql.Date dateCreated = Date.valueOf(LocalDate.now());

}
