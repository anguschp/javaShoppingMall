package com.angus.springbootmall.service.Implement;

import com.angus.springbootmall.dao.OrderDao;
import com.angus.springbootmall.dao.ProductDao;
import com.angus.springbootmall.dao.UserDao;
import com.angus.springbootmall.dto.OrderQueryParameter;
import com.angus.springbootmall.dto.OrderRequset;
import com.angus.springbootmall.model.Order;
import com.angus.springbootmall.model.OrderItem;
import com.angus.springbootmall.model.Product;
import com.angus.springbootmall.model.User;
import com.angus.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Transactional
    @Override
    public Integer createOrder(int userId, OrderRequset orderReq) {

        User checkUser = userDao.getUserById(userId);

        if(checkUser == null){
            log.warn("User not exist {}", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        Integer orderPrice = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(int i = 0 ; i < orderReq.getOrderList().size() ; i++){

            int tempProductId = orderReq.getOrderList().get(i).getProductId();
            Product tempProduct = productDao.getProductById(tempProductId);

            if(tempProduct == null){
                log.warn("Product does not exist in the system: {}", tempProductId);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //check if the stock have enough quantity for sale
            if(orderReq.getOrderList().get(i).getQuantity() > tempProduct.getStock())
            {
                log.warn("Product: {} has only {} on stock, cannot fullfill request amonut", tempProductId  , tempProduct.getStock() );
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(tempProduct.getProduct_id(), tempProduct.getStock() - orderReq.getOrderList().get(i).getQuantity() );


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


    @Override
    public List<Order> getUserAllOrders(OrderQueryParameter orderParam) {

        User checkUser = userDao.getUserById(orderParam.getUserId());

        if(checkUser == null){
            log.warn("The user id {} is not exist, ", orderParam.getUserId(), "no order found.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<Order> returnOrderList = orderDao.getUserAllOrders(orderParam);

        for(Order listItem : returnOrderList)
        {
            Integer tempOrderId = listItem.getOrder_id();
            List<OrderItem> returnOrderItemList = orderDao.getItemsByOrderId(tempOrderId);

            listItem.setOrderItemList(returnOrderItemList);
        }

        return returnOrderList;
    }


    @Override
    public Integer getFilterOrderCount(OrderQueryParameter orderQueryParameter) {

        Integer resultCount = orderDao.getFilterOrderCount(orderQueryParameter);

        return resultCount;
    }
}
