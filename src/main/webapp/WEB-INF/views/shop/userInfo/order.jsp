<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>주문 내역</h1>
<table class="table">
    <thead>
    <tr>
        <th>주문번호</th>
        <th>상품번호</th>
        <th>수량</th>
        <th>가격</th>
        <th>총 가격</th>
        <th>삭제</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="orderDetail" items="${orderDetails}">
        <tr>
            <td>${orderDetail.orderID}</td>
            <td>${orderDetail.productID}</td>
            <td>${orderDetail.quantity}</td>
            <td>${orderDetail.unitCost}</td>
            <td>${orderDetail.quantity * orderDetail.unitCost}</td>
            <td>
                <form action="/mypage/deleteorder.do" method="post">
                    <input type="hidden" name="orderDetailId" value="${orderDetail.orderID}">
                    <button class="btn btn-sm btn-outline-secondary">주문취소</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
