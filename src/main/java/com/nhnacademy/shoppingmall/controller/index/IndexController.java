package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET,value = {"/index.do"})
public class IndexController implements BaseController {
    private final ProductService<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> list = productService.toList();
        Page<Product> page = new Page<>(list);
        req.setAttribute("totalPage", page.getTotalPage());

        int currentPage = Objects.nonNull(req.getParameter("page")) ? Integer.parseInt(req.getParameter("page")) : 1;

        List<Product> pagingList = page.pagingList(currentPage);
        req.setAttribute("products", pagingList);
        return "shop/main/index";
    }
}