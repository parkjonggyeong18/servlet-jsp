package com.nhnacademy.shoppingmall.controller.admin.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Category;

import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/addCategory.do")
public class AddCategoryController implements BaseController {
    CategoryService<Category> categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> list = categoryService.toList();
        req.setAttribute("categories", list);
        return "shop/admin/addCategory";
    }
}
