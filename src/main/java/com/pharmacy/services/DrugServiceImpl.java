package com.pharmacy.services;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.repositories.DrugRepository;
import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.requests.BuyDrugRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;
import com.pharmacy.dtos.responses.AvailableDrugResponse;
import com.pharmacy.exceptions.DrugNotFoundException;
import com.pharmacy.exceptions.InvalidDataException;
import com.pharmacy.exceptions.InvalidDrugQuantityException;
import com.pharmacy.utils.Mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DrugServiceImpl implements DrugService{

   private DrugRepository drugRepository;

   public DrugServiceImpl(DrugRepository drugRepository){
       this.drugRepository = drugRepository;

   }

    @Override
    public AddDrugResponse addDrug(AddDrugRequest addDrugRequest) {
       validateRequest(addDrugRequest);
       Drug drug = Mapper.convertToDrug(addDrugRequest);
       drugRepository.save(drug);
       return Mapper.convertToResponse(drug);
    }



    @Override
    public void removeById(int id) {
        Optional<Drug> drug = drugRepository.findById(id);
        Drug found = drugRepository.findById(id).orElseThrow(() -> new DrugNotFoundException(id));
            drugRepository.deleteById(found.getDrugId());

    }

    @Override
    public void removeAll() {
       drugRepository.deleteAll();

    }


    @Override
    public AddDrugResponse findDrugById(int id) {
        Optional<Drug> foundDrug = drugRepository.findById(id);
        Drug foundDrugEntity = foundDrug.orElseThrow(()-> new DrugNotFoundException(id));
        return Mapper.convertToResponse(foundDrugEntity);
    }


    private void validateRequest(AddDrugRequest addDrugRequest){
       validateQuantity((int) addDrugRequest.getQuantity());
       if(addDrugRequest.getManufactureDate() !=null) validateManufactureDate(addDrugRequest.getManufactureDate().toLocalDate());
      if (addDrugRequest.getExpiryDate() != null) validateExpiryDate(addDrugRequest.getExpiryDate().toLocalDate());

    }

    private void validateManufactureDate(LocalDate manufactureDate) {
        if (manufactureDate == null) return;

        LocalDate today = LocalDate.now();

        if(manufactureDate.isAfter(today)){
            throw new InvalidDataException("Manufacture date cannot be in the future");
        }
    }

    private void validateQuantity(int quantity) {
       if  (quantity <= 0) {
           throw new InvalidDrugQuantityException("Drug quantity must be greater than 0");
       }
    }

    private void validateExpiryDate(LocalDate expiryDate) {
       if (expiryDate == null) return;

       LocalDate today = LocalDate.now();

       if(expiryDate.isBefore(today)){
           throw new InvalidDataException("Expiry date must be after today");
       }

   }

   public void buyDrugs(BuyDrugRequest buyDrugRequest){
    Drug drug = drugRepository.findById(buyDrugRequest.getDrugId()).orElseThrow(() -> new DrugNotFoundException(buyDrugRequest.getDrugId()));
    int remaining = (int) (drug.getQuantity() - buyDrugRequest.getQuantity());
    if(remaining < 0){
        throw new InvalidDrugQuantityException("Not enough drugs available");
    }
    drug.setQuantity(remaining);
    drugRepository.save(drug);
   }

   public List<AvailableDrugResponse> getAvailableDrugs(){
       List<Drug> allDrugs = drugRepository.findAll();
       List<AvailableDrugResponse> availableDrugs = new ArrayList<>();
       for(Drug drug: allDrugs){
           if(drug.getQuantity() > 0){
               AvailableDrugResponse availableDrugResponse = new AvailableDrugResponse();
               availableDrugResponse.setName(drug.getDrugName());
               availableDrugResponse.setQuantity((int) drug.getQuantity());
               availableDrugResponse.isExpired(drug.getExpiryDate().toLocalDate());
               availableDrugs.add(availableDrugResponse);
           }
       }
       return availableDrugs;
   }


}





