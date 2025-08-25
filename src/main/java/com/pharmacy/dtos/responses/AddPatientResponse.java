package com.pharmacy.dtos.responses;

import com.pharmacy.data.models.Gender;
import lombok.Data;

import java.sql.Date;
@Data
public class AddPatientResponse {

    private int patientId;
    private String patientName;
    private String name;
    private int age;
    private String contactInfo;
    private String medicalCondition;
    private String gender;
    private java.sql.Date dateCreated;



}