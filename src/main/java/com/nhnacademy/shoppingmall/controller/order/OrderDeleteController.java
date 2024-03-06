package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.domain.OrderDetails;
import com.nhnacademy.shoppingmall.order.domain.Orders;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderDetailsRepositoryImpl;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderDetailsService;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderDetailsServiceImpl;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.POST, value = "/cancelOrder.do")
public class OrderDeleteController implements BaseController {
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl(new OrderDetailsRepositoryImpl());
    private final ProductService<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int orderID = Integer.parseInt(req.getParameter("orderID")); // 주문 ID 받기

            // 주문 정보 찾기
            Orders order = orderService.get(orderID);
            List<OrderDetails> orderDetailsList = orderDetailsService.getByOrderID(orderID);

            // 주문 상태를 취소로 변경
            order.setOrderStatus("Cancelled");
            orderService.update(order);
            // 상품 재고 수량 복구
            for (OrderDetails orderDetails : orderDetailsList) {
                Product product = productService.get(orderDetails.getProductID());
                product.setQuantity(product.getQuantity() + orderDetails.getQuantity());
                productService.update(product);
            }



        // 주문 내역 페이지로 리다이렉트
        return "redirect:/orderHistory.do";
    }
}
