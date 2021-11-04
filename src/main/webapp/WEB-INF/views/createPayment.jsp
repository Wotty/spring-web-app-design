<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Create Payment Method</title>
</head>
<body>
	<h2>Register</h2>
	private int paymentID;
	private Date paymentDate;
	private String cardNumber;
	private String cardCVC;
	private Date cardExpiry;
	<sf:form action="submitPaymentCreation" method="POST" modelAttribute="payment">
		Username <sf:input type="date" path="paymentDate" required='true'/>
		<br>
		Password <sf:password path="cardNumber" required='true'/>
		<br>
		First Name <sf:input path="cardCVC" required='true'/>
		<br>
		Last Name <sf:input path="cardExpiry" required='true'/>
		<br>

${requestScope.message}<br>
		<input type="submit" name="commit" value="submit" />
	</sf:form>
</body>
</html>