package com.angus.springbootmall.rowmapper;

import com.angus.springbootmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setCreated_date(rs.getTimestamp("created_date"));
        user.setPassword(rs.getString("password"));
        user.setLast_modified_date(rs.getTimestamp("last_modified_date"));


        return user;
    }
}
