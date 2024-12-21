<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>강의실 검색</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f8fa;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        h2 {
            color: #24292e;
            margin-bottom: 20px;
            font-weight: 600;
        }
        .search-container {
            width: 90%;
            max-width: 600px;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #d8dee4;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 6px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        label {
            margin-top: 10px;
            margin-bottom: 5px;
            font-size: 14px;
            font-weight: bold;
            color: #24292e;
        }
        select, button {
            margin-bottom: 15px;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #d8dee4;
            border-radius: 4px;
            width: 100%;
        }
        button {
            background-color: #2ea44f;
            color: #ffffff;
            cursor: pointer;
        }
        button:hover {
            background-color: #22863a;
        }
    </style>
</head>
<body>
<%-- 헤더 포함 --%>
<jsp:include page="header.jsp" />

<%-- 검색 화면 --%>
<div class="search-container">
    <h2>빈 강의실 검색</h2>
    <form action="${pageContext.request.contextPath}/search" method="get">
        <label for="building">건물:</label>
        <select id="building" name="building" required>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <option value="E">E</option>
        </select>
        <label for="time">시간:</label>
        <select id="time" name="time" required>
            <c:forEach var="i" begin="1" end="14">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>
        <button type="submit">검색</button>
    </form>
</div>
</body>
</html>