<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Inscription Formateur</title>
</head>
<body>
	
		
		<form method="post" action="http://localhost:8080/elearn-webapp-0.1/RegisterForm">
			
			<fieldset>
			<legend><h2>Inscription Formateur</h2></legend>
			<label for="User">User</label>
			<input id="user" type="text" name="mail_user">
			<span class="erreur">${erreurs['mail_user']}</span>
			<BR><BR>
			
			<label for="Password">Password</label>
			<input id="password" type="text" name="password">
			<span class="erreur">${erreurs['password']}</span>
			<BR><BR>
			
			<INPUT type=submit value=Valider>
			</fieldset>
		</form>
	



</body>
</html>