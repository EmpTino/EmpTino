<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Classrooms</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #333;
            color: white;
            padding: 1em 0;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-top: 20px;
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
            text-align: center;
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
        footer {
            text-align: center;
            padding: 1em;
            background-color: #333;
            color: white;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp" />

<h1>Classrooms List</h1>

<!-- Table to display classrooms -->
<table>
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
            <td>${classroom.classroomName}호</td> <!-- Display classroom name -->
            <td>${classroom.buildingName}</td> <!-- Display building name -->
            <td>${classroom.floor}</td> <!-- Display floor number -->
        </tr>
    </c:forEach>
    </tbody>
</table>

<footer>
    <p>&copy; 2024 Your School. All rights reserved.</p>
</footer>

</body>
</html>
