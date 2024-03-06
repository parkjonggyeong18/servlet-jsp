<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center mb-4 fw-normal">관리자 페이지</h1>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">상품 관리</h5>
                            <p class="card-text">상품 목록을 확인합니다.</p>
                            <a href="/admin/product.do" class="btn btn-primary">상품 목록</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">상품 관리</h5>
                            <p class="card-text">상품을 추가 및 교체합니다.</p>
                            <a href="/admin/addProduct.do" class="btn btn-primary">상품 추가</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">카테고리 관리</h5>
                            <p class="card-text">카테고리 확인 및 추가합니다.</p>
                            <a href="/admin/addCategory.do" class="btn btn-primary">카테고리 추가</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">유저 관리</h5>
                            <p class="card-text">유저 목록을 확인합니다.</p>
                            <a href="/admin/userlist.do" class="btn btn-primary">유저 목록</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>