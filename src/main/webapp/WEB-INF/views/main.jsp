<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 화면</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f8fa;
            margin: 0;
            padding: 0;
        }
        nav {
            position: sticky;
            top: 0;
            background-color: #ffffff;
            padding: 10px;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }
        ul {
            list-style: none;
            display: flex;
            justify-content: center;
            margin: 0;
            padding: 0;
        }
        li {
            margin: 0 15px;
        }
        li a {
            text-decoration: none;
            color: #24292e;
            font-weight: bold;
            padding: 5px 10px;
            border-radius: 4px;
            transition: background-color 0.2s ease;
        }
        li a:hover {
            background-color: #d8dee4;
        }
        .main-content {
            text-align: center;
            margin-top: 50px;
        }
        .main-content h1 {
            font-size: 28px;
            color: #24292e;
            margin-bottom: 20px;
        }
        .logo {
            width: 150px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<!-- 네비게이션 바 -->
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/search">강의실 찾기</a></li>
        <li><a href="${pageContext.request.contextPath}/timetable">내 시간표</a></li>
        <li><a href="${pageContext.request.contextPath}/friend">친구보기</a></li>
        <li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
    </ul>
</nav>

<!-- 메인 내용 -->
<div class="main-content">
    <%-- 한국공학대학교 로고 이미지 --%>
        <img src="/resources/images/koreatech_logo.png" alt="한국공학대학교 로고" class="logo">
    <h1>한국공학대학교 빈강의실 찾기에 오신 것을 환영합니다.</h1>
</div>
</body>
</html>