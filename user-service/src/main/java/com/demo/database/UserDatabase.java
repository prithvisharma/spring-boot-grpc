package com.demo.database;

import com.demo.User;
import com.demo.database.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String table = "public.user";

    private String addTableToQuery(String table, String sql) {
        return String.format(sql, table);
    }

    public String insert(User user) {
        //final String query = "INSERT INTO public.\"user\"(name, age, gender, phone, email, address, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        final String query = "INSERT INTO %s " +
                "(name, age, gender, phone, email, address, city, state, pincode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)  RETURNING id;";
        final String sql = addTableToQuery(table, query);
        final String id = jdbcTemplate.queryForObject(sql, String.class, user.getName(), user.getAge(), user.getGender(),
                user.getPhone(), user.getEmail(), user.getAddress(), user.getCity(), user.getState(), user.getPincode());
        return id;
    }

    public List<User> findAll() {
        final String query = "SELECT * FROM %s;";
        final String sql = addTableToQuery(table, query);
        final List<User> userList = jdbcTemplate.query(sql, new UserRowMapper());
        return userList;
    }

    public User findById(String id) {
        final String query = "SELECT * FROM %s WHERE id = uuid(?);";
        final String sql = addTableToQuery(table, query);
        try {
            final User user= jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public User findByName(String name) {
        final String query = "SELECT * FROM %s WHERE name = ?;";
        final String sql = addTableToQuery(table, query);
        try {
            final User user= jdbcTemplate.queryForObject(sql, new UserRowMapper(), name);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public User findAlikeByName(String nameLike) {
        final String query = "SELECT * FROM %s WHERE name LIKE ?;";
        final String sql = addTableToQuery(table, query);
        try {
            final User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), nameLike.concat("%"));
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public boolean updateById(User user) {
        final String query = "UPDATE %s SET name=?, age=?, gender=?, phone=?, " +
                "email=?, address=?, city=?, state=?, pincode=? " +
                "WHERE id=uuid(?);";
        final String sql = addTableToQuery(table, query);
        final int result = jdbcTemplate.update(sql,user.getName(), user.getAge(), user.getGender(),
                user.getPhone(), user.getEmail(), user.getAddress(), user.getCity(), user.getState(),
                user.getPincode(), user.getId());
        return result == 1;
    }

    public boolean deleteById(String id) {
        final String query = "DELETE FROM %s WHERE id=uuid(?);";
        final String sql = addTableToQuery(table, query);
        final int result = jdbcTemplate.update(sql,id);
        return result == 1;
    }
}
