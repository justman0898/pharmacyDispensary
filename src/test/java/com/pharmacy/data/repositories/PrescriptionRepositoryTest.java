package com.pharmacy.data.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Prescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class PrescriptionRepositoryTest {

    @Container
    private MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("pharmacy")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("prescriptions.sql");

    private PrescriptionRepositoryImpl prescriptionRepository;
    
    @BeforeEach
    void setUp() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(sqlContainer.getJdbcUrl());
        dataSource.setUser(sqlContainer.getUsername());
        dataSource.setPassword(sqlContainer.getPassword());
        prescriptionRepository = new PrescriptionRepositoryImpl(dataSource);

    }

    @Test
    void testThatCanAddPrescriptions(){
        Prescription prescription = new  Prescription();
        prescription.setDiagnosis("Diagnosis");
        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        List<Drug> drugs = List.of(drug1);
        prescription.setDrugsPrescribed(drugs);
        prescriptionRepository.save(prescription);
        assertEquals(1, prescriptionRepository.count());
    }

    @Test
    void testThatCanGetPrescriptions(){
        Prescription prescription = new  Prescription();
        prescription.setDiagnosis("Diagnosis");
        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        List<Drug> drugs = List.of(drug1);
        prescription.setDrugsPrescribed(drugs);
        Prescription saved = prescriptionRepository.save(prescription);

        Optional<Prescription> foundPrescription = prescriptionRepository.findById(saved.getPrescriptionId());
        assertTrue(foundPrescription.isPresent());
        assertEquals("Diagnosis", foundPrescription.get().getDiagnosis());
    }

    @Test
    void testThatIdsAreUnique(){
        Prescription prescription = new  Prescription();
        prescription.setDiagnosis("Diagnosis");

        Prescription prescription2 = new  Prescription();
        prescription2.setDiagnosis("Diagnosis2");

        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        List<Drug> drugs = List.of(drug1);
        prescription.setDrugsPrescribed(drugs);
        prescription2.setDrugsPrescribed(drugs);

        Prescription saved1 =  prescriptionRepository.save(prescription);
        assertDoesNotThrow(()-> prescriptionRepository.save(prescription2));
        Prescription saved2 =   prescriptionRepository.save(prescription2);


        Optional<Prescription> foundPrescription1 = prescriptionRepository.findById(saved1.getPrescriptionId());
        assertTrue(foundPrescription1.isPresent());
        Optional<Prescription> foundPrescription2 = prescriptionRepository.findById(saved2.getPrescriptionId());
        assertTrue(foundPrescription2.isPresent());
        assertNotEquals(foundPrescription1.get().getPrescriptionId(), foundPrescription2.get().getPrescriptionId());

    }

    @Test
    void testThatWhenUpdateNoNewObjectIsCreated(){
        Prescription prescription = new  Prescription();
        prescription.setDiagnosis("Diagnosis");

        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        List<Drug> drugs = List.of(drug1);
        prescription.setDrugsPrescribed(drugs);

        Prescription saved = prescriptionRepository.save(prescription);

        saved.setDiagnosis("Diagnosis2");

        Prescription saved2 = prescriptionRepository.save(saved);
        assertEquals(saved.getPrescriptionId(), saved2.getPrescriptionId());

        assertEquals(1, prescriptionRepository.count());
    }

    @Test
    void testThatCanFindAllPrescriptions(){
        Prescription prescription = new  Prescription();
        prescription.setDiagnosis("Diagnosis");

        Prescription prescription2 = new  Prescription();
        prescription2.setDiagnosis("Diagnosis2");

        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        List<Drug> drugs = List.of(drug1);
        prescription.setDrugsPrescribed(drugs);
        prescription2.setDrugsPrescribed(drugs);

        prescriptionRepository.save(prescription);
        prescriptionRepository.save(prescription2);

        List<Prescription> prescriptions = prescriptionRepository.findAll();
        assertEquals(2, prescriptions.size());
    }

    @Test
    void testThatCanRetrieveDrugsPrescribed(){
        Prescription prescription = new  Prescription();
        prescription.setDiagnosis("Diagnosis");

        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        Drug drug2 = new Drug();
        drug2.setDrugId(2);
        drug2.setDrugName("drug2");

        List<Drug> drugs = List.of(drug1, drug2);
        prescription.setDrugsPrescribed(drugs);

        prescriptionRepository.save(prescription);
        Optional<Prescription> foundPrescription = prescriptionRepository.findById(prescription.getPrescriptionId());
        foundPrescription.ifPresent((value)-> assertNotNull(value.getDrugsPrescribed()));
        assertTrue(foundPrescription.isPresent());
        List<Drug> prescribedDrugs = foundPrescription.get().getDrugsPrescribed();
        assertEquals(2, prescribedDrugs.size());

    }

    @Test
    void testThatDrugPrescribedUpdatesIfPrescriptionIsUpdated(){
        Prescription prescription = new  Prescription();
        prescription.setDiagnosis("Diagnosis");

        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        List<Drug> drugs = List.of(drug1);
        prescription.setDrugsPrescribed(drugs);

        Prescription saved =  prescriptionRepository.save(prescription);
        Drug drug2 = new Drug();
        drug2.setDrugId(2);
        drug2.setDrugName("drug2");

        Drug drug3 = new Drug();
        drug3.setDrugId(2);
        drug3.setDrugName("drug2");

        List<Drug>  updatedDrugs = List.of(drug2, drug3);
        saved.setDrugsPrescribed(updatedDrugs);

        Prescription newSaved =  prescriptionRepository.save(saved);

        Optional<Prescription> foundPrescription = prescriptionRepository.findById(saved.getPrescriptionId());
        assertTrue(foundPrescription.isPresent());
        assertNotNull(foundPrescription.get().getDrugsPrescribed());
        assertEquals(2, foundPrescription.get().getDrugsPrescribed().size());
    }

    @Test
    void test() {
        Prescription prescription = new Prescription();
        prescription.setDiagnosis("Diagnosis");

        Prescription prescription2 = new Prescription();
        prescription2.setDiagnosis("Diagnosis2");

        Drug drug1 = new Drug();
        drug1.setDrugId(1);
        drug1.setDrugName("drug1");

        List<Drug> drugs = List.of(drug1);
        prescription.setDrugsPrescribed(drugs);
        prescription2.setDrugsPrescribed(drugs);

        prescription.setDoctorId(1);
        prescription2.setDoctorId(2);

        prescriptionRepository.save(prescription);
        prescriptionRepository.save(prescription2);

        List<Prescription> prescriptions = prescriptionRepository.findPrescriptionByDocId(1);
        assertNotNull(prescriptions);

        assertEquals(1, prescriptions.size());
        assertEquals("Diagnosis", prescriptions.get(0).getDiagnosis());


    }



}
