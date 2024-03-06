
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
                    <strong>아이디:</strong> ${sessionScope.address.userId}
                </div>
                <div class="mb-3">
                    <strong>주소 번호:</strong> ${sessionScope.address.addressId}
                </div>
                </div>
                <strong>주소:</strong> ${sessionScope.address.address}
            </div>
            <div class="btn-group" style="margin-top: 50px">
                <button type="button" class="btn btn-outline-primary" onclick=location.href="${user_edit}">주소 수정
                </button>
                <form method="post" action="${user_delete}">
                    <button class="btn btn-outline-danger" id="deleteBtn">주소 삭제</button>
                </form>
            </div>
        </div>
    </div>
</div>

