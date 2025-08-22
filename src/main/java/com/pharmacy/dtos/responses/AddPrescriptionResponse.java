package com.pharmacy.dtos.responses;

import com.pharmacy.data.models.Drug;

import java.sql.Date;
import java.util.List;

public class AddPrescriptionResponse {

    private int prescriptionId;
    private int patientId;
    private String patientName;
    private int doctorId;
    private String doctorName;
    private String diagnosis;
    private List<Drug> drugsPrescribed;
    private Date dateCreated;
    private boolean isResolved;

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }




    @Override
    public String toString() {
        return "Prescription Id: "+ getPrescriptionId()+ "\nPatient Id: "+getPatientId()+"\nPatient Name: "+getPatientName()+"\nDoctor Id: "+getDoctorId()+"\nDoctor Name: "+getDoctorName()+"\nDiagnosis: "+getDiagnosis()+"\nDrugs: "+getDrugsPrescribed();
    }
}
