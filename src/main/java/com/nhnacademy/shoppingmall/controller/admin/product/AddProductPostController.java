package com.nhnacademy.shoppingmall.controller.admin.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/addProductAction.do")
public class AddProductPostController implements BaseController {
    private final ProductService<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int categoryID = Integer.parseInt(req.getParameter("categories"));
        String modelNumber = req.getParameter("product_modelnumber");
        String modelName = req.getParameter("product_modelname");
        int quantity = Integer.parseInt(req.getParameter("product_quantity"));
        String productImage = req.getParameter("product_image");
        int price = Integer.parseInt(req.getParameter("product_price"));
        String comment = req.getParameter("product_comment");

        // 모델 넘버로 이미 존재하는 상품을 찾음
        Product existingProduct = productService.findByModelNumber(modelNumber);

        if (existingProduct != null) {
            // 이미 존재하는 상품이 있으면 해당 상품을 수정
            existingProduct.setCategoryID(categoryID);
            existingProduct.setModelName(modelName);
            existingProduct.setQuantity(quantity);
            existingProduct.setProductImage(productImage);
            existingProduct.setUnitCost(price);
            existingProduct.setComment(comment);
            productService.update(existingProduct);
        } else {
            // 존재하지 않는 상품이면 새로 추가
            Product newProduct = new Product(categoryID, modelNumber, modelName, quantity, productImage, price, comment);
            productService.save(newProduct);
        }

        return "redirect:/admin/index.do";
    }
}
