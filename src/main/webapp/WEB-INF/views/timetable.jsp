<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 시간표</title>
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
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 0 auto;
            background-color: #ffffff;
            border: 1px solid #d8dee4;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 6px;
            overflow: hidden;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #d8dee4;
        }
        th {
            background-color: #f3f4f6;
            font-weight: bold;
        }
        td {
            background-color: #ffffff;
        }
        td:hover {
            background-color: #eaeef2;
        }
        .time-table-container {
            width: 90%;
            max-width: 800px;
            text-align: center;
        }
    </style>
</head>
<body>
<%-- 헤더 포함 --%>
<jsp:include page="header.jsp" />

<div class="time-table-container">
    <h2>내 시간표</h2>
    <table>
        <thead>
        <tr>
            <th>시간</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="time" begin="1" end="14">
            <tr>
                <td>${time}</td>
                <c:forEach var="day" items="['월', '화', '수', '목', '금']">
                    <td></td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>