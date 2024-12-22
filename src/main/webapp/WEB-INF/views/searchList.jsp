<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>강의실 검색 결과</title>
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
            max-width: 800px;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #d8dee4;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 6px;
        }
        .room-list {
            width: 100%;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 6px;
            margin-top: 20px;
        }
        .room-item {
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        .room-item:last-child {
            border-bottom: none;
        }
        .room-name {
            font-size: 18px;
            font-weight: bold;
            color: #2a2a2a;
        }
        .room-details {
            font-size: 14px;
            color: #666;
        }
        .room-capacity {
            color: #007bff;
            font-size: 14px;
        }
        .message {
            color: #d9534f;
            font-size: 16px;
            font-weight: bold;
        }
        .btn {
            text-decoration: none;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<%-- 헤더 포함 --%>
<jsp:include page="header.jsp" />

<%-- 검색 결과 화면 --%>
<div class="search-container">
    <h2>강의실 검색 결과</h2>

    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>

    <c:if test="${not empty rooms}">
        <div class="room-list">
            <c:forEach var="room" items="${rooms}">
                <div class="room-item">
                    <p class="room-name">${room.classroomName}호</p>
                    <p class="room-details">건물: ${room.buildingName} | 층수: ${room.floor}</p>
                    <a href="${pageContext.request.contextPath}/api/classrooms/${room.classroomId}" class="btn">상세보기</a>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <a href="${pageContext.request.contextPath}/search" class="btn">검색 화면으로 돌아가기</a>
</div>

</body>
</html>
