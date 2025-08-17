package com.pharmacy.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.data.repositories.DrugRepository;
import com.pharmacy.data.repositories.DrugRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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
    public void testThatCanSaveAndRetrieveDrug() {


    }




}
