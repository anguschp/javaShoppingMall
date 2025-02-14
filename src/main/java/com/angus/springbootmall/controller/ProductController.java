package com.angus.springbootmall.controller;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts()
    {
        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }


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


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest pr) {

        int returnProductId = productService.createProduct(pr);

        Product newProduct = productService.getProductById(returnProductId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }


    @PutMapping("/products/{pid}")
    public ResponseEntity<Product> updateProduct(@PathVariable int pid, @RequestBody @Valid ProductRequest pr) {

        Product productResult = productService.getProductById(pid);

        //check target product if exists
        //if not return 404 product not found
        if(productResult == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProductById(pid, pr);

        Product returnProduct = productService.getProductById(pid);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnProduct);

    }

    @DeleteMapping("/products/{pid}")
    public ResponseEntity<?> deleteProduct(@PathVariable int pid) {

        productService.deleteProductById(pid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
