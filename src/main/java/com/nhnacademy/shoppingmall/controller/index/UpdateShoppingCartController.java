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

@RequestMapping(method = RequestMapping.Method.POST, value = "/UpdateCartAction.do")
public class UpdateShoppingCartController implements BaseController {
    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loginID = (String) req.getSession(false).getAttribute("loginID");
        int productID = Integer.parseInt(req.getParameter("productID"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        List<ShoppingRecord> list = shoppingCartService.filterById(loginID);

        ShoppingRecord shoppingRecord = list.stream()
                .filter(item -> item.getProductID() == productID)
                .findFirst()
                .orElse(null);
        shoppingRecord.setQuantity(quantity);
        shoppingCartService.update(shoppingRecord);

        return "redirect:/mypage/cart.do";
    }
}
