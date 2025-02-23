package com.angus.springbootmall.controller;

import com.angus.springbootmall.dto.OrderQueryParameter;
import com.angus.springbootmall.dto.OrderRequset;
import com.angus.springbootmall.model.Order;
import com.angus.springbootmall.service.OrderService;
import com.angus.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/user/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable int userId , @RequestBody @Valid OrderRequset orderReq) {

        Integer orderId = orderService.createOrder(userId , orderReq);

        Order returnOrder = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnOrder);

    }

    @GetMapping("/user/{userId}/allOrders")
    public ResponseEntity<Page<Order>> getOrderById(@PathVariable Integer userId,
                                                    @RequestParam(defaultValue = "4") @Valid @Min(0) @Max(100) Integer pagelimit,
                                                    @RequestParam(defaultValue = "0") @Min(0) Integer offSet)
    {

        OrderQueryParameter orderParam = new OrderQueryParameter();
        orderParam.setUserId(userId);
        orderParam.setPageLimit(pagelimit);
        orderParam.setOffset(offSet);

        List<Order> returnUserOrder = orderService.getUserAllOrders(orderParam);

        Integer filteredOrderCount = orderService.getFilterOrderCount(orderParam);

        Page<Order> page = new Page<Order>();
        page.setLimit(pagelimit);
        page.setOffset(offSet);
        page.setTotalRecords(filteredOrderCount);
        page.setResult(returnUserOrder);

        return ResponseEntity.status(HttpStatus.OK).body(page);

    }


}
