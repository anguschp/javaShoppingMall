package com.angus.springbootmall.dao;

import org.springframework.stereotype.Component;

@Component
public class SqlFactory {


    public String sql_SearchStudentById()
    {
        String param1 = ":productId";

         String sqlStatement =
                 "select product_id, product_name, category, image_url, price, stock, " +
                "product_desc, created_date, last_modified_date " +
                "from products where product_id = " + param1;

         return sqlStatement;
    }


    public String sql_createNewProduct()
    {

        String param1 = ":_name";
        String param2 = ":_category";
        String param3 = ":_imgURL";
        String param4 = ":_price";
        String param5 = ":_stock";
        String param6 = ":_desc";

        String sqlStatement =
        "insert into products(product_name , category , image_url, price, stock, product_desc, created_date, last_modified_date)" +
         "values(" + param1 + " , " + param2 + " , " + param3 + " , " + param4+ " , " + param5 + " , " + param6 + " , current_timestamp() , current_timestamp() )";

        return sqlStatement;
    }

    public String sql_updateProductById()
    {
        String sqlStatement = "update products set product_name = :pName , category = :pCat , image_url = :pURL, "+
                                "price = :pPrice, stock = :pStock , product_desc = :pDesc , last_modified_date = current_timestamp() "+
                                "where product_id = :pID";


        return sqlStatement;
    }



}
