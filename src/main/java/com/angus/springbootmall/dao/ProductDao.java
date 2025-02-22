package com.angus.springbootmall.dao;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;

import java.net.InterfaceAddress;
import java.util.List;

public interface ProductDao {

    public Product getProductById(int id);

    public int createProduct(ProductRequest product);

    public void updateProductById(int id, ProductRequest product);

    public void deleteProductById(int id);

    public List<Product> getAllProducts(ProductQueryParameter param);

    public Integer getProductsCount(ProductQueryParameter param);

    public void updateStock(Integer productId, Integer stockChange);
}
