package com.angus.springbootmall.dao;

import com.angus.springbootmall.dto.OrderQueryParameter;
import com.angus.springbootmall.model.Order;
import com.angus.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {


    public Integer createOrder(Integer userId, Integer amount);

    public void createOrderItem(Integer orderId , List<OrderItem> orderItemList);

    public Order getOrderById(Integer orderId);

    public List<OrderItem> getItemsByOrderId(Integer orderId);

    public List<Order> getUserAllOrders(OrderQueryParameter orderQueryParameter);

    public Integer getFilterOrderCount(OrderQueryParameter orderQueryParameter);
}
