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
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;
import com.nhnacademy.shoppingmall.shoppingcart.repositroy.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingcart.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(method = RequestMapping.Method.POST, value = "/orderAction.do")
public class OrderController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl(new OrderDetailsRepositoryImpl());
    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    private final ProductService<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loginID = (String) req.getSession(false).getAttribute("loginID");
        String productId = req.getParameter("productID"); // 상품 ID 받기
        int quantity = Integer.parseInt(req.getParameter("quantity")); // 수량 받기

        // 사용자, 상품, 주문 정보 찾기
        Product product = productService.get(Integer.parseInt(productId));

        if (product.getQuantity() < quantity) {
            req.setAttribute("errMsg", "재고 수량보다 많이 주문할 수 없습니다.");
            return "/shop/main/index";
        }

        Orders order = new Orders(loginID, LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        int orderId = orderService.saveAndGetOrderId(order);


        // 주문 내역 생성
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderID(orderId);
        orderDetails.setProductID(Integer.parseInt(productId));
        orderDetails.setQuantity(quantity);
        orderDetails.setUnitCost(product.getUnitCost());
        orderDetailsService.save(orderDetails);

        int productID = Integer.parseInt(req.getParameter("productID"));
        product.setQuantity(product.getQuantity() - quantity);

        productService.update(product);

        List<ShoppingRecord> list = shoppingCartService.filterById(loginID);

        ShoppingRecord shoppingRecord = list.stream()
                .filter(item -> item.getProductID() == productID)
                .findFirst()
                .orElse(null);

        shoppingCartService.delete(shoppingRecord.getRecordID());


        // 주문 완료 페이지로 리다이렉트
        return "redirect:/index.do";
    }
}
