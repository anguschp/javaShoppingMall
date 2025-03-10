package com.angus.springbootmall.service;

import com.angus.springbootmall.dto.ProductQueryParameter;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(int id);

    public int createProduct(ProductRequest product);

    public void updateProductById(int id, ProductRequest product);

    public void deleteProductById(int id);

    public List<Product> getAllProducts(ProductQueryParameter queryParam);

    public Integer getProductsCount(ProductQueryParameter queryParam);

}
