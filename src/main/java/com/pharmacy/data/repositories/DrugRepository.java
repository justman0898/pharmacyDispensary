package com.pharmacy.data.repositories;

import com.pharmacy.data.models.Drug;

import java.util.Optional;

public interface DrugRepository {

    Drug save (Drug drug);
    void deleteById(int id);
    void deleteAll(int id);
    Optional<Drug>  findById(int id);


}
