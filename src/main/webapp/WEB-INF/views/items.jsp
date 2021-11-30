<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Items</title>
</head>
<body>
	<h2>Items</h2>
	<TABLE>
		<c:forEach var="eachItem" items="${listOfItems}">
			<TR>
				<TD><c:out value="${eachItem.itemID}" /></TD>
				<TD><c:out value="${eachItem.itemName}" /></TD>
				<TD><c:out value="${eachItem.description}" /></TD>
				<TD><sf:form action="addItemToBasket/${eachItem.itemID}">
						<input type="submit" name="commit" value="add" />
					</sf:form></TD>
			</TR>
		</c:forEach>
	</TABLE>
	<a href="home">Return to menu</a>
</body>
</html>