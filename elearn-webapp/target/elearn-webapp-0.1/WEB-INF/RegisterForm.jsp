<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css"><%@include file="/CSS/FormateurRegister.css" %></style>
<title>Trainer registration</title>
</head>
<body>
	<div id="Home">
		<form method="post">
			<a href="http://localhost:8080/elearn-webapp-0.1/welcome"> <input
				type="button" value="Home" /></a>
		</form>
		<br><br>
	</div>
	<form method="post"
		action="http://localhost:8080/elearn-webapp-0.1/RegisterForm">
		<fieldset>
			<legend>Register as a trainer</legend>
			<label for="User">User</label> <input id="mailUser" type="text"
				name="mailUser"> <span class="erreur">${erreurs['mailUser']}</span>
			<br>
			<br> <label for="Password">Password</label> <input
				id="passwordUser" type="password" name="passwordUser"> <span
				class="erreur">${erreurs['passwordUser']}</span> <br>
			<br> <input type=submit value="Create account">
		</fieldset>
	</form>
</body>
</html>