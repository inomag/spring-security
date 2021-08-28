<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
	.err{
		font-size:12px;
		color:rgb(250,0,0);
	}


</style>
</head>
<body>
	<h3>LOGIN / REGISTER</h3>
	<br>
	<br>
	
	<form:form method="POST" action="${pageContext.request.contextPath}/auth">
	
		<c:if test="${param.error!=null}">
		
			<b><i class="err">Sorry, username or password invalid</i></b>
		
		</c:if>
	
	
		<p>User Name: <input type="text" name="username"/></p>
		<p>Password: <input type="password" name="password"/></p>
		<input type="submit" value="LOG IN"/>
	</form:form>
</body>
</html>