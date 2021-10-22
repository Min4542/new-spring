<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- for문 if문 쓸수있게만드는 태그  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <h1>
        <a href="/board/list">My First Website</a>
    </h1>
    <div class="menu-box">
        <c:if test="${loginUser == null}">
            <a class="btn btn-outline-secondary" href="/member/sign-in">로그인</a>
            <a class="btn btn-info" href="/member/sign-up">회원가입</a>
        </c:if>

        <c:if test="${loginUser != null}">
            <span>${loginUser.name}님 안녕하세요</span>
            <a class="btn btn-danger" href="/member/logout">로그아웃</a>

        </c:if>
    </div>
</header>