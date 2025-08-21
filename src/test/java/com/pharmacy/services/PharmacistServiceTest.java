package com.pharmacy.services;

import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.MockPrescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PharmacistServiceImplTest {
    private PharmacistService pharmacistService;
    private MockPrescriptionRepository prescriptionRepository;

    @BeforeEach
    void setUp() {
        prescriptionRepository = new MockPrescriptionRepository();
        pharmacistService = new PharmacistServiceImpl(prescriptionRepository);
    }

    @Test
    void testPharmacistCanLogin() {
        assertTrue(pharmacistService.login(1, "password123"));
    }

    @Test
    void testThatPharmacistCanVerifyDrugPrescription() {
        Prescription prescription = new Prescription();
        prescription.setPatientId(10);
        prescription.setDoctorId(20);
        prescription.setDiagnosis("Malaria");
        prescription.setDateCreated(Date.valueOf(LocalDate.now().toString()));

        prescriptionRepository.save(prescription);

        pharmacistService.login(1, "password123");
        Prescription found = pharmacistService.verifyPrescription(prescription.getPrescriptionId());

        assertEquals("Malaria", found.getDiagnosis());
    }

    @Test
    void testThatDrugDispensePrescriptionUpdatesStatus() {
        Prescription prescription = new Prescription();
        prescription.setPatientId(10);
        prescription.setDoctorId(20);
        prescription.setDiagnosis("Typhoid");
        prescription.setDateCreated(Date.valueOf(LocalDate.now().toString()));

        prescriptionRepository.save(prescription);

        pharmacistService.login(1, "password123");
        Prescription dispensed = pharmacistService.dispensePrescription(prescription.getPrescriptionId());

        assertTrue(dispensed.isResolved());
    }

    @Test
    void testThatPharmacistCanViewDispensedHistory() {
        Prescription prescription = new Prescription();
        prescription.setPatientId(10);
        prescription.setDoctorId(20);
        prescription.setDiagnosis("Flu");
        prescription.setDateCreated(Date.valueOf(LocalDate.now().toString()));

        prescriptionRepository.save(prescription);

        pharmacistService.login(2, "securePass");
        pharmacistService.dispensePrescription(prescription.getPrescriptionId());

        assertEquals(1, pharmacistService.viewDispensedHistory(2).size());
    }
}
