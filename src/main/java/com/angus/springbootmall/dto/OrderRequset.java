package com.angus.springbootmall.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderRequset {

    //data member
    @NotEmpty
    List<BuyItem> orderList;

    //method
    public List<BuyItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<BuyItem> orderList) {
        this.orderList = orderList;
    }
}
