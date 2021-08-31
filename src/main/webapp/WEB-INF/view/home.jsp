<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>

	<h3>Spring Security</h3>
	<br>
	
	<!-- DISPLAY ROLE AND USERNAME -->
	
	<p><b>User: </b><security:authentication property="principal.username"/></p>
	<p><b>Role(s): </b><security:authentication property="principal.authorities"/></p>
	
	<br>
	
	<hr>
	<!-- ADD LINK FOR LEADERS.... FOR MANAGERS -->
		<p>
			<a href = "${pageContext.request.contextPath}/leaders">Leaders' Page</a>
			(Only For Leaders)
		</p>	
	<hr>
	<br>
	<!-- ADD LINK FOR LEADERS.... FOR MANAGERS -->
		<p>
			<a href = "${pageContext.request.contextPath}/systems">Systems Page</a>
			(Only For Admin)
		</p>	
	<hr>
	<br>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	
		<input type="submit" value="LOGOUT" />
	
	</form:form>
	

</body>
</html>