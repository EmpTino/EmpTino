<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${classroom.classroomName} - Classroom Details</title>
</head>
<body>
<h1>${classroom.classroomName} - Classroom Details</h1>

<h2>Classroom Information</h2>
<table>
    <tr>
        <th>Classroom ID</th>
        <td>${classroom.classroomId}</td>
    </tr>
    <tr>
        <th>Classroom Name</th>
        <td>${classroom.classroomName}</td>
    </tr>
    <tr>
        <th>Building</th>
        <td>${classroom.buildingName}</td>
    </tr>
    <tr>
        <th>Floor</th>
        <td>${classroom.floor}</td>
    </tr>
</table>

<h2>Lecture List</h2>
<table>
    <thead>
    <tr>
        <th>Lecture Name</th>
        <th>Professor</th>
        <th>Lecture Times</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lecture" items="${lectures}">
        <tr>
            <td>${lecture.lectureName}</td>
            <td>${lecture.professor}</td>
            <td>
                <ul>
                    <!-- 해당 강의의 lectureId로 강의 시간 정보 찾아서 표시 -->
                    <c:forEach var="time" items="${lectureTimesMap[lecture.lectureId]}">
                        <li>${time.day} - ${time.time}</li> <!-- 교시 정보 추가 -->
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>
<a href="/classrooms">Back to Classroom List</a>
</body>
</html>
