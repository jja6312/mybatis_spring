<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
<h2>Main</h2>
<%
    String id = session.getAttribute("id")+"";
    String name = (String) session.getAttribute("name");
    String loginError = (String) request.getAttribute("loginError");
%>

<% if (name == null || id=="") { %>
<div id="loginDiv" style="background-color:pink; display:flex; flex-direction: column;">
    <div style="display: flex;">
    <form action="/member/login" method="post">
        email<input type="text" name="email">
        password<input type="text" name="password">
        <button>로그인</button>
    </form>
    <button><a style="text-decoration: none; color:black;" href="/member/createMember">회원가입</a></button>
    </div>
    <div style="color:red; font-size:15px;">
        <% if (loginError != null) {%>
        <%=loginError%>
        <%}%></div>
</div>
<%} else {%>
<div>
    <%=name%>님 환영합니다!
    <button><a style="text-decoration: none; color:black;" href="/member/logout">로그아웃</a></button>
</div>
<%} %>

<a href="/list">목록</a>
<a href="/writeform">등록</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="../../../resources/static/js/my.js"></script>
</body>
</html>