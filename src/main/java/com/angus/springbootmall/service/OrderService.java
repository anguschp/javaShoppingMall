package com.angus.springbootmall.service;

import com.angus.springbootmall.dto.OrderQueryParameter;
import com.angus.springbootmall.dto.OrderRequset;
import com.angus.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    public Integer createOrder(int userId, OrderRequset orderReq);

    public Order getOrderById(Integer orderId);

    public List<Order> getUserAllOrders(OrderQueryParameter orderQueryParameter);

    public Integer getFilterOrderCount(OrderQueryParameter orderQueryParameter);
}
