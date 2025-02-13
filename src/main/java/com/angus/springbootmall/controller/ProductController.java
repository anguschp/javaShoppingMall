package com.angus.springbootmall.controller;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable int pid) {

        Product productResult = productService.getProductById(pid);

        if(productResult != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productResult);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }






}
