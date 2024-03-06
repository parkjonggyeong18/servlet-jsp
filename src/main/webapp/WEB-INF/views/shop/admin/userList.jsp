<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>관리자</h3>
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="user" items="${userList}">
        <c:if test="${user.userAuth eq 'ROLE_ADMIN'}">
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">ID : ${user.userId}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">이름 : ${user.userName}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">비밀번호 : ${user.userPassword}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">생일 : ${user.userBirth}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">권한 : ${user.userAuth}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">포인트 : ${user.userPoint}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">생성일 : ${user.createdAt}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">
                                <c:choose>
                                    <c:when test="${empty user.latestLoginAt}">
                                        마지막접속 : 접속없음
                                    </c:when>
                                    <c:otherwise>
                                        마지막 접속 : ${user.latestLoginAt}
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>

<h3>일반 사용자</h3>
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="user" items="${userList}">
        <c:if test="${user.userAuth ne 'ROLE_ADMIN'}">
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">ID : ${user.userId}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">이름 : ${user.userName}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">비밀번호 : ${user.userPassword}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">생일 : ${user.userBirth}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">권한 : ${user.userAuth}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">포인트 : ${user.userPoint}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">생성일 : ${user.createdAt}</p>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text">
                                <c:choose>
                                    <c:when test="${empty user.latestLoginAt}">
                                        마지막접속 : 접속없음
                                    </c:when>
                                    <c:otherwise>
                                        마지막 접속 : ${user.latestLoginAt}
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>


<span style="margin-top: 50px; height: 100%; display:flex; align-items: center; justify-content: center">
        <c:forEach begin="1" end="${totalPages}" var="pageNumber">
            <c:url var="pageURL" value="/admin/userlist.do">
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