<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f8fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            width: 360px;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #d8dee4;
            border-radius: 6px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .login-container h2 {
            margin: 0 0 20px 0;
            text-align: center;
            color: #24292e;
            font-weight: 600;
        }
        .login-container label {
            font-size: 14px;
            color: #24292e;
            display: block;
            margin-bottom: 5px;
        }
        .login-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #d8dee4;
            border-radius: 6px;
            font-size: 14px;
            box-sizing: border-box;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #2da44e;
            border: none;
            border-radius: 6px;
            color: #ffffff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.2s ease;
        }
        .login-container button:hover {
            background-color: #218639;
        }
        .login-container p {
            text-align: center;
            font-size: 12px;
            margin-top: 10px;
        }
        .login-container p a {
            text-decoration: none;
            color: #0969da;
        }
        .login-container p a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%-- 헤더 포함 --%>
<jsp:include page="header.jsp" />

<div class="login-container">
    <h2>로그인</h2>
    <form action="${pageContext.request.contextPath}/api/auth/login" method="post">
        <label for="userName">아이디</label>
        <input type="text" id="userName" name="userName" placeholder="아이디를 입력하세요" required>

        <label for="password">비밀 번호</label>
        <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>

        <button type="submit">로그인</button>
    </form>
    <p><a href="${pageContext.request.contextPath}/api/auth/signup">회원가입</a></p>
</div>
</body>
</html>