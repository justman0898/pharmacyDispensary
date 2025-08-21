package com.pharmacy.services;


import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.DrugCategory;
import com.pharmacy.data.models.DrugType;
import com.pharmacy.data.repositories.MockDrugRepository;
import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.requests.BuyDrugRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.exceptions.DrugNotFoundException;
import com.pharmacy.exceptions.InvalidDataException;
import com.pharmacy.exceptions.InvalidDrugQuantityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DrugServiceTest {

    private DrugService drugService;
    private MockDrugRepository drugRepository;


    @BeforeEach
    void setUp() {
        drugRepository = new MockDrugRepository();
        drugService = new DrugServiceImpl(drugRepository);
    }


    @Test
    public void testThatDrugCanBeAdded() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Panadol");
        addDrugRequest.setDrugType(DrugType.CAPSULE);
        addDrugRequest.setDrugCategory(DrugCategory.ANALGESIC);
        addDrugRequest.setQuantity(1);
        addDrugRequest.setManufactureDate(Date.valueOf(LocalDate.now()));
        addDrugRequest.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(10)));

        AddDrugResponse addDrugResponse = drugService.addDrug(addDrugRequest);
        assertTrue(drugRepository.wasSavedCalled());
    }


    @Test
    public void testThatDrugCanBeDeleted() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Panadol");
        addDrugRequest.setDrugType(DrugType.CAPSULE);
        addDrugRequest.setDrugCategory(DrugCategory.ANALGESIC);
        addDrugRequest.setQuantity(1);
        addDrugRequest.setManufactureDate(Date.valueOf(LocalDate.now()));
        addDrugRequest.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(10)));

        AddDrugResponse addDrugResponse = drugService.addDrug(addDrugRequest);
        drugService.removeById(addDrugResponse.getDrugId());
        assertTrue(drugRepository.wasDeletedCalled());
    }

    @Test
    void testInvalidExpiryDate() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Panadol");
        addDrugRequest.setDrugType(DrugType.CAPSULE);
        addDrugRequest.setDrugCategory(DrugCategory.ANALGESIC);
        addDrugRequest.setQuantity(1);
        addDrugRequest.setManufactureDate(Date.valueOf(LocalDate.now()));
        addDrugRequest.setExpiryDate(Date.valueOf(LocalDate.now().minusDays(10)));


        assertThrows(InvalidDataException.class, () -> drugService.addDrug(addDrugRequest));
        assertFalse(drugRepository.wasSavedCalled());
    }


    @Test
    void testThatDeletedWasNotCalledWhenDrugDoesNotExist() {
        drugRepository.setDrugToReturn(Optional.empty());
        assertThrows(DrugNotFoundException.class, () -> drugService.removeById(1));

        assertFalse(drugRepository.wasDeletedCalled());
    }


    @Test
    void testThatCanFindValidDrug() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Paracetamol");
        addDrugRequest.setQuantity(5);
        Drug convert = new Drug();
        convert.setDrugName("Paracetamol");
        convert.setQuantity(2);
        drugRepository.setDrugToReturn(Optional.of(convert));

        AddDrugResponse addDrugResponse = drugService.addDrug(addDrugRequest);

        AddDrugResponse foundDrug = drugService.findDrugById(addDrugResponse.getDrugId());
        assertEquals("Paracetamol", foundDrug.getDrugName());

    }

    @Test
    void testThatItThrowsExceptionWhenDrugNotFound() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Panadol");
        addDrugRequest.setQuantity(1);

        AddDrugResponse addDrugResponse = drugService.addDrug(addDrugRequest);


        drugRepository.setDrugToReturn(Optional.empty());

        assertThrows(DrugNotFoundException.class, () -> drugService.findDrugById(addDrugResponse.getDrugId()));

    }

    @Test
    void addDrugWithZeroQuantityThrowsException() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Panadol");
        addDrugRequest.setQuantity(0);

        assertThrows(InvalidDrugQuantityException.class, () -> drugService.addDrug(addDrugRequest));
    }


    @Test
    void testThatDrugQuantityReducesWhenDrugISBought() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Panadol");
        addDrugRequest.setQuantity(30);
        addDrugRequest.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(10)));
        addDrugRequest.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(30)));

        AddDrugResponse response = drugService.addDrug(addDrugRequest);


        BuyDrugRequest buyDrugRequest = new BuyDrugRequest();
        buyDrugRequest.setDrugId(response.getDrugId());
        buyDrugRequest.setQuantity(5);
        drugService.buyDrugs(buyDrugRequest);


        Drug updatedDrug = drugRepository.findById(response.getDrugId()).orElseThrow();

        assertEquals(25, updatedDrug.getQuantity());
        assertTrue(drugRepository.wasSavedCalled());
    }

    @Test
    void testBuyDrugsThrowsWhenQuantityTooHigh() {
        AddDrugRequest request = new AddDrugRequest();
        request.setDrugName("Amoxicillin");
        request.setQuantity(10);
        request.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(10)));
        request.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(30)));

        AddDrugResponse response = drugService.addDrug(request);

        BuyDrugRequest buyRequest = new BuyDrugRequest();
        buyRequest.setDrugId(response.getDrugId());
        buyRequest.setQuantity(50);

        assertThrows(InvalidDrugQuantityException.class, () -> drugService.buyDrugs(buyRequest));
    }

    @Test
    void testBuyDrugsThrowsWhenDrugNotFound() {
        drugRepository.setDrugToReturn(Optional.empty());

        BuyDrugRequest request = new BuyDrugRequest();
        request.setDrugId(99);
        request.setQuantity(1);

        assertThrows(DrugNotFoundException.class, () -> drugService.buyDrugs(request));
    }


    @Test
    void testAddDrugThrowsWhenManufactureDateInFuture() {
        AddDrugRequest request = new AddDrugRequest();
        request.setDrugName("Paracetamol");
        request.setQuantity(5);
        request.setManufactureDate(Date.valueOf(LocalDate.now().plusDays(10)));

        assertThrows(InvalidDataException.class, () -> drugService.addDrug(request));
    }

    @Test
    void testRemoveByIdDeleteDrug() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Paracetamol");
        addDrugRequest.setQuantity(5);
        addDrugRequest.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(5)));
        addDrugRequest.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(10)));

        AddDrugResponse addDrugResponse = drugService.addDrug(addDrugRequest);

        drugService.removeById(addDrugResponse.getDrugId());
        assertTrue(drugRepository.wasDeletedCalled());
    }

    @Test
    void testRemoveByIdThrowsWhenDrugNotFound() {
        drugRepository.setDrugToReturn(Optional.empty());

        assertThrows(DrugNotFoundException.class, () -> drugService.removeById(99));
    }

    @Test
    void testRemoveAllDeletesAllRepository() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setDrugName("Paracetamol");
        addDrugRequest.setQuantity(10);
        addDrugRequest.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(5)));
        addDrugRequest.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(20)));


        AddDrugRequest request = new AddDrugRequest();
        request.setDrugName("Panadol");
        request.setQuantity(5);
        request.setManufactureDate(Date.valueOf(LocalDate.now().minusDays(5)));
        request.setExpiryDate(Date.valueOf(LocalDate.now().plusDays(10)));


        drugService.addDrug(addDrugRequest);
        drugService.addDrug(request);

        drugService.removeAll();
        assertTrue(drugRepository.findAll().isEmpty());
        assertTrue(drugRepository.wasDeleteAllCalled());
    }
}