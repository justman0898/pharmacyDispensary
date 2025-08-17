package com.pharmacy.data.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.DrugCategory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.beans.BeanDescriptor;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DrugRepositoryImpl implements DrugRepository {
    private final QueryRunner queryRunner;
    private int count;

    public DrugRepositoryImpl(MysqlDataSource dataSource) {
        this.queryRunner = new QueryRunner(dataSource);
    }


    @Override
    public Drug save(Drug drug) {
        try {
            String sql = "INSERT INTO drugs (drug_id, drug_name, drug_category, drug_type, expiry_date, date_created, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            drug.setDrugId(++count);
            queryRunner.update(sql, drug.getDrugId(), drug.getDrugName(), drug.getDrugCategory().toString(), drug.getDrugtype().toString(), drug.getExpiryDate(), drug.getDateCreated(), drug.getQuantity());

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

            String sql = "SELECT * FROM drugs";
            return queryRunner.query(sql, new BeanListHandler<>(Drug.class));
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
