<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login Page</title>
</head>
<body>

	<h1>Login</h1>

	<sf:form action="submitLogin" method="POST" modelAttribute="user">
		Username <sf:input path="username" />
		<br>
		Password <sf:password path="password" />
		<br>
		<input type="submit" name="commit" value="submit" />
	</sf:form>

	<B>${requestScope.message}</B>
	<br>

	<a href="register">Register</a>

	<!-- img src="<c:url value="/resources/image.jpg" />"> -->

</body>
</html>