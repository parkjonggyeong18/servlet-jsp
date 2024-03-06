<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h3>검색 결과</h3>
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${searchResults}">
        <c:url var="viewLink" value="/view.do">
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
        <div class="col">
            <div class="card shadow-sm">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                     xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                     preserveAspectRatio="xMidYMid slice" focusable="false">
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    <image xlink:href="${img_link}" width="100%" height="225"></image>
                </svg>
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <p class="card-text">${product.modelName}</p>
                        <p class="text-muted">상품 번호 : ${product.productID}</p>
                    </div>
                    <p class="card-text">${product.comment}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-outline-secondary"
                                    onclick="location.href='${viewLink}'">View
                            </button>
                        </div>
                        <small class="text-bold">₩${product.unitCost}원</small>
                        <small class="text-muted">남은 수량 : ${product.quantity}</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
