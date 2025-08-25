package com.pharmacy.data.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.config.DataSourceConfig;
import com.pharmacy.data.models.Drug;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DrugRepositoryImpl implements DrugRepository {
    private final QueryRunner queryRunner;
    private int count;

    public DrugRepositoryImpl() {
        this.queryRunner = new QueryRunner(DataSourceConfig.createUserDataSource());
    }

    public DrugRepositoryImpl(MysqlDataSource db) {
        this.queryRunner = new QueryRunner(db);
    }

    @Override
    public Drug save(Drug drug) {
        try {
            if (drug.getDrugId() != 0){
                String sql = "UPDATE drugs SET drug_name =?, drug_category=?, drug_type=?, expiry_date=?, date_created=?, manufacture_date=?, quantity=? WHERE drug_id = ?";
                queryRunner.update(sql, drug.getDrugName(), drug.getDrugCategory().toString(), drug.getDrugtype().toString(), drug.getExpiryDate(), drug.getDateCreated(), drug.getManufactureDate(), drug.getQuantity(), drug.getDrugId());
            }else {
                String sql = "INSERT INTO drugs (drug_id, drug_name, drug_category, drug_type, expiry_date, date_created, manufacture_date, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                drug.setDrugId(++count);
                queryRunner.update(sql, drug.getDrugId(), drug.getDrugName(), drug.getDrugCategory().toString(), drug.getDrugtype().toString(), drug.getExpiryDate(), drug.getDateCreated(), drug.getManufactureDate(), drug.getQuantity());
            }
                return drug;

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            String sql = "DELETE FROM drugs WHERE drug_id =?";
            queryRunner.update(sql, id);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            String sql = "DELETE FROM drugs";
            queryRunner.update(sql);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Drug> findById(int id) {
        try {
            String sql = "SELECT drug_id AS drugId, drug_name AS drugName, drug_category AS drugCategory, drug_type AS drugtype, expiry_date AS expiryDate, date_created AS dateCreated, manufacture_date AS manufactureDate,  quantity FROM drugs WHERE drug_id = ?";
            Drug foundDrug = queryRunner.query(sql, new BeanHandler<>(Drug.class), id);
            return Optional.ofNullable(foundDrug);
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Drug> findAll() {
        try {
            String sql = "SELECT " +
                    "drug_id AS drugId, " +
                    "drug_name AS drugName, " +
                    "drug_category AS drugCategory, " +
                    "drug_type AS drugtype, " +
                    "expiry_date AS expiryDate, " +
                    "date_created AS dateCreated, " +
                    "manufacture_date AS manufactureDate, " +
                    "quantity AS quantity " +
                    "FROM drugs";
            List<Drug> drugs = queryRunner.query(sql, new BeanListHandler<>(Drug.class));
            return drugs == null ? Collections.emptyList() : drugs;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
