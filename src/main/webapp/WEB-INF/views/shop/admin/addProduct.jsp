<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="p-2">
                <c:url var="add_link" value="/admin/addProductAction.do"/>
                <c:set var="action" value="${add_link}"/>

                <form method="post" action="${action}">

                    <h1 class="h3 mb-3 fw-normal">상품 추가</h1>

                    <div class="form-floating">
                        <select name="categories" class="form-control">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryID}"
                                        <c:if test="${product.categoryID == category.categoryID}">selected</c:if>>
                                        ${category.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                        <label for="product_modelnumber">카테고리</label>
                    </div>

                    <div class="form-floating">
                        <input type="text" name="product_modelnumber" class="form-control" id="product_modelnumber"
                               placeholder="모델 넘버" value="${product.modelNumber}" required>
                        <label for="product_modelnumber">제품번호</label>
                    </div>

                    <div class="form-floating">
                        <input type="text" name="product_modelname" class="form-control" id="product_modelname"
                               placeholder="모델 이름" value="${product.modelName}" required>
                        <label for="product_modelname">제품명</label>
                    </div>

                    <div class="form-floating">
                        <input type="number" name="product_quantity" class="form-control" id="product_quantity"
                               placeholder="수량" value="${product.quantity}" required>
                        <label for="product_quantity">수량</label>
                    </div>

                    <div class="form-floating">
                        <input type="text" name="product_image" class="form-control" id="product_image"
                               placeholder="이미지" value="${product.productImage}" required>
                        <label for="product_image">이미지</label>
                    </div>

                    <div class="form-floating">
                        <input type="number" name="product_price" class="form-control" id="product_price"
                               placeholder="가격" value="${product.unitCost}" required>
                        <label for="product_price">가격</label>
                    </div>

                    <div class="form-floating">
                        <input type="text" name="product_comment" class="form-control" id="product_comment"
                               placeholder="상품 설명" value="${product.comment}" required>
                        <label for="product_comment">상품 설명</label>
                    </div>

                    <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">추가 및 수정</button>
                    <p class="mt-5 mb-3 text-muted">제품이 존재하는 경우 수정됩니다.</p>
                </form>
            </div>

        </div>
    </div>
</div>
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
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">상품 삭제</h5>
                            <p class="card-text">기존 상품을 삭제합니다.</p>
                            <a href="/admin/deleteProduct.do" class="btn btn-danger">상품 삭제하기</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>