package com.angus.springbootmall.service;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(int id);

    public int createProduct(ProductRequest product);

    public void updateProductById(int id, ProductRequest product);

    public void deleteProductById(int id);

    public List<Product> getAllProducts(ProductCategory category , String searchString);
}
