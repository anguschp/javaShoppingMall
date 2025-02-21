package com.angus.springbootmall.service;

import com.angus.springbootmall.dto.OrderRequset;

public interface OrderService {

    public Integer createOrder(int userId, OrderRequset orderReq);

}
