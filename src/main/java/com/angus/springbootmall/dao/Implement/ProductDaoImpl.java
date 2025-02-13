package com.angus.springbootmall.dao.Implement;

import com.angus.springbootmall.dao.ProductDao;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.rowmapper.productRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate JdbcTemplate;

    @Override
    public Product getProductById(int productId) {

        String sql = "select product_id, product_name, category, image_url, price, stock, " +
                    "product_desc, created_date, last_modified_date " +
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


    @Override
    public int createProduct(ProductRequest productRequest)
    {


        String sql = "insert into products(product_name , category , image_url, price, stock, product_desc, created_date, last_modified_date)"
                    + "values(:_name , :_category , :_imgURL , :_price , :_stock , :_desc , current_timestamp() , current_timestamp() )";

        HashMap<String , Object> params = new HashMap<>();
        params.put("_name" , productRequest.getProduct_name());
        params.put("_category" , productRequest.getCategory().toString());
        params.put("_imgURL" , productRequest.getImage_url());
        params.put("_price" , productRequest.getPrice());
        params.put("_stock" , productRequest.getStock());
        if(productRequest.getProduct_desc() != null)
        {
            params.put("_desc" , productRequest.getProduct_desc());
        }


        KeyHolder keyHolder = new GeneratedKeyHolder();
        JdbcTemplate.update(sql, new MapSqlParameterSource(params) , keyHolder);

        int returnId = keyHolder.getKey().intValue();

        System.out.println("Created a new product in database: productId = " + returnId);

        return returnId;
    }



}
