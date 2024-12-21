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

<!-- Table to display classrooms -->
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Classroom Name</th>
        <th>Building Name</th>
        <th>Floor</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through the classrooms list and display each classroom's details -->
    <c:forEach var="classroom" items="${classrooms}">
        <tr>
            <td>${classroom.classroomId}</td>  <!-- Display UUID for classroomId -->
            <td>${classroom.classroomName}</td> <!-- Display classroom name -->
            <td>${classroom.buildingName}</td> <!-- Display building name -->
            <td>${classroom.floor}</td> <!-- Display floor number -->
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
