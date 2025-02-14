package com.angus.springbootmall.controller;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.dao.ProductQueryParameter;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) ProductCategory category ,
                                                     @RequestParam(required = false) String searchString,
                                                     @RequestParam(defaultValue = "created_date") String orderBy,
                                                     @RequestParam(defaultValue = "desc") String sort,
                                                     @RequestParam(defaultValue = "3") @Max(1000) @Min(0) Integer pageLimit,
                                                     @RequestParam(defaultValue = "0") @Min(0) Integer offSet)
    {
        ProductQueryParameter queryParam = new ProductQueryParameter();

        queryParam.setCategory(category);
        queryParam.setSearchString(searchString);
        queryParam.setOrderBy(orderBy);
        queryParam.setSortingType(sort);
        queryParam.setPageLimit(pageLimit);
        queryParam.setOffSet(offSet);

        List<Product> productList = productService.getAllProducts(queryParam);

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
