<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Login Success page</title>
</head>
<body>
	<h1>You have Logged In!</h1>

	Here are the details you entered:	<BR>
	Username: ${user.username}   <BR>
	Password: ${user.password}   <BR>
	
	<a href="items">Items</a>
	<a href="order">Orders</a>
	<a href="login">Log out</a>

</body>
</html>