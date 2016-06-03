<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome to E-learning Plateform</title>
<style type="text/css">
    <%@include file="/CSS/WelcomePage.css" %>
    </style>
</head>
<body>
	<div id="Loggin">
		<form  method="post">
		
		<a href="http://localhost:8080/elearn-webapp-0.1/login"> <input type="button" value="Log In as Trainer" /></a>
		
		<a href="http://localhost:8080/elearn-webapp-0.1/login"> <input type="button" value="Log In as Student" disabled="disabled"/></a>
    		
		<a href="http://localhost:8080/elearn-webapp-0.1/RegisterForm"><input type="button" value="Register as Trainer" /></a>
		
		<a href="http://localhost:8080/elearn-webapp-0.1/RegisterForm"><input type="button" value="Register as Student" disabled="disabled" /></a>
	
		</form>
		
		<br><br>
	</div>
	
	<h1 align=center> Welcome to E-learning PlateForm</h1>
	<h2> Lists of courses published on the site</h2>
	
	<table border="1">
		<thead>
			<tr>
				<th>Title</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ courses }" var="c">
				<tr>
					<td>${ c.titleCourse }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>