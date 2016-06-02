<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${ userMail } Home Page</title>
<style type="text/css">
    <%@include file="/CSS/WelcomePage.css" %>
    </style>
</head>
<body>
	<div id="Loggin">
		<form action="http://localhost:8080/elearn-webapp-0.1/logout" method="POST">
			
				<p>Bonjour <a  href="">${ userMail }</a></p>
		 	
			<!--<a href="http://localhost:8080/elearn-webapp-0.1/logout">--><input type="submit" value="Log Out" /><!-- </a> -->
		
		</form>
		
		<br><br>
	</div>
	
	<h1 align=center> Welcome to Your Courses Plateform</h1>
	<h2> Listes de vos cours</h2>
	<table border="1">
		<!--<thead>
			<tr>
				<th>Catégories</th>
				<th>Titres</th>
				<th>Nombres de Chapitres</th>
				<th>Nombres de Lectures</th>
				<th>Prix</th>
			</tr>
		</thead>-->
		<tbody>
			<!-- <c:forEach items="${ users }" var="u">  -->
				<tr>
					<td><a href=""><img src="images/javacourse.jpg" ${ u.isPublish }/></a></td>
					<td><img src="images/javacourse.jpg" ${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg" ${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					
				</tr>
				<tr>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					
				</tr>
				<tr>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					<td><img src="images/javacourse.jpg"${ u.isPublish }/></td>
					
				</tr>
			<!--</c:forEach>  -->
		</tbody>
	</table>
</body>
</html>