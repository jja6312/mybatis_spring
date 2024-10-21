
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
<h1>회원 가입</h1>
<form method="post" action="/member/createMember">
    email<input type="text" name="email"><br>
    password<input type="text" name="password"><br>
    name<input type="text" name="name"><br>
    <button>회원가입</button>
</form>

</body>
</html>