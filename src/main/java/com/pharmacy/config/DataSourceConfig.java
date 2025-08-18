package com.pharmacy.config;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSourceConfig {

    public static MysqlDataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/pharmacy");
        dataSource.setUser("root");
        dataSource.setPassword("Katherinemylove1&");
        return dataSource;
    }
}
