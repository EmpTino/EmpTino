<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Classrooms</title>
</head>
<body>
<h1>Classrooms List</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Lecture Name</th>
        <th>Professor</th>
        <th>Classroom</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lecture" items="${lecture}">
        <tr>
            <td>${lecture.lectureId}</td>
            <td>${lecture.lectureName}</td>
            <td>${lecture.professor}</td>
            <td>${lecture.classroomId}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>