<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="styles_login.css">
</head>
<body>
	<div class="container">
        <form action="login" method="post" class="form">
            <h2>Login</h2>
            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="button-group">
                <button type="submit">Login</button>
                <button type="button" onclick="window.location.href='sign_up.jsp'">Sign up</button>
            </div>
            <a href="forgot_password.jsp" class="forgot-password">Forgot Password!</a>
            <p class="error-message">${errorMessage}</p>
        </form>
    </div>
</body>
</html>