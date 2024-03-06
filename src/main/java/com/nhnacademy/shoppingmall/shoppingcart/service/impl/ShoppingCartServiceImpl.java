package com.nhnacademy.shoppingmall.shoppingcart.service.impl;


import com.nhnacademy.shoppingmall.product.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;
import com.nhnacademy.shoppingmall.shoppingcart.repositroy.ShoppingCartRepository;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository<ShoppingRecord> shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository<ShoppingRecord> shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingRecord get(int id) {

        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ShoppingRecord shoppingRecord) {
        if(shoppingRecord == null){
            throw new DomainNullPointerException(shoppingRecord);
        }
        if(shoppingCartRepository.countById(shoppingRecord.getRecordID()) == 1){
            throw new RuntimeException("존재하는 레코드 입니다.");
        }
        shoppingCartRepository.save(shoppingRecord);
    }

    @Override
    public void update(ShoppingRecord shoppingRecord) {
        if(shoppingRecord == null){
            throw new DomainNullPointerException(shoppingRecord);
        }
        if(shoppingCartRepository.countById(shoppingRecord.getRecordID()) == 0 ){
            throw new RuntimeException("변경할 수 없습니다.");
        } else{
            shoppingCartRepository.update(shoppingRecord);
        }
    }

    @Override
    public void delete(int id) {
        if(shoppingCartRepository.countById(id) == 0 ){
            throw new RuntimeException("삭제할 상품이 없습니다.");
        } else{
            shoppingCartRepository.deleteById(id);
        }
    }

    @Override
    public List<ShoppingRecord> toList() {
        List<ShoppingRecord> list = new ArrayList<>();
        int count = shoppingCartRepository.totalCount().orElse(0);
        int id = 1;
        for (int i = 0; i < count; i++) {
            ShoppingRecord shoppingRecord = get(id);
            while(shoppingRecord == null){
                id++;
                shoppingRecord = get(id);
            }
            list.add(shoppingRecord);
            id++;
        }
        return list;
    }

    public List<ShoppingRecord> filterById(String cartID){
        if(cartID == null){
            throw new NullPointerException("cartID가 null 입니다.");
        }

        return toList().stream()
                .filter(element -> element.getCartID().equals(cartID))
                .collect(Collectors.toList());
    }
}
