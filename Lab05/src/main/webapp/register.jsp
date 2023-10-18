<%
    if(request.getSession().getAttribute("username") != null)
        response.sendRedirect("home");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng kí</title>
</head>
<body>
<h2>Đăng kí</h2>
<form action="/Lab05/register" method="POST">
    <label for="username">Tên người dùng:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <br>
    <label for="password">Mật khẩu:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <label for="confirmPassword">Xác nhận mật khẩu:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>
    <br>
    <p style="color:red; font-size: 12px"><%=
    request.getAttribute("error") != null ? (String) request.getAttribute("error") : ""
    %></p>
    <br>
    <input type="submit" value="Đăng kí">
    <a href="login.jsp">Đăng nhập</a>
</form>
</body>
</html>
