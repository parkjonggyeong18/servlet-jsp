package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;
import com.nhnacademy.shoppingmall.shoppingcart.repositroy.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingcart.service.impl.ShoppingCartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.POST, value = "/DeleteRecordAction.do")
public class DeleteShoppingCartController implements BaseController {
    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loginID = (String) req.getSession(false).getAttribute("loginID");
        List<ShoppingRecord> list = shoppingCartService.filterById(loginID);
        int productID = Integer.parseInt(req.getParameter("productID"));

        ShoppingRecord shoppingRecord = list.stream()
                .filter(item -> item.getProductID() == productID)
                .findFirst()
                .orElse(null);

        shoppingCartService.delete(shoppingRecord.getRecordID());

        return "redirect:/mypage/cart.do";
    }
}
