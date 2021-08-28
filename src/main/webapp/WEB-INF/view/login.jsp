<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h3>LOGIN / REGISTER</h3>
	<br>
	<br>
	
	<form:form method="POST" action="${pageContext.request.contextPath}/auth">
		<p>User Name: <input type="text" name="username"/></p>
		<p>Password: <input type="password" name="password"/></p>
		<input type="submit" value="LOG IN"/>
	</form:form>
</body>
</html>