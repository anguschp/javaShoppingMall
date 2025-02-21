package com.angus.springbootmall.dao;

import com.angus.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {


    public Integer createOrder(Integer userId, Integer amount);

    public void createOrderItem(Integer orderId , List<OrderItem> orderItemList);
}
