<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${classroom.classroomName} - Classroom Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #333;
            color: white;
            padding: 1em 0;
            text-align: center;
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #e9f7e9;
        }
        a {
            display: block;
            width: 200px;
            margin: 30px auto;
            padding: 10px;
            text-align: center;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp" />

<h1>${classroom.classroomName} - Classroom Details</h1>

<h2>강의실 정보</h2>
<table>
    <tr>
        <th>강의실</th>
        <td>${classroom.classroomName}호</td>
    </tr>
    <tr>
        <th>건물</th>
        <td>${classroom.buildingName}</td>
    </tr>
    <tr>
        <th>층수</th>
        <td>${classroom.floor}</td>
    </tr>
</table>

<h2>강의 리스트</h2>
<table>
    <thead>
    <tr>
        <th>강의 이름</th>
        <th>교수진</th>
        <th>강의 시간</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lecture" items="${lectures}">
        <tr>
            <td>${lecture.lectureName}</td>
            <td>${lecture.professor}</td>
            <td>
                <ul>
                    <c:forEach var="time" items="${lectureTimesMap[lecture.lectureId]}">
                        <li>${time.day} - ${time.time}교시</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
