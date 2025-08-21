package com.pharmacy.data.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.config.DataSourceConfig;
import com.pharmacy.data.models.Drug;
import com.pharmacy.data.models.Prescription;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrescriptionRepositoryImpl implements PrescriptionRepository {
    private final QueryRunner queryRunner;
    private int count;

    public PrescriptionRepositoryImpl(MysqlDataSource dataSource) {
        queryRunner = new QueryRunner(dataSource);
    }

    public PrescriptionRepositoryImpl() {
        queryRunner = new QueryRunner(DataSourceConfig.createPrescriptionDataSource());
    }

    @Override
    public Prescription save(Prescription prescription) {
        try {
            if (prescription.getPrescriptionId() != 0 ){
                String sql = "UPDATE prescriptions SET patientId=?, patientName=?, doctorId=?, doctorName=?, diagnosis=?, dateCreated=?, isResolved=? WHERE prescriptionId = ?";
                queryRunner.update(sql, prescription.getPatientId(), prescription.getPatientName(), prescription.getDoctorId(), prescription.getDoctorName(),prescription.getDiagnosis(), prescription.getDateCreated(), prescription.isResolved(), prescription.getPrescriptionId());

                String deleteSql = "DELETE FROM prescription_drugs WHERE prescriptionId = ?";
                queryRunner.update(deleteSql, prescription.getPrescriptionId());

                String drugSql = "INSERT INTO prescription_drugs (drugId, prescriptionId, quantityPrescribed) VALUES (?, ?, ?)";
                prescription.getDrugsPrescribed()
                        .forEach((drug)-> {
                            try {
                                queryRunner.update(drugSql, drug.getDrugId(), prescription.getPrescriptionId(), drug.getQuantity());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }else {
                prescription.setPrescriptionId(++count);
                String sql = "INSERT INTO prescriptions (prescriptionId, patientId, patientName, doctorId, doctorName, diagnosis, dateCreated, isResolved) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                queryRunner.update(sql, prescription.getPrescriptionId(), prescription.getPatientId(), prescription.getPatientName(), prescription.getDoctorId(), prescription.getDoctorName(), prescription.getDiagnosis(), prescription.getDateCreated(), prescription.isResolved());

                String drugSql = "INSERT INTO prescription_drugs (drugId, prescriptionId, quantityPrescribed) VALUES (?,?,?)";
                prescription.getDrugsPrescribed().forEach((drug)-> {
                    try {
                        queryRunner.update(drugSql, drug.getDrugId(), prescription.getPrescriptionId(), drug.getQuantity());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  prescription;
    }

    @Override
    public Optional<Prescription> findById(int id) {
        try {
            String sql = "SELECT * FROM prescriptions WHERE prescriptionId = ?";
            Optional<Prescription> prescription = Optional.ofNullable(queryRunner.query(sql, new BeanHandler<>(Prescription.class), id));

            String drugSql = "SELECT drugId, quantityPrescribed FROM prescription_drugs WHERE prescriptionId=?";
            List<Map<String, Object>> rows = queryRunner.query(drugSql, new MapListHandler(), id);

            List<Drug> drugs = rows.stream()
                    .map(row-> {
                        Drug drug = new Drug();
                        drug.setDrugId((Integer) row.get("drugId"));
                        drug.setQuantity((Long) row.get("quantityPrescribed"));
                        return drug;
                    })
                    .collect(Collectors.toList());
            prescription.ifPresent((prescription1)-> prescription1.setDrugsPrescribed(drugs));
            return prescription;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public List<Prescription> findAll() {
        try {
            return queryRunner.query("SELECT * FROM prescriptions", new BeanListHandler<>(Prescription.class));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {

    }

    public long count(){
        try{
            return (long) queryRunner.query("SELECT COUNT(*) FROM prescriptions", new ScalarHandler<Number>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
