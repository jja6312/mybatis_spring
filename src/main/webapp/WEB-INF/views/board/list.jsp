<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <%@ include file="../common/bootstrap.jsp" %>
</head>
<body>
    <div class="container">
        <h2>게시글 목록</h2>
        <hr>
        <table class="table text-center">
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>쓰니</th>
                <th>조회수</th>
                <th>날짜</th>
            </tr>
            <c:forEach items="${boards}" var="board">
                <tr>
                    <td>${board.id}</td>
                    <td><a href="/board/detail?id=${board.id}">${board.title}</td>
                    <td>${board.writer}</td>
                    <td>${board.viewCnt}</td>
                    <td>${board.regDate}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="d-flex justify-content-end">
            <a class="btn btn-outline-primary" href="/board/writeform">글 쓰기</a>

        </div>

    </div>

</body>
</html>