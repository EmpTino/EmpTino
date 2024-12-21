<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lecture Times</title>
</head>
<body>
<h1>Lecture Times List</h1>

<table border="1">
    <thead>
    <tr>
        <th>Time ID</th>
        <th>Lecture Name</th>
        <th>Day</th>
        <th>Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lecturetime" items="${lecturetime}">
        <tr>
            <td>${lecturetime.lectureTimeId}</td>
            <td>${lecturetime.lectureId}</td> <!-- You might need to resolve the lecture name here -->
            <td>${lecturetime.day}</td>
            <td>${lecturetime.time}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>