<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${ userMail } Home Page</title>
<style type="text/css"><%@include file="/CSS/WelcomePage.css" %></style>
</head>
<body>
    <div id="Loggin">
        <form action="http://localhost:8080/elearn-webapp-0.1/logout" method="POST">
            
            <p class="id">Hello <a  href="">${ userMail }</a></p>
             
            <input type="submit" value="Log Out" />
        
        </form>
        
        <br><br>
    </div>
    
    <h1 align=center> Welcome to Your Courses Plateform</h1>
    <a href="http://localhost:8080/elearn-webapp-0.1/courseform"><input type="submit" value="Create course" /></a>
    <h2> List of your courses</h2>
        <a href=""><img src="images/javacourse.jpg" class="image" ${ u.isPublish }/></a>
        <a href=""><img src="images/javacourse.jpg" class="image" ${ u.isPublish }/></a>
        <a href=""><img src="images/javacourse.jpg" class="image" ${ u.isPublish }/></a>
        
</body>
</html>