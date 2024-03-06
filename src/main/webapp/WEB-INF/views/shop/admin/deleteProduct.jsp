<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="col-md-9">
                <h2>상품 삭제</h2>
                <form method="post" action="/admin/deleteProductAction.do">
                    <div class="form-floating">
                        <input type="text" class="form-control" id="productID" name="productID"
                               placeholder="삭제할 상품 ID를 입력해주세요." required>
                        <label for="productID">상품 ID</label>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-danger">상품 삭제</button>
                </form>
            </div>

            <br>
            <c:if test="${requestScope.successMessage == true}">
                <div class="alert alert-success">
                    <strong>성공!</strong> 상품이 성공적으로 삭제되었습니다.
                </div>
            </c:if>

            <c:if test="${requestScope.errorMessage == true}">
                <div class="alert alert-danger">
                    <strong>오류!</strong> 상품을 찾을 수 없습니다.
                </div>
            </c:if>
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
            </div>
        </div>
    </div>
</div>