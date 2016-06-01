<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
    <%@include file="/CSS/WelcomePage.css" %>
    </style>
</head>
<body>

	<div id="Loggin">
		<form  method="post">
			
				<p class="id">Bonjour ${ user.mailUser }</p>
			
		<a href="http://localhost:8080/elearn-webapp-0.1/welcome"> <input type="button" value="Retour Accueil" /></a>
		
		<a href="http://localhost:8080/elearn-webapp-0.1/welcome"> <input type="button" value="Log Out" /></a>
		</form>
		
		<br><br>
	</div>
	
	<h1 align=center> Welcome to Your Courses Plateform</h1><br><br>
	<a href="http://localhost:8080/elearn-webapp-0.1/createcourse"> <input type="button" value="Créer cours" /></a><br><br>
	<h2> Listes de vos cours</h2>
	<a href = "" ><img src="images/javacourse.jpg" ${ u.isPublish } class="image"/></a>
	<a href = "" ><img src="images/javacourse.jpg" ${ u.isPublish }  class="image"/></a>
	<a href = "" ><img src="images/javacourse.jpg" ${ u.isPublish }  class="image"/></a>
					

</body>
</html>