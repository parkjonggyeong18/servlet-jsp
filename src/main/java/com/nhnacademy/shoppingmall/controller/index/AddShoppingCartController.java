package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;
import com.nhnacademy.shoppingmall.shoppingcart.repositroy.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingcart.service.impl.ShoppingCartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.POST, value = "/AddCartAction.do")
public class AddShoppingCartController implements BaseController {
    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loginID = (String) req.getSession(false).getAttribute("loginID");
        int productID = Integer.parseInt(req.getParameter("productID"));
        List<ShoppingRecord> list = shoppingCartService.filterById(loginID);
        for (ShoppingRecord rc: list) {
            if(rc.getProductID() == productID){
                req.setAttribute("existErrMsg", "이미 등록된 상품입니다.");
                req.setAttribute("existProductID", productID);
                return "/shop/main/index";
            }
        }
        ShoppingRecord shoppingRecord = new ShoppingRecord(loginID, 1, productID, LocalDateTime.now());
        shoppingCartService.save(shoppingRecord);
        return "redirect:/mypage/cart.do";
    }
}
