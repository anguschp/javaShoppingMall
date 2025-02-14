package com.angus.springbootmall.dao.Implement;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.dao.ProductDao;
import com.angus.springbootmall.dao.ProductQueryParameter;
import com.angus.springbootmall.dao.SqlFactory;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.rowmapper.productRowMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
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
    @Autowired
    private SqlFactory sqlFactory;
    @Autowired
    private ParameterNamesModule parameterNamesModule;


    @Override
    public Product getProductById(int productId) {

        HashMap<String , Object> params = new HashMap<>();
        params.put("productId", productId);


        List<Product> queryResult = JdbcTemplate.query(sqlFactory.sql_SearchStudentById(), params, new productRowMapper());

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
        JdbcTemplate.update(sqlFactory.sql_createNewProduct(), new MapSqlParameterSource(params) , keyHolder);

        int returnId = keyHolder.getKey().intValue();

        System.out.println("Created a new product in database: productId = " + returnId);

        return returnId;
    }


    public void updateProductById(int ProductId, ProductRequest productRequest)
    {

        HashMap<String, Object> param = new HashMap<>();
        param.put("pID", ProductId);
        param.put("pName", productRequest.getProduct_name());
        param.put("pCat", productRequest.getCategory().toString());
        param.put("pURL", productRequest.getImage_url());
        param.put("pPrice", productRequest.getPrice());
        param.put("pStock", productRequest.getStock());
        param.put("pDesc", productRequest.getProduct_desc());


        try{

            JdbcTemplate.update(sqlFactory.sql_updateProductById(), param);
            System.out.println("Product has been updated successfully.");

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void deleteProductById(int id) {

        HashMap<String , Object> params = new HashMap<>();
        params.put("pID" , id);

        try
        {
            JdbcTemplate.update(sqlFactory.sql_deleteProductById(id) , params);
            System.out.println("Product has been deleted successfully.");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }


    @Override
    public List<Product> getAllProducts(ProductQueryParameter queryParam) {

        HashMap<String , Object> params = new HashMap<>();

        List<Product> resultProductList = JdbcTemplate.query(sqlFactory.sql_getAllProducts(queryParam, params) , params , new productRowMapper());

        return resultProductList;

    }

}
