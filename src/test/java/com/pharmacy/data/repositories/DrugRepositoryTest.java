package com.pharmacy.data.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.DrugCategory;
import com.pharmacy.data.models.DrugType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class DrugRepositoryTest {

    @Container
    private MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("pharmacy")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("drugs.sql");

    private DrugRepository drugRepository;

    @BeforeEach
    void setUp() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(mySQLContainer.getJdbcUrl());
        ds.setUser(mySQLContainer.getUsername());
        ds.setPassword(mySQLContainer.getPassword());
        drugRepository = new DrugRepositoryImpl(ds);
    }

    @Test
    void testThatCanSaveAndRetrieveDrug() {
        Drug drug = new Drug();
        drug.setDrugName("test");
        drug.setDrugCategory(DrugCategory.ANALGESIC);
        drug.setDrugtype(DrugType.INJECTION);
        drug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        drug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        drug.setQuantity(2);
        Drug savedDrug = drugRepository.save(drug);

        Optional<Drug> foundDrug = drugRepository.findById(savedDrug.getDrugId());
        foundDrug.ifPresent(value -> assertEquals("test", value.getDrugName()));
    }

    @Test
    void testThatCanSaveTwoAndFindOneById(){
        Drug drug = new Drug();
        drug.setDrugName("test");
        drug.setDrugCategory(DrugCategory.ANALGESIC);
        drug.setDrugtype(DrugType.INJECTION);
        drug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        drug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        drug.setQuantity(2);
        Drug savedDrug = drugRepository.save(drug);

        Drug secondDrug = new Drug();
        secondDrug.setDrugName("test2");
        secondDrug.setDrugCategory(DrugCategory.ANALGESIC);
        secondDrug.setDrugtype(DrugType.INJECTION);
        secondDrug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        secondDrug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        secondDrug.setQuantity(2);
        Drug secondSavedDrug = drugRepository.save(secondDrug);

        assertEquals(2, secondSavedDrug.getDrugId());

        Optional<Drug> foundDrug = drugRepository.findById(secondSavedDrug.getDrugId());
        foundDrug.ifPresent(value -> assertEquals("test2", value.getDrugName()));
    }

    @Test
    void testThatCanDeleteById(){
        Drug drug = new Drug();
        drug.setDrugName("test");
        drug.setDrugCategory(DrugCategory.ANALGESIC);
        drug.setDrugtype(DrugType.INJECTION);
        drug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        drug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        drug.setQuantity(2);
        Drug savedDrug = drugRepository.save(drug);
        Optional<Drug> foundDrug = drugRepository.findById(savedDrug.getDrugId());
        foundDrug.ifPresent(value -> assertEquals("test", value.getDrugName()));

        drugRepository.deleteById(drug.getDrugId());

        foundDrug = drugRepository.findById(drug.getDrugId());

        assertTrue(foundDrug.isEmpty());
    }

    @Test
    void testThatCanDIndAllDrugs(){

        Drug drug = new Drug();
        drug.setDrugName("test");
        drug.setDrugCategory(DrugCategory.ANALGESIC);
        drug.setDrugtype(DrugType.INJECTION);
        drug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        drug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        drug.setQuantity(2);

        Drug secondDrug = new Drug();
        secondDrug.setDrugName("test2");
        secondDrug.setDrugCategory(DrugCategory.ANALGESIC);
        secondDrug.setDrugtype(DrugType.INJECTION);
        secondDrug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        secondDrug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        secondDrug.setQuantity(2);
        List<Drug> allDrugs = drugRepository.findAll();

        assertEquals(0, allDrugs.size());
        Drug savedDrug = drugRepository.save(drug);
        Drug secondSavedDrug = drugRepository.save(secondDrug);

        allDrugs = drugRepository.findAll();
        assertEquals(2, allDrugs.size());
    }

    @Test
    void testThatCanRemoveAllDrugs(){
        Drug drug = new Drug();
        drug.setDrugName("test");
        drug.setDrugCategory(DrugCategory.ANALGESIC);
        drug.setDrugtype(DrugType.INJECTION);
        drug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        drug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        drug.setQuantity(2);

        Drug secondDrug = new Drug();
        secondDrug.setDrugName("test2");
        secondDrug.setDrugCategory(DrugCategory.ANALGESIC);
        secondDrug.setDrugtype(DrugType.INJECTION);
        secondDrug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        secondDrug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        secondDrug.setQuantity(2);
        List<Drug> allDrugs = drugRepository.findAll();

        assertEquals(0, allDrugs.size());
        Drug savedDrug = drugRepository.save(drug);
        Drug secondSavedDrug = drugRepository.save(secondDrug);

        allDrugs = drugRepository.findAll();
        assertEquals(2, allDrugs.size());

        drugRepository.deleteAll();

        allDrugs = drugRepository.findAll();
        assertEquals(0, allDrugs.size());
    }

    @Test
    void testThatAlreadySavedDrugWillBeUpdated(){
        Drug drug = new Drug();
        drug.setDrugName("test");
        drug.setDrugCategory(DrugCategory.ANALGESIC);
        drug.setDrugtype(DrugType.INJECTION);
        drug.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(1)));
        drug.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(2)));
        drug.setQuantity(2);
        Drug savedDrug = drugRepository.save(drug);
        assertEquals(1, drugRepository.findAll().size());
        savedDrug.setDrugCategory(DrugCategory.ANTIDEPRESSANT);

        Drug updated = drugRepository.save(savedDrug);
        assertEquals(1, drugRepository.findAll().size());
    }




}
