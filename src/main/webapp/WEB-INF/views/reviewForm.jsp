<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>새 리뷰 작성</title>
</head>
<body>
<h2>새 리뷰 작성</h2>
<form action="/api/reviews" method="post">
    <label for="classroomId">강의실 ID:</label>
    <input type="text" id="classroomId" name="classroomId" value="${review.classroomId}" required/><br>

    <label for="userName">사용자 이름:</label>
    <input type="text" id="userName" name="userName" value="${review.userName}" required/><br>

    <label for="mark">별점:</label>
    <input type="number" id="mark" name="mark" value="${review.mark}" min="1" max="5" required/><br>

    <label for="content">리뷰 내용:</label><br>
    <textarea id="content" name="content" rows="4" cols="50" required>${review.content}</textarea><br>

    <button type="submit">제출</button>
</form>
<br>
<a href="/api/reviews">리뷰 목록으로 돌아가기</a>
</body>
</html>
