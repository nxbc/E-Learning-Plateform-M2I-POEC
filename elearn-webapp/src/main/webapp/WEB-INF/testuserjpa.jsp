<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users JPA</title>
</head>
<body>
<h3>Test d'accès a la table ELearning.users</h3>
	<table border="1">
		<thead>
			<tr>
				<th>idUser</th>
				<th>mailUser</th>
				<th>passwordUser</th>
				<th>confirmedUser</th>
				<th>confirmedKeyUser</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ users }" var="u">
				<tr>
					<td>${ u.idUser }</td>
					<td>${ u.mailUser }</td>
					<td>${ u.passwordUser }</td>
					<td>${ u.confirmedUser }</td>
					<td>${ u.confirmedKeyUser }</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>