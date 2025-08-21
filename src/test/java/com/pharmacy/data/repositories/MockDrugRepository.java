package com.pharmacy.data.repositories;

import com.pharmacy.data.models.Drug;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockDrugRepository implements DrugRepository {
    private boolean wasSavedCalled;
    private boolean wasDeletedCalled;
    private boolean wasDeleteAllCalled;
    private Optional<Drug> drugToReturn;
    private Drug storedDrug;

    public MockDrugRepository(){
        drugToReturn = Optional.of(new Drug());
    }


    @Override
    public Drug save(Drug drug) {
        this.wasSavedCalled = true;
        this.storedDrug = drug;
        this.drugToReturn = Optional.of(drug);
        return drug;
    }

    @Override
    public void deleteById(int id) {
        this.wasDeletedCalled = true;
        this.drugToReturn = Optional.empty();
    }

    @Override
    public void deleteAll() {
        this.wasDeleteAllCalled = true;
        this.storedDrug = null;
        this.drugToReturn = Optional.empty();


    }

    @Override
    public Optional<Drug> findById(int id) {
        return drugToReturn;
    }

    @Override
    public List<Drug> findAll() {
        return List.of();
    }

    public boolean wasSavedCalled(){
        return wasSavedCalled;
    }

    public boolean wasDeletedCalled() {
        return wasDeletedCalled;
    }

    public void setDrugToReturn(Optional<Drug> drug){
        this.drugToReturn = drug;
        this.storedDrug = drug.orElse(null);

    }

    public Drug getStoredDrug(){
        return storedDrug;
    }

    public boolean wasDeleteAllCalled() {
        return wasDeleteAllCalled;
    }
}
