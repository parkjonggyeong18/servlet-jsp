<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="g-3">
    <h1>${user.userName}님의 장바구니</h1>
    <br/>
    <h2><c:if test="${empty map}">장바구니가 비었습니다.</c:if></h2>
    <div>
        <c:forEach var="item" items="${map}">
            <div class="col" style="margin-bottom: 30px">
                <div>상품 이름 : ${item.key.modelName}</div>
                <div>상품 가격 : ${item.key.unitCost}</div>
                <div class="btn-group">
                    <c:url var="quantity_edit" value="/UpdateCartAction.do">
                        <c:param name="productID" value="${item.key.productID}"/>
                    </c:url>
                    <c:url var="record_delete" value="/DeleteRecordAction.do">
                        <c:param name="productID" value="${item.key.productID}"/>
                    </c:url>
                    <form method="post" action="${quantity_edit}">
                        <input type="number" name="quantity" id="quantity" value="${item.value}" style="width: 50px" minlength="0" maxlength="${item.key.quantity}" required>
                        <button class="btn btn-sm btn-outline-secondary">수량 변경</button>
                    </form>

                    <form method="post" action="${record_delete}">
                        <button class="btn btn-sm btn-outline-secondary" >삭제</button>
                    </form>

                        <form method="post" action="/orderAction.do">
                            <input type="hidden" name="userID" value="${user.userId}">
                            <input type="hidden" name="productID" value="${item.key.productID}">
                            <input type="hidden" name="quantity" value="${item.value}">
                            <button class="btn btn-sm btn-outline-secondary">구매하기</button>
                        </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
