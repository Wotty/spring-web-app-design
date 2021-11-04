<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<%@include file="header.jsp"%>
<title>Home</title>
</head>
<body>

	<h1>You have Logged In!</h1>

	Here are the details you entered:
	<BR> Username: ${user.username}
	<BR> Password: ${user.password}
	<BR>



</body>
</html>