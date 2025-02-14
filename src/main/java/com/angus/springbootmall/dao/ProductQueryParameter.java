package com.angus.springbootmall.dao;

import com.angus.springbootmall.constant.ProductCategory;
import org.springframework.web.bind.annotation.RequestParam;


public class ProductQueryParameter {


    private ProductCategory category;
    private String searchString;
    private String orderBy;
    private String sortingType;


    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String orderType) {
        this.sortingType = orderType;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
