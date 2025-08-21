package com.pharmacy.dtos.requests;

import com.pharmacy.data.models.Gender;

import java.sql.Date;

public class AddPatientRequest {


    private int patientId;
    private String name;
    private int age;
    private String contactInfo;
    private String medicalCondition;
    private String gender;


    public int getPatientId() {
        return patientId;

    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


   }
