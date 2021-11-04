<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<a href="${pageContext.request.contextPath}/home">Home</a>
	<a href="${pageContext.request.contextPath}/items">Items</a>
	<a href="${pageContext.request.contextPath}/basket">Basket</a>
	<a href="${pageContext.request.contextPath}/orderDetails">Order
		Details</a>
	<a href="${pageContext.request.contextPath}/payments">Payments</a>
	<a href="${pageContext.request.contextPath}/logout">Log out</a>
</body>
</html>