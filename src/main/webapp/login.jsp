<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<form action="login" method="post">
		<h2>Login</h2>
		<label for="username">Username:</label> <input type="text"
			id="username" name="username" required><br> <br> <label
			for="password">Password:</label> <input type="password" id="password"
			name="password" required><br> <br>
		<button type="submit">Login</button>
		<button type="button" onclick="window.location.href='sign_up.jsp'">Sign
			up</button>
	</form>
	<a href="forgot_password.jsp">Forgot Password!</a>
	<p style="color: red">${errorMessage}</p>
</body>
</html>