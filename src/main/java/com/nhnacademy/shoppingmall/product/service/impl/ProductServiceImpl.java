package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService<Product> {
    private final ProductRepository<Product> productRepository;

    public ProductServiceImpl(ProductRepository<Product> productRepository) {
        this.productRepository = productRepository;}

    @Override
    public Product get(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void save(Product product) {
        if(product == null){
            throw new DomainNullPointerException(product);
        }
        if(productRepository.countById(product.getProductID()) == 1){
            throw new RuntimeException("존재하는 상품 ID 입니다.");
        }
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        if(product == null){
            throw new DomainNullPointerException(product);
        }
        if(productRepository.countById(product.getProductID()) == 0 ){
            throw new RuntimeException("Update할 상품이 없습니다.");
        } else{
            productRepository.update(product);
        }
    }

    @Override
    public void delete(int id) {
        if (!productRepository.findById(id).isPresent()) {
            throw new RuntimeException("삭제할 상품이 없습니다.");
        } else{
            productRepository.deleteById(id);
        }
    }

    @Override
    public List<Product> toList() {
        List<Product> list = new ArrayList<>();
        int count = productRepository.totalCount().orElse(0);
        int id = 1;
        for (int i = 0; i < count; i++) {
            Product product = get(id);
            while(product == null){
                id++;
                product = get(id);
            }
            list.add(product);
            id++;
        }
        return list;
    }
    public Product findByModelNumber(String modelNumber) {
        return productRepository.findByModelNumber(modelNumber).orElse(null);
    }
    @Override
    public List<Product> searchProducts(String query) {

        return productRepository.findByKeyword(query);
    }
}
