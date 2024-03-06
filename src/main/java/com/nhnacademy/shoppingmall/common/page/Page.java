package com.nhnacademy.shoppingmall.common.page;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Page<T> {
    private final List<List<T>> content = new ArrayList<>();
    @Getter
    private final int unitCount = 9;
    @Getter
    private final int totalPage;

    public Page(List<T> list) {
        this.totalPage = (int) Math.floor((double) list.size() / unitCount) + 1;
        pagination(list);
    }

    private void pagination(List<T> list){
        if(list == null){
            throw new NullPointerException("list가 null 입니다.");
        }
        for (int i = 0; i < totalPage; i++) {
            int start = i * unitCount;
            int end = Math.min(start + unitCount, list.size());
            List<T> sublist = list.subList(start, end);
            content.add(sublist);
        }
    }

    public List<T> pagingList(int page){
        if(page <= 0){
            throw new IllegalArgumentException("페이지는 양의 정수여야 합니다.");
        }
        if(page >  totalPage){
            throw new IllegalArgumentException("입력한 페이지 값이 총 페이지 수 보다 더 큽니다.");
        }
        return content.get(page - 1);
    }

}
