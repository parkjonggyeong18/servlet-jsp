package com.nhnacademy.shoppingmall.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CookieUtil {

    // 최근 본 상품 쿠키의 이름
    private static final String RECENTLY_VIEWED_COOKIE_NAME = "recentlyViewed";
    // 최대 최근 본 상품 수
    private static final int MAX_RECENTLY_VIEWED_PRODUCTS = 5;

    // 쿠키에 최근 본 상품 추가
    public static void addRecentlyViewedProduct(HttpServletRequest request, HttpServletResponse response, String productId) {
        Cookie[] cookies = request.getCookies();
        List<String> recentlyViewedProducts = new ArrayList<>();

        // 기존 쿠키 읽기
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(RECENTLY_VIEWED_COOKIE_NAME)) {
                    String[] products = cookie.getValue().split("-");
                    for (String product : products) {
                        recentlyViewedProducts.add(product);
                    }
                    break;
                }
            }
        }

        // 최근 본 상품 목록에 현재 상품 추가 (리스트 맨 앞에 추가)
        // 이미 목록에 있는지 확인
        if (!recentlyViewedProducts.contains(productId)) {
            // 최근 본 상품 목록에 현재 상품 추가 (리스트 맨 앞에 추가)
            recentlyViewedProducts.add(0, productId);

            // 최근 본 상품 수가 최대 개수를 초과하는 경우 가장 오래된 상품 삭제
            if (recentlyViewedProducts.size() > MAX_RECENTLY_VIEWED_PRODUCTS) {
                recentlyViewedProducts.remove(recentlyViewedProducts.size() - 1); // 가장 오래된 상품 제거
            }
        }

        // 쿠키 설정
        StringBuilder recentlyViewedBuilder = new StringBuilder();
        for (String product : recentlyViewedProducts) {
            recentlyViewedBuilder.append(product).append("-");
        }
        String recentlyViewed = recentlyViewedBuilder.toString();
        // 마지막 "-" 제거
        if (recentlyViewed.endsWith("-")) {
            recentlyViewed = recentlyViewed.substring(0, recentlyViewed.length() - 1);
        }

        Cookie cookie = new Cookie(RECENTLY_VIEWED_COOKIE_NAME, recentlyViewed);
        cookie.setMaxAge(30 * 24 * 60 * 60); // 30일 유효기간 설정 (초 단위)
        response.addCookie(cookie);
    }


    public static List<String> getRecentlyViewedProducts(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<String> recentlyViewedProducts = new ArrayList<>();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(RECENTLY_VIEWED_COOKIE_NAME)) {
                    String[] products = cookie.getValue().split("-");
                    for (String product : products) {
                        recentlyViewedProducts.add(product);
                    }
                    break;
                }
            }
        }

        return recentlyViewedProducts;
    }
}
