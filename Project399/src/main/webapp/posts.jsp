<%--
  Created by IntelliJ IDEA.
  User: DedSec
  Date: 2024/04/21
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Posts</h2>
    <span onclick="window.location.href='index.jsp';" style="cursor:pointer;">Create a post</span>
    <div>
        <c:forEach var="posts" items="${posts}">
            <h3>${posts.title}</h3>
            <p>${posts.text}</p>
            <img src="${posts.imageUrl}" alt="Post Image" style="width: 200px; height: auto;">
            <hr>
        </c:forEach>
    </div>
</body>
</html>
