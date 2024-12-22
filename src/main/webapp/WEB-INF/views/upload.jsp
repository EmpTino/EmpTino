<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>엑셀 파일 업로드</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="file"] {
            display: block;
            width: 100%;
            padding: 10px;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #5cb85c;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background: #4cae4c;
        }
        .message {
            margin-top: 20px;
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>엑셀 파일 업로드</h1>
    <form action="/excel/upload" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="file">엑셀 파일 선택</label>
            <input type="file" id="file" name="file" accept=".xlsx" required>
        </div>
        <button type="submit">업로드 및 처리</button>
    </form>
    <div class="message">
        <c:if test="${not empty message}">
            ${message}
        </c:if>
    </div>
</div>
</body>
</html>
