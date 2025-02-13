package com.angus.springbootmall.service;

import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;

public interface ProductService {

    public Product getProductById(int id);

    public int createProduct(ProductRequest product);
}
