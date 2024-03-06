<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <h2 class="text-center mb-4 fw-normal">유저 정보</h2>
    <c:url var="user_delete" value="/mypage/delete.do">
        <c:param name="userID" value="${user.userId}"/>
    </c:url>
    <c:url var="user_edit" value="/mypage/update.do">
        <c:param name="userID" value="${user.userId}"/>
    </c:url>
    <div class="card">
        <div class="row text-center">
            <div class="col-md-6 mx-auto">
                <div class="mb-3">
                </div>
                <div class="mb-3">
                    <strong>아이디:</strong> ${sessionScope.user.userId}
                </div>
                <div class="mb-3">
                    <strong>이름:</strong> ${sessionScope.user.userName}
                </div>
                <div class="mb-3">
                    <strong>생년월일:</strong> ${sessionScope.user.userBirth}
                </div>
                <div class="mb-3">
                    <strong>권한:</strong> ${sessionScope.user.userAuth}
                </div>
                <div class="mb-3">
                    <strong>포인트:</strong> ${sessionScope.user.userPoint}
                </div>
                <div class="mb-3">
                    <strong>가입일:</strong> ${sessionScope.user.createdAt}
                </div>
                    <strong>최근 접속일:</strong> ${sessionScope.user.latestLoginAt}
            </div>
            <div class="btn-group" style="margin-top: 50px">
                <button type="button" class="btn btn-outline-primary" onclick=location.href="${user_edit}">회원 정보 수정
                </button>
                <form method="post" action="${user_delete}">
                    <button class="btn btn-outline-danger" id="deleteBtn">회원 탈퇴</button>
                </form>
            </div>
        </div>
    </div>
</div>

