package com.angus.springbootmall.dao.Implement;

import com.angus.springbootmall.dao.OrderDao;
import com.angus.springbootmall.dao.SqlFactory;
import com.angus.springbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate JdbcTemplate;
    @Autowired
    private SqlFactory sqlFactory;

    @Override
    public Integer createOrder(Integer userId, Integer amount) {

        HashMap<String, Object> map = new HashMap<>();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        JdbcTemplate.update(sqlFactory.sql_createNewOrder(userId , amount, map) , new MapSqlParameterSource(map) , keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItems) {

        MapSqlParameterSource[] parameterSource = new MapSqlParameterSource[orderItems.size()];

        for(int i = 0; i < orderItems.size(); i++){
            OrderItem orderItem = orderItems.get(i);

            parameterSource[i] = new MapSqlParameterSource();
            parameterSource[i].addValue("order_id", orderId);
            parameterSource[i].addValue("product_id", orderItem.getProduct_id());
            parameterSource[i].addValue("quantity", orderItem.getQuantity());
            parameterSource[i].addValue("amount", orderItem.getAmount());
        }

        JdbcTemplate.batchUpdate(sqlFactory.sql_createOrderItems(orderId , orderItems) , parameterSource);

    }
}
