package com.angus.springbootmall.service.Implement;

import com.angus.springbootmall.dao.OrderDao;
import com.angus.springbootmall.dao.ProductDao;
import com.angus.springbootmall.dto.OrderRequset;
import com.angus.springbootmall.model.Order;
import com.angus.springbootmall.model.OrderItem;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;


    @Transactional
    @Override
    public Integer createOrder(int userId, OrderRequset orderReq) {

        Integer orderPrice = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(int i = 0 ; i < orderReq.getOrderList().size() ; i++){

            int tempProductId = orderReq.getOrderList().get(i).getProductId();
            Product tempProduct = productDao.getProductById(tempProductId);

            orderPrice = orderPrice + tempProduct.getPrice() * orderReq.getOrderList().get(i).getQuantity();


            OrderItem tempOrderItem = new OrderItem();
            tempOrderItem.setProduct_id(tempProductId);
            tempOrderItem.setQuantity(orderReq.getOrderList().get(i).getQuantity());
            tempOrderItem.setAmount(tempProduct.getPrice() * orderReq.getOrderList().get(i).getQuantity());

            orderItemList.add(tempOrderItem);
        }

        Integer orderId = orderDao.createOrder(userId , orderPrice);
        orderDao.createOrderItem(orderId , orderItemList);


        return orderId;
    }


    @Override
    public Order getOrderById(Integer orderId) {

        Order returnOrder = orderDao.getOrderById(orderId);

        List<OrderItem> relatedOrderItems = orderDao.getItemsByOrderId(orderId);

        returnOrder.setOrderItemList(relatedOrderItems);

        return returnOrder;
    }
}
