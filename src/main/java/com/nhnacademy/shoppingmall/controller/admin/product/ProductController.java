package com.nhnacademy.shoppingmall.controller.admin.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.common.util.CookieUtil;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET ,value = "/admin/product.do")
public class ProductController implements BaseController {
    ProductService<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());
    CategoryService<Category> categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> list = productService.toList();
        List<Category> lists = categoryService.toList();

        Page<Product> page = new Page<>(list);
        req.setAttribute("totalPage", page.getTotalPage());
        int currentPage = Objects.nonNull(req.getParameter("page")) ? Integer.parseInt(req.getParameter("page")) : 1;
        List<Product> pagingList = page.pagingList(currentPage);
        req.setAttribute("products", pagingList);
        req.setAttribute("categories", lists);
        return "shop/admin/productList";
    }
}
