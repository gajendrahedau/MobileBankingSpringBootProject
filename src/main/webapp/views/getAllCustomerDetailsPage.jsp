<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Get All Customer Details Pages</title>
</head>
<body>
<div align="center" > 
<table border="2"  style="size: 500px;">
<c:forEach var="listOfCustomer" items="${listOfCustomers}">
<tr>
<th>Customer ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>Email ID</th>
<th>DOB</th>
<th>City</th>
<th>State</th>
<th>PIN Code</th>
</tr>
<tr>
<td><c:out value="${listOfCustomer.customerID}"></c:out></td>
<td><c:out value="${listOfCustomer.firstName}"></c:out></td>
<td><c:out value="${listOfCustomer.lastName}"></c:out></td>
<td><c:out value="${listOfCustomer.emailID}"></c:out></td>
<td><c:out value="${listOfCustomer.dateOfBirth}"></c:out></td>
<td><c:out value="${listOfCustomer.billingAddress.city}"></c:out></td>
<td><c:out value="${listOfCustomer.billingAddress.state}"></c:out></td>
<td><c:out value="${listOfCustomer.billingAddress.pinCode}"></c:out></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>