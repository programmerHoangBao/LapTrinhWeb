<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
</head>
<body>
	<h2>Forgot Password</h2>
	<form action="forgotpassword" method="post">
		<label for="username">Username:</label> <input type="text"
			id="username" name="username" required><br> <label
			for="password">New Password:</label> <input type="password"
			id="password" name="password" required><br>
		<button type="submit">Save</button>
	</form>
	<p style="color:${messageColor};">${message}</p>
</body>
</html>