package com.angus.springbootmall.service;

import com.angus.springbootmall.dto.OrderRequset;
import com.angus.springbootmall.model.Order;

public interface OrderService {

    public Integer createOrder(int userId, OrderRequset orderReq);

    public Order getOrderById(Integer orderId);
}
