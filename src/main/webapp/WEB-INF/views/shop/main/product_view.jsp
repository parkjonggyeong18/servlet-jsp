<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:url var="buy_link" value="/order.do">
        <c:param name="productID" value="${product.productID}"/>
    </c:url>
    <c:url var="cart_link" value="/AddCartAction.do">
        <c:param name="productID" value="${product.productID}"/>
    </c:url>
    <c:choose>
        <c:when test="${empty product.productImage}">
            <c:set var="img_link" value="${pageContext.request.contextPath}/resources/no-image.png"/>
        </c:when>
        <c:otherwise>
            <c:set var="img_link" value="${pageContext.request.contextPath}/resources/images/products/${product.productImage}"/>
        </c:otherwise>
    </c:choose>
    <div>
        <h1>${product.modelName}</h1>
        <div>${product.comment}</div>
        <small class="text-bold">₩${product.unitCost}</small>
        <small class="text-muted">남은 수량 : ${product.quantity}</small>
        <svg class="bd-placeholder-img" width="100%" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
            <title>Placeholder</title>
            <rect width="100%" height="100%" fill="#55595c"></rect>
            <image xlink:href="${img_link}" width="100%" height="400"></image>
        </svg>
        <div class="btn-group" style="margin-top: 50px">
            <form method="post" action="${cart_link}">
                <button type="submit" class="btn btn-sm btn-outline-secondary">Cart</button>
            </form>
        </div>
    </div>
</div>
