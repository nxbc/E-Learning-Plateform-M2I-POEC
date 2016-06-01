<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css"><%@include file="/CSS/FormateurRegister.css" %></style>
<title>Inscription Formateur</title>
</head>
<body>
	
	<div id="Accueil">
		<form  method="post">
		<a href="http://localhost:8080/elearn-webapp-0.1/welcome"> <input type="button" value="Retour Accueil" /></a>
		</form>
		<br><br>
	</div>
		
		<form method="post" action="http://localhost:8080/elearn-webapp-0.1/RegisterForm">
			
			<fieldset>
			<legend>Inscription Formateur</legend>
			<label for="User">User</label>
			<input id="mailUser" type="text" name="mailUser">
			<span class="erreur">${erreurs['mailUser']}</span>
			<BR><BR>
			
			<label for="Password">Password</label>
			<input id="passwordUser" type="password" name="passwordUser">
			<span class="erreur">${erreurs['passwordUser']}</span>
			<BR><BR>
			
			<INPUT type=submit value=Valider>
			</fieldset>
		</form>
	



</body>
</html>