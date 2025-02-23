package com.angus.springbootmall.dao;

import com.angus.springbootmall.constant.ProductCategory;
import com.angus.springbootmall.dto.*;
import com.angus.springbootmall.model.OrderItem;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
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


    public String sql_createNewOrder(Integer userId, Integer totalAmount , HashMap map)
    {

        String sqlStatement = "insert into `order`(user_id , total_amount , created_date , last_modified_date) " +
                "values (:user_id , :totalAmount , current_timestamp() , current_timestamp())";

        map.put("user_id" , userId);
        map.put("totalAmount" , totalAmount);

        return sqlStatement;
    }

    public String sql_createOrderItems(int orderId , List<OrderItem> orderItems)
    {
        String sqlStatement = "insert into order_item(order_id , product_id , quantity , amount) "+
                "values ( :order_id , :product_id , :quantity , :amount )";


        return sqlStatement;

    }


    public String sql_getOrderById(Integer orderId , HashMap map)
    {
        String sqlStatement = "select order_id , user_id , total_amount , created_date , last_modified_date " +
                                "from `order` where order_id = :order_id";

        map.put("order_id" , orderId);

        return sqlStatement;

    }


    public String getItemsByOrderId(Integer orderId , HashMap map)
    {
        String sqlStatement = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount," +
                "p.product_name, p.image_url FROM order_item oi " +
                "left join products p " +
                "on oi.product_id = p.product_id " +
                "where oi.order_id = :order_id ;";

        map.put("order_id" , orderId);

        return sqlStatement;
    }


    public String sql_getAllUserOrders(HashMap map , OrderQueryParameter queryParam)
    {
        String sqlStatement = "select order_id, user_id, total_amount, created_date , last_modified_date from `order` where 1=1";

        if(queryParam.getUserId() != null)
        {
            sqlStatement = sqlStatement + " and user_id = :userId";
            map.put("userId" , queryParam.getUserId());
        }


        //sorting order
        sqlStatement = sqlStatement + " order by created_date DESC";

        //pagination
        sqlStatement = sqlStatement + " limit :pageLimit OFFSET :pageOffset";
        map.put("pageLimit" , queryParam.getPageLimit());
        map.put("pageOffset" , queryParam.getOffset());

        return sqlStatement;
    }


    public String sql_getFilterOrderCount(HashMap map , OrderQueryParameter queryParam)
    {
        String sqlStatement = "select count(*) from `order` where 1=1";

        if(queryParam.getUserId() != null)
        {
            sqlStatement = sqlStatement + " AND user_id = :userId";
            map.put("userId" , queryParam.getUserId());
        }

        return sqlStatement;
    }

    public static String updateProductStock(Integer productId, Integer stockChange, HashMap map)
    {
        String sqlStatment = "UPDATE products SET stock = :stockChange , last_modified_date = current_timestamp() where  product_id = :productId";

        map.put("stockChange" , stockChange);
        map.put("productId" , productId);

        return sqlStatment;
    }

}
