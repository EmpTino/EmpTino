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
    <form action="${pageContext.request.contextPath}/api/classrooms/search" method="get">
        <label for="time">시간:</label>
        <select id="time" name="time" required>
            <c:forEach var="i" begin="1" end="14">
                <option value="${i}" ${i == selectedTime ? 'selected' : ''}>${i}</option>
            </c:forEach>
        </select>

        <label for="day">요일:</label>
        <select id="day" name="day" required>
            <option value="월" ${selectedDay == '월' ? 'selected' : ''}>월요일</option>
            <option value="화" ${selectedDay == '화' ? 'selected' : ''}>화요일</option>
            <option value="수" ${selectedDay == '수' ? 'selected' : ''}>수요일</option>
            <option value="목" ${selectedDay == '목' ? 'selected' : ''}>목요일</option>
            <option value="금" ${selectedDay == '금' ? 'selected' : ''}>금요일</option>
        </select>

        <button type="submit">검색</button>
    </form>

    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

    <c:if test="${not empty rooms}">
        <h3>검색된 강의실</h3>
        <ul>
            <c:forEach var="room" items="${rooms}">
                <li>${room.name} - ${room.capacity}명</li>
            </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>
