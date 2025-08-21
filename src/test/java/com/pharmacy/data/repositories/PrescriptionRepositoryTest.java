package com.pharmacy.data.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pharmacy.config.DataSourceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PrescriptionRepositoryTest {

    @Container
    private MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("pharmacy")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("doctors.sql");

    private PrescriptionRepository prescriptionRepository;
    
    @BeforeEach
    public void setUp() {
        MysqlDataSource dataSource = new MysqlDataSource();
        
                
        
    }




}
