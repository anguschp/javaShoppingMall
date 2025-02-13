package com.angus.springbootmall.dao.Implement;

import com.angus.springbootmall.dao.ProductDao;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.rowmapper.productRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate JdbcTemplate;

    @Override
    public Product getProductById(int productId) {

        String sql = "select product_id, product_name, category, image_url, price, stock, " +
                    "description, created_date, last_modified_date " +
                    "from products where product_id = :productId";

        HashMap<String , Object> params = new HashMap<>();
        params.put("productId", productId);


        List<Product> queryResult = JdbcTemplate.query(sql, params, new productRowMapper());

        if(queryResult.size() > 0)
        {
            return queryResult.get(0);
        }
        else
        {
            return null;
        }
    }
}
