package com.angus.springbootmall.dao;

import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;

import java.net.InterfaceAddress;

public interface ProductDao {

    public Product getProductById(int id);

    public int createProduct(ProductRequest product);

}
