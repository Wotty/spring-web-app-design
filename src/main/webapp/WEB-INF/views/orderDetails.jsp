<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<%@include file="header.jsp"%>
<title>Order Details</title>
</head>
<body>
	<h2>Orders</h2>
	<TABLE>
		<thead>
			<tr>
				<th>Order ID</th>
				<th>Item ID</th>
				<th>Quantity</th>
			</tr>
		</thead>
		<c:forEach var="eachItem" items="${listOfOrderDetails}">
			<TR>
				<TD><c:out value="${eachItem.order.orderID}" /></TD>
				<TD><c:out value="${eachItem.item.itemID}" /></TD>
				<TD><c:out value="${eachItem.orderQty}" /></TD>
			</TR>
		</c:forEach>
	</TABLE>
</body>
</html>