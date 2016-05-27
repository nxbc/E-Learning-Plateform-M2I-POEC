<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Courses JPA</title>
</head>
<body>
<h3>Test d'accès à la table ELearning.course</h3>
${ courses }
<h3>Test d'accès à la table ELearning.chapter</h3>
${ chapters }
<h3>Test d'accès à la table ELearning.lecture</h3>
${ lectures }
</body>
</html>