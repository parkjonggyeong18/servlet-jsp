package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.controller.admin.UserListController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET, value = "/search.do")
public class SearchController implements BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserListController.class);
    ProductService<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String searchQuery = req.getParameter("query");
        if (searchQuery == null || searchQuery.isEmpty()) {
            return "redirect: /index.do";
        }
        List<Product> searchResults = productService.searchProducts(searchQuery);
        req.setAttribute("searchResults", searchResults);
        log.info("searchResults");
        return "shop/main/search";
    }
}
