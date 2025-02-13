package com.angus.springbootmall.service.Implement;

import com.angus.springbootmall.dao.ProductDao;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }


}
