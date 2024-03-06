package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.controller.admin.UserListController;
import com.nhnacademy.shoppingmall.order.domain.OrderDetails;
import com.nhnacademy.shoppingmall.order.domain.Orders;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderDetailsRepositoryImpl;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderDetailsService;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderDetailsServiceImpl;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/order.do")
public class OrderViewController implements BaseController {
    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl(new OrderDetailsRepositoryImpl());
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private static final Logger log = LoggerFactory.getLogger(UserListController.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = (String) req.getSession(false).getAttribute("loginID"); // 로그인한 사용자 ID 가져오기
        // 사용자의 주문 내역 가져오기
        List<Orders> orders = orderService.getOrdersByUserId(userId);
        List<OrderDetails> allOrderDetails = new ArrayList<>();
        for (Orders order : orders) {
            List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsByOrderId(order.getOrderID());
            allOrderDetails.addAll(orderDetails);
        }
        // 모든 주문 내역을 요청 객체에 저장
        req.setAttribute("orderDetails", allOrderDetails);
        req.setAttribute("orders", orders);
        return "/shop/userInfo/order";
    }
}
