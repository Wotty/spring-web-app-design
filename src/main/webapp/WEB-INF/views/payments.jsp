<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<%@include file="header.jsp"%>
<title>Payments</title>
</head>
<body>
	<h2>Payments</h2>
	<TABLE>
		<thead>
			<tr>
				<th>PaymentID</th>
				<th>Card Number</th>
				<th>CVC</th>
				<th>Expiry Date</th>
			</tr>
		</thead>
		<c:forEach var="eachItem" items="${listOfPayments}">
			<TR>
				<TD><c:out value="${eachItem.paymentID}" /></TD>
				<TD><c:out value="${eachItem.cardNumber}" /></TD>
				<TD><c:out value="${eachItem.cardCVC}" /></TD>
				<TD><c:out value="${eachItem.cardExpiry}" /></TD>
				<!--How do you view a date!-->
			</TR>
		</c:forEach>
	</TABLE>
</body>
</html>