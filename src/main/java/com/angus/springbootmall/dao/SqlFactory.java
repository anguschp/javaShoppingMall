package com.angus.springbootmall.dao;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.dto.ProductRequest;
import com.angus.springbootmall.dto.UserRegisterRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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


    public String sql_deleteProductById(int _productId)
    {
        String param1 = ":pID";

        String sqlStatement = "delete from products where product_id = " + param1;

        return sqlStatement;
    }

    public String sql_getAllProducts(ProductQueryParameter queryParam ,  HashMap hashMap)
    {
        //default sql statement
        String sqlStatement = "select product_id , product_name , category , image_url , price , stock , product_desc, created_date ," +
                                "last_modified_date from products where 1=1";


        sqlStatement = addFilterSql(sqlStatement , hashMap , queryParam);

        //sorting Products
        sqlStatement = sqlStatement + " Order by " + queryParam.getOrderBy() + " " + queryParam.getSortingType();

        //pagination
        sqlStatement = sqlStatement + " limit :pageLimit OFFSET :pageOffset";
        hashMap.put("pageLimit" , queryParam.getPageLimit());
        hashMap.put("pageOffset" , queryParam.getOffSet());


        return sqlStatement;

    }


    public String sql_getProductsCount(ProductQueryParameter queryParam , HashMap hashMap)
    {
        String sqlStatement = "select count(*) from products where 1=1";

        sqlStatement = addFilterSql(sqlStatement , hashMap , queryParam);

        return sqlStatement;

    }

    private String addFilterSql(String sqlStatement, Map<String, Object> hashMap, ProductQueryParameter queryParam)
    {
        //filter criteria
        if(queryParam.getCategory() != null)
        {
            sqlStatement = sqlStatement + " AND category = :pCategory";
            hashMap.put("pCategory", queryParam.getCategory().name());
        }

        if(queryParam.getSearchString() != null)
        {
            sqlStatement = sqlStatement + " AND product_name like :searchString";
            hashMap.put("searchString" , "%" + queryParam.getSearchString() + "%");
        }


        return sqlStatement;
    }


    public String sql_createUser(UserRegisterRequest registerReq , HashMap hashMap)
    {

        String sqlStatement = "insert into user(email, password, created_date, last_modified_date) " +
        "values (:inputEmail , :inputPassword , current_timestamp() , current_timestamp())" ;

        hashMap.put("inputEmail" , registerReq.getEmail());
        hashMap.put("inputPassword" , registerReq.getPassword());

        return sqlStatement;
    }

    public String sql_getUserById(Integer userId , HashMap hashMap)
    {
        String sqlStatement = "select user_id, email, password , created_date , last_modified_date from user where user_id = :userID";
        hashMap.put("userID" , userId);

        return sqlStatement;

    }


    public String getUserByEmail(String reqEmail , HashMap map)
    {
        String sqlStatement = "Select user_id, email, password , created_date, last_modified_date from user where email = :email";

        map.put("email" , reqEmail);

        return sqlStatement;

    }



}
