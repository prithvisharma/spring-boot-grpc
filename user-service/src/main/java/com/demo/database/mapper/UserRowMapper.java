package com.demo.database.mapper;

import com.demo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        final User user = User.newBuilder()
                .setId(rs.getString("id"))
                .setName(rs.getString("name"))
                .setAge(rs.getInt("age"))
                .setGender(rs.getString("gender"))
                .setPhone(rs.getLong("phone"))
                .setEmail(rs.getString("email"))
                .setAddress(rs.getString("address"))
                .setCity(rs.getString("city"))
                .setState(rs.getString("state"))
                .setPincode(rs.getInt("pincode"))
                .build();
        return user;
    }

}