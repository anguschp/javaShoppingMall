package com.angus.springbootmall.rowmapper;

import com.angus.springbootmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {

        OrderItem orderItemRecord = new OrderItem();

        orderItemRecord.setOrder_item_id(rs.getInt("order_item_id"));
        orderItemRecord.setOrder_id(rs.getInt("order_id"));
        orderItemRecord.setProduct_id(rs.getInt("product_id"));
        orderItemRecord.setQuantity(rs.getInt("quantity"));
        orderItemRecord.setAmount(rs.getInt("amount"));

        //fields from products table
        orderItemRecord.setProduct_name(rs.getString("product_name"));
        orderItemRecord.setProduct_imgURL(rs.getString("image_url"));


        return orderItemRecord;
    }
}
