package com.angus.springbootmall.dto;

import org.springframework.stereotype.Component;


public class OrderQueryParameter {

    private Integer userId;
    private Integer PageLimit;
    private Integer offset;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPageLimit() {
        return PageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        PageLimit = pageLimit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
