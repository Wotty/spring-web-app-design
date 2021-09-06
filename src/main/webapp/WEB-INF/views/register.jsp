<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Register</title>
</head>
<body>
	<h2>Register</h2>
	<sf:form action="submitRegister" method="POST" modelAttribute="user">
		Username <sf:input path="username" required='true'/>
		<br>
		Password <sf:password path="password" required='true'/>
		<br>
		First Name <sf:input path="firstName" required='true'/>
		<br>
		Last Name <sf:input path="lastName" required='true'/>
		<br>
		Email <sf:input path="email" required='true'/>
		<br>
		Address <sf:input path="address" required='true'/>
		<br>
		Postcode <sf:input path="postcode" required='true'/>
		<br>

${requestScope.message}<br>
		<button name="submit" value="submit" type="submit">Submit</button>
	</sf:form>
		<a href="login">Login</a>
	
</body>
</html>