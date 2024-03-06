<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:forEach var="category" items="${categories}">
    <c:set var="hasProducts" value="false" />
    <c:forEach var="product" items="${products}">
        <c:if test="${product.categoryID eq category.categoryID}">
            <c:set var="hasProducts" value="true" />
        </c:if>
    </c:forEach>
    <c:if test="${hasProducts}">
        <h3>${category.categoryName}</h3>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="product" items="${products}">
                <c:if test="${product.categoryID eq category.categoryID}">
                    <c:url var="view_link" value="/view.do">
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
                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
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
                                        <button type="button" class="btn btn-sm btn-outline-secondary" onclick=location.href="${view_link}">View</button>
                                    </div>
                                    <small class="text-bold">₩${product.unitCost}원</small>
                                    <small class="text-muted">남은 수량 : ${product.quantity}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</c:forEach>
<span style="margin-top: 50px; height: 100%; display:flex; align-items: center; justify-content: center">
    <c:forEach begin="1" end="${totalPage}" var="pageNumber">
        <c:url var="pageURL" value="/admin/product.do">
            <c:param name="page" value="${pageNumber}"/>
        </c:url>
        <a href="${pageURL}" style="margin-right: 5px">${pageNumber}</a>
    </c:forEach>
</span>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">관리자 페이지</h5>
                            <p class="card-text">관리자 페이지로 이동.</p>
                            <a href="/admin/index.do" class="btn btn-primary">관리자 페이지</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>