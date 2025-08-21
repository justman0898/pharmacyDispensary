package com.pharmacy.dtos.requests;

import com.pharmacy.data.models.Drug;

import java.util.List;

public class AddPrescriptionRequest {

    private int patientId;
    private String patientName;
    private int doctorId;
    private String doctorName;
    private String diagnosis;
    private List<Drug> drugsPrescribed;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Drug> getDrugsPrescribed() {
        return drugsPrescribed;
    }

    public void setDrugsPrescribed(List<Drug> drugsPrescribed) {
        this.drugsPrescribed = drugsPrescribed;
    }
}
