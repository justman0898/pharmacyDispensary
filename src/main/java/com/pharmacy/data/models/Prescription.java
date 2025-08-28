package com.pharmacy.data.models;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
public class Prescription {
    private int prescriptionId;
    private int patientId;
    private String patientName;
    private int doctorId;
    private String doctorName;
    private String diagnosis;
    private List<Drug> drugsPrescribed;
    private Date dateCreated = Date.valueOf(LocalDate.now());
    private boolean isResolved;


}
