<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<%@include file="header.jsp"%>
<title>Basket</title>
</head>
<body>
	<h2>Basket</h2>
	<TABLE>
		<thead>
			<tr>
				<th>Item ID</th>
				<th>Item Name</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Remove</th>
			</tr>
		</thead>
		<c:forEach var="eachItem" items="${basket}">
			<TR>
				<TD><c:out value="${eachItem.key.itemID}" /></TD>
				<TD><c:out value="${eachItem.key.itemName}" /></TD>
				<TD><c:out value="${eachItem.key.description}" /></TD>
				<TD><c:out value="${eachItem.value}" /></TD>
				<TD><sf:form
						action="${pageContext.request.contextPath}/removeItemFromBasket/${eachItem.key.itemID}">
						<input type="submit" name="commit" value="remove" />
					</sf:form></TD>
			</TR>
		</c:forEach>
	</TABLE>
	<a href="${pageContext.request.contextPath}/payForOrder">Order</a>
</body>
</html>