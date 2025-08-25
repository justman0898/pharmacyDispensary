package com.pharmacy.data.repositories;

import com.pharmacy.config.DataSourceConfig;
import com.pharmacy.data.models.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final QueryRunner queryRunner;

    public UserRepositoryImpl() {
        this.queryRunner = new QueryRunner(DataSourceConfig.createUserDataSource());    }


    @Override
    public User save(User user) {
        try {
            String sql = "INSERT INTO users (username, userPassword, role) VALUES (?, ?, ?)";
            queryRunner.update(sql, user.getUserName(), user.getUserPassword(), user.getRole().toString());
            return user;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
            User user = queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
            return Optional.ofNullable(user);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public long checkIfUserExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE userPassword = ?";
        try {
            return queryRunner.query(sql, new ScalarHandler<Long>(), username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
