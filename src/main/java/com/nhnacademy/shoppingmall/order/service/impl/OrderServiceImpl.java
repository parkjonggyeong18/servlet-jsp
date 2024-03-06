package com.nhnacademy.shoppingmall.order.service.impl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.nhnacademy.shoppingmall.order.domain.OrderDetails;
import com.nhnacademy.shoppingmall.order.domain.Orders;

import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.order.service.OrderService;


import com.nhnacademy.shoppingmall.product.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService<Orders> {

    private final OrderRepository<Orders> orderRepository;
    public OrderServiceImpl(OrderRepository<Orders> orderRepository){
        this.orderRepository = orderRepository;}

    @Override
    public void save(Orders orders) {
        if(orders == null){
            throw new DomainNullPointerException(orders);
        }

        if(orderRepository.countById(orders.getOrderID()) == 1){
            throw new RuntimeException("이미 존재하는 ID 값입니다.");
        }

        orderRepository.save(orders);
    }

    @Override
    public Orders get(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Orders orders) {
        if(orders == null){
            throw new DomainNullPointerException(orders);
        }

        if(orderRepository.countById(orders.getOrderID()) == 0){
            throw new RuntimeException("이미 존재하지 않는 ID 값입니다.");
        }

        orderRepository.update(orders);
    }

    @Override
    public void delete(int id) {
        if(orderRepository.countById(id) == 0){
            throw new RuntimeException("이미 존재하지 않는 ID 값입니다.");
        } else{
            orderRepository.deleteById(id);
        }
    }

    @Override
    public List<Orders> toList() {
        List<Orders> list = new ArrayList<>();
        int count = orderRepository.totalCount().orElse(0);
        int id = 1;
        for (int i = 0; i < count; i++) {
            Orders Orders = get(id);
            while(Orders == null){
                id++;
                Orders = get(id);
            }
            list.add(Orders);
            id++;
        }
        return list;
    }
    public List<Orders> filterById(String orderID){
        if(orderID == null){
            throw new NullPointerException("cartID가 null 입니다.");
        }

        return toList().stream()
                .filter(element -> element.getUserID().equals(orderID))
                .collect(Collectors.toList());
    }
    public int saveAndGetOrderId(Orders orders) {

        return orderRepository.saveAndGetOrderId(orders);
    }
    @Override
    public List<Orders> getOrdersByUserId(String userId) {
        return orderRepository.getOrdersByUserId(userId);
    }
}