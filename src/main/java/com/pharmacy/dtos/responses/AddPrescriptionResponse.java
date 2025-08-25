package com.pharmacy.dtos.responses;

import com.pharmacy.data.models.Drug;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
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


    public String printDrugsPrescribed() {
        return drugsPrescribed.stream().map(Drug::toString)
                .collect(Collectors.joining("\n\n"));
    }

    @Override
    public String toString() {
        return "\n\nPrescription Id: "+ getPrescriptionId()+ "\nPatient Id: "+getPatientId()+"\nPatient Name: "+getPatientName()+"\nDoctor Id: "+getDoctorId()+"\nDoctor Name: "+getDoctorName()+"\nDiagnosis: "+getDiagnosis()+"\n=== Drugs Prescribed ==\n"+printDrugsPrescribed();
    }
}
