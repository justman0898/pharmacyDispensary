package com.pharmacy.services;

import com.pharmacy.data.models.Drug;
import com.pharmacy.data.repositories.DrugRepository;
import com.pharmacy.dtos.requests.AddDrugRequest;
import com.pharmacy.dtos.responses.AddDrugResponse;

import static com.pharmacy.utils.Mapper.convertToDrug;
import static com.pharmacy.utils.Mapper.convertToResponse;

public class DrugServiceImpl implements DrugService{

    private DrugRepository drugRepository;

    public DrugServiceImpl(DrugRepository drugRepository){
        this.drugRepository = drugRepository;
    }

    @Override
    public AddDrugResponse addDrug(AddDrugRequest addDrugRequest){
        Drug drug = convertToDrug(addDrugRequest);
        Drug savedDrug = drugRepository.save(drug);
        return convertToResponse(savedDrug);
    }

    @Override
    public void removeById(int id) {
        drugRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        drugRepository.deleteAll();
    }

    @Override
    public AddDrugResponse findDrugById(int id) {
        return null;
    }
}
