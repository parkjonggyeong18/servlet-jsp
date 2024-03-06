<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="p-2">
                <c:url var="add_link" value="/admin/addCategoryAction.do"/>
                <c:set var="action" value="${add_link}"/>

                <form method="post" action="${action}">

                    <h1 class="h3 mb-3 fw-normal">카테고리 관리</h1>

                    <div class="form-floating">
                        <select name="categories" class="form-control">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryID}" selected>
                                        카테고리 ID : ${category.categoryID} 이름 : ${category.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                        <label for ="category_name">기존 카테고리</label>
                    </div>

                    <div class="form-floating">
                        <input type="text" name="category_name" class="form-control" id="category_name"
                               placeholder="모델 이름" value="${category.categoryName}" required>
                        <label for="category_name">카테고리 추가(이름 입력)</label>
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
                            <h5 class="card-title">카테고리 삭제</h5>
                            <p class="card-text">기존 카테고리를 삭제합니다.</p>
                            <a href="/admin/deleteCategory.do" class="btn btn-danger">카테고리 삭제하기</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>