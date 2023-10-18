<%
    if(request.getSession().getAttribute("username") != null)
        response.sendRedirect("home");
%>
<%@ page import="edu.tdtu.beans.Account" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
<h2>Đăng nhập</h2>
<%
    Cookie[] cookies = request.getCookies();
    String user = "";
    String pass = "";
    for(Cookie c : cookies) {
        if(c.equals("user")) {
            user = (String) c.getValue();

        }

        if(c.equals("password")) {
            pass = (String) c.getValue();
        }
    }
%>
<form action="/Lab05/login" method="post">
    <label for="username">Tên người dùng:</label>
    <input type="text" id="username" name="username" value="<%=user%>" required>
    <br>
    <label for="password">Mật khẩu:</label>
    <input type="password" id="password" name="password" value="<%=pass%>" required>
    <br>
    <input type="checkbox" id="remember" name="remember">
    <label for="remember">Ghi nhớ thông tin</label>
    <br>
    <p style="color:red; font-size: 12px"><%=
        request.getAttribute("errorMessage") != null ? (String) request.getAttribute("errorMessage") : ""
    %></p>

    <input type="submit" value="Đăng nhập">
    <a href="register.jsp">Đăng kí</a>
</form>
</body>
</html>

