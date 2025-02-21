package com.angus.springbootmall.rowmapper;

import com.angus.springbootmall.model.Order;
import com.angus.springbootmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

        Order orderRecord = new Order();

        orderRecord.setOrder_id(rs.getInt("order_id"));
        orderRecord.setUser_id(rs.getInt("user_id"));
        orderRecord.setTotal_amount(rs.getInt("total_amount"));
        orderRecord.setCreated_date(rs.getTimestamp("created_date"));
        orderRecord.setLast_modified_date(rs.getTimestamp("last_modified_date"));


        return orderRecord;
    }
}
