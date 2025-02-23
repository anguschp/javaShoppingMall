package com.angus.springbootmall.dto;

import com.angus.springbootmall.constant.ProductCategory;


public class ProductQueryParameter {


    private ProductCategory category;
    private String searchString;
    private String orderBy;
    private String sortingType;
    private Integer pageLimit;
    private Integer offSet;


    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

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
