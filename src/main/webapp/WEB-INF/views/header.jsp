<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <title>Header</title>
    <style>
        /* 공통 네비게이션 스타일 */
        nav {
            position: fixed;
            top: 0;
            width: 100%;
            background-color: #212529;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            z-index: 1000;
        }

        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            margin: 0;
            padding: 0;
        }

        nav li {
            margin: 0 15px;
        }

        nav a {
            text-decoration: none;
            color: #f8f9fa;
            font-size: 16px;
            padding: 14px 20px;
            display: block;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        nav a:hover {
            background-color: #495057;
            color: #ffffff;
        }

        nav a.active {
            background-color: #0d6efd;
            color: white;
        }

        /* 페이지 내용 여백 */
        .content {
            margin-top: 60px; /* 네비게이션 높이만큼 추가 */
        }
    </style>
</head>
<body>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/" class="active">메인</a></li>
        <li><a href="${pageContext.request.contextPath}/search">강의실 찾기</a></li>
        <li><a href="${pageContext.request.contextPath}/api/timetables">내 시간표</a></li>
        <li><a href="${pageContext.request.contextPath}/api/auth/login">로그인</a></li>
    </ul>
</nav>
<div class="content">
    <!-- 각 페이지의 내용이 여기에 표시됩니다 -->
</div>
</body>
</html>