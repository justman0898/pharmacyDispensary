package com.pharmacy.data.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.data.models.Drug;
import org.apache.commons.dbutils.QueryRunner;

import java.util.Optional;

public class DrugRepositoryImpl implements DrugRepository {
    private QueryRunner queryRunner;

    public DrugRepositoryImpl(MysqlDataSource dataSource) {

    }


    @Override
    public Drug save(Drug drug) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll(int id) {

    }

    @Override
    public Optional<Drug> findById(int id) {
        return Optional.empty();
    }
}
