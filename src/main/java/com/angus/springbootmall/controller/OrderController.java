package com.angus.springbootmall.controller;

import com.angus.springbootmall.dto.OrderRequset;
import com.angus.springbootmall.model.Order;
import com.angus.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/user/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable int userId , @RequestBody @Valid OrderRequset orderReq) {

        Integer orderId = orderService.createOrder(userId , orderReq);


        return ResponseEntity.status(201).body(orderId);

    }


}
