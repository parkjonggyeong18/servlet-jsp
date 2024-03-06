<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center mb-4 fw-normal">마이 페이지</h1>

<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">상세정보</h5>
                            <p class="card-text">상세정보를 확인합니다.</p>
                            <a href="/mypage/userinfo.do" class="btn btn-primary">정보 보기</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">주문 내역</h5>
                            <p class="card-text">주문을 확인합니다.</p>
                            <a href="/mypage/order.do" class="btn btn-primary">주문 보기</a>
                        </div>
                    </div>
                </div>
                <c:if test="${sessionScope.loginID != null && sessionScope.userAuth == 'ROLE_ADMIN'}">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">관리자</h5>
                                <p class="card-text">쇼핑몰을 관리합니다.</p>
                                <a href="/admin/index.do" class="btn btn-primary">쇼핑몰 관리</a>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
