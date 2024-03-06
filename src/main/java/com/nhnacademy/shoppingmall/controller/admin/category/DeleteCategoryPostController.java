package com.nhnacademy.shoppingmall.controller.admin.category;

import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/deleteCategoryAction.do")
public class DeleteCategoryPostController implements BaseController {
    CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productIdString = req.getParameter("productID");
        req.setAttribute("successMessage", false);
        req.setAttribute("errorMessage", false);

        try {
            // 상품 ID가 숫자인지 확인
            if (!isNumeric(productIdString)) {
                throw new RuntimeException("상품 ID는 숫자로 입력되어야 합니다.");
            }

            int productId = Integer.parseInt(productIdString);


            categoryService.delete(productId);
            req.setAttribute("successMessage", true);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", true);
        } catch (RuntimeException e) {
            req.setAttribute("errorMessage", true);
        }

        return "shop/admin/deleteCategory";
    }
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
