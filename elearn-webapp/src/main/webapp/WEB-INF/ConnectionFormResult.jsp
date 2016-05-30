<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire de Connexion</title>
<style type="text/css">
    <%@include file="/CSS/FormulaireConnection.css" %>
    </style>
</head>
<body>
<form action="" method="POST" >
	<fieldset >
	
    <legend> Accéder à Mon Compte : </legend>
	   <p style="color:red; font-size:10px;">Veuillez entrez le bon Mot de passe ou Identifiant<p>
	<label for="mail_user"> Username :</label>
	<input id="mailUser" type="text" name="mailUser" />
	<br><br>
	<label for="password_user">Password :</label>
	<input id="passwordUser" type="password" name="passwordUser"/>	
	<br><br>
	<input type="submit" value="Sign In" />
	</fieldset>
	</form>
</body>
</html>