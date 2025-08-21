package com.pharmacy.services;

import com.pharmacy.data.models.Pharmacist;
import com.pharmacy.data.models.Prescription;
import com.pharmacy.data.repositories.MockPrescriptionRepository;
import com.pharmacy.exceptions.InvalidDetailsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PharmacistServiceImplTest {
    private PharmacistService pharmacistService;
    private MockPrescriptionRepository prescriptionRepository;

    @BeforeEach
    void setUp() {
        prescriptionRepository = new MockPrescriptionRepository();

        List<Pharmacist> pharmacists = new ArrayList<>();
        pharmacists.add(new Pharmacist(1, "John", "password123"));
        pharmacists.add(new Pharmacist(2, "Job", "securePass"));

        pharmacistService = new PharmacistServiceImpl(prescriptionRepository, pharmacists);
    }

    @Test
    void testPharmacistCanLoginSuccessfully() {
        assertTrue(pharmacistService.login(1, "password123"));
    }

    @Test
    void testPharmacistLoginFailsWithWrongPassword() {
        assertFalse(pharmacistService.login(1, "wrongPass"));
    }

    @Test
    void testVerifyPrescriptionAfterLogin() {
        Prescription prescription = new Prescription();
        prescription.setPatientId(10);
        prescription.setDoctorId(20);
        prescription.setDiagnosis("Malaria");
        prescription.setDateCreated(Date.valueOf(LocalDate.now()));

        prescriptionRepository.save(prescription);

        pharmacistService.login(1, "password123");
        Prescription found = pharmacistService.verifyPrescription(prescription.getPrescriptionId());

        assertEquals("Malaria", found.getDiagnosis());
    }

    @Test
    void testVerifyPrescriptionFailsIfNotLoggedIn() {
        Prescription prescription = new Prescription();
        prescription.setPatientId(11);
        prescription.setDoctorId(21);
        prescription.setDiagnosis("Typhoid");
        prescription.setDateCreated(Date.valueOf(LocalDate.now()));

        prescriptionRepository.save(prescription);

        assertThrows(InvalidDetailsException.class, () -> pharmacistService.verifyPrescription(prescription.getPrescriptionId()));
    }

    @Test
    void testDispensePrescriptionUpdatesStatus() {
        Prescription prescription = new Prescription();
        prescription.setPatientId(12);
        prescription.setDoctorId(22);
        prescription.setDiagnosis("Flu");
        prescription.setDateCreated(Date.valueOf(LocalDate.now()));

        prescriptionRepository.save(prescription);

        pharmacistService.login(1, "password123");
        Prescription dispensed = pharmacistService.dispensePrescription(prescription.getPrescriptionId());

        assertTrue(dispensed.isResolved());
    }

    @Test
    void testVerifyPrescriptionThrowsIfNotFound() {
        pharmacistService.login(1, "password123");
        assertThrows(InvalidDetailsException.class, () -> pharmacistService.verifyPrescription(999));
    }
}
