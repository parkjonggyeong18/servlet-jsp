package com.nhnacademy.shoppingmall.shoppingcart.service;


import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;

import java.util.List;

public interface ShoppingCartService extends CategoryService<ShoppingRecord> {
    List<ShoppingRecord> filterById(String cartID);
}
