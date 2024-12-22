<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 목록</title>
</head>
<body>
<h2>리뷰 목록</h2>
<table border="1">
    <thead>
    <tr>
        <th>리뷰 ID</th>
        <th>강의실 ID</th>
        <th>사용자 이름</th>
        <th>별점</th>
        <th>리뷰 내용</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="review" items="${reviews}">
        <tr>
            <td>${review.ReviewId}</td>
            <td>${review.classroomId}</td>
            <td>${review.userName}</td>
            <td>${review.mark}</td>
            <td>${review.content}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="/api/reviews/news">새 리뷰 작성하기</a>
</body>
</html>
