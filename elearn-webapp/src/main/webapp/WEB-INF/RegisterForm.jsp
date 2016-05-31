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