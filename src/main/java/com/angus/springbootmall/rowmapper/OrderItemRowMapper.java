package com.angus.springbootmall.rowmapper;

import com.angus.springbootmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {

        OrderItem orderItemReocrd = new OrderItem();

        orderItemReocrd.setOrder_item_id(rs.getInt("order_item_id"));
        orderItemReocrd.setOrder_id(rs.getInt("order_id"));
        orderItemReocrd.setProduct_id(rs.getInt("product_id"));
        orderItemReocrd.setQuantity(rs.getInt("quantity"));
        orderItemReocrd.setAmount(rs.getInt("amount"));
        orderItemReocrd.setProduct_name(rs.getString("product_name"));
        orderItemReocrd.setProduct_imgURL(rs.getString("image_url"));


        return orderItemReocrd;
    }
}
