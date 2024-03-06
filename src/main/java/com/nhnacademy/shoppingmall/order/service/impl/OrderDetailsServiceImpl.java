package com.nhnacademy.shoppingmall.order.service.impl;

import com.nhnacademy.shoppingmall.order.domain.OrderDetails;

import com.nhnacademy.shoppingmall.order.repository.OrderDetailsRepository;
import com.nhnacademy.shoppingmall.order.service.OrderDetailsService;
import com.nhnacademy.shoppingmall.product.exception.DomainNullPointerException;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService<OrderDetails> {
    private final OrderDetailsRepository<OrderDetails> orderDetailsRepository;

    public OrderDetailsServiceImpl(OrderDetailsRepository<OrderDetails> orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;

    }

    @Override
    public void save(OrderDetails orderDetails) {
        if(orderDetails== null){
            throw new DomainNullPointerException(orderDetails);
        }

        if(orderDetailsRepository.countById(orderDetails.getOrderID()) == 1){
            throw new RuntimeException("이미 존재하는 ID 값입니다.");
        }

        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public OrderDetails get(int id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public void update(OrderDetails orderDetails) {
        if(orderDetails == null){
            throw new DomainNullPointerException(orderDetails);
        }

        if(orderDetailsRepository.countById(orderDetails.getOrderID()) == 0){
            throw new RuntimeException("이미 존재하지 않는 ID 값입니다.");
        }

        orderDetailsRepository.update(orderDetails);
    }

    @Override
    public void delete(int id) {
        if(orderDetailsRepository.countById(id) == 0){
            throw new RuntimeException("이미 존재하지 않는 ID 값입니다.");
        } else{
            orderDetailsRepository.deleteById(id);
        }
    }

    @Override
    public List<OrderDetails> toList() {
        List<OrderDetails> list = new ArrayList<>();
        int count = orderDetailsRepository.totalCount().orElse(0);
        int id = 1;
        for (int i = 0; i < count; i++) {
            OrderDetails orderDetails = get(id);
            while(orderDetails == null){
                id++;
                orderDetails = get(id);
            }
            list.add(orderDetails);
            id++;
        }
        return list;
    }
    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        return orderDetailsRepository.getOrderDetailsByOrderId(orderId);
    }
}

