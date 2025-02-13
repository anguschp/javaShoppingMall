package com.angus.springbootmall.rowmapper;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class productRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product tempProduct = new Product();

        tempProduct.setProduct_id(rs.getInt("product_id"));
        tempProduct.setProduct_name(rs.getString("product_name"));

        tempProduct.setCategory(ProductCategory.valueOf(rs.getString("category")));
        tempProduct.setImage_url(rs.getString("image_url"));
        tempProduct.setPrice(rs.getInt("price"));
        tempProduct.setStock(rs.getInt("stock"));
        tempProduct.setProduct_desc(rs.getString("description"));
        tempProduct.setCreate_date(rs.getTimestamp("created_date"));
        tempProduct.setLast_modify_date(rs.getTimestamp("last_modified_date"));

        return tempProduct;
    }
}
