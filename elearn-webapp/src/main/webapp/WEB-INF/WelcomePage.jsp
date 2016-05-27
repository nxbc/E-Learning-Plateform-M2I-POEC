<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome to E-learning Plateform</title>
<style type="text/css">
    <%@include file="/CSS/WelcomePage.css" %>
    </style>
</head>
<body>
	<div id="Loggin">
		<form  method="post">
		
		<a href="http://localhost:8080/elearn-webapp-0.1/welcome/connectionform"> <input type="button" value="Log In as Trainer" /></a>
		
		<a href="http://localhost:8080/elearn-webapp-0.1/welcome/connectionform"> <input type="button" value="Log In as Student" /></a>
    		
		<a href="http://localhost:8080/elearn-webapp-0.1/RegisterForm.jsp"><input type="button" value="Register" /></a>
	
		</form>
		
		<br><br>
	</div>
	
	<h1 align=center> Welcome to E-learning PlateForm</h1>
</body>
</html>