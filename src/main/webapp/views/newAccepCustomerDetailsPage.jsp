<html>
<head>
<title>A</title>
</head>
<body>
<div align="center">
<h1>Congratulations! Your customer ID is generated successfully</h1>
<table style="color:maroon; ;size: 100px; border: 2">
<tr>
<td>Customer ID</td>
<td>${requestScope.customer.customerID}</td>
</tr>
<tr>
<td>First Name</td>
<td>${requestScope.customer.firstName}</td>
</tr>
<tr>
<td>Last Name</td>
<td>${requestScope.customer.lastName}</td>
</tr>
<tr>
<td>Email ID</td>
<td>${requestScope.customer.emailID}</td>
</tr>
<tr>
<td>City</td>
<td>${requestScope.customer.billingAddress.city}</td>
</tr>
<tr>
<td>State</td>
<td>${requestScope.customer.billingAddress.state}</td>
</tr>
<tr>
<td>PIN Code</td>
<td>${requestScope.customer.billingAddress.pinCode}</td>
</tr>
</table>
</div>
</body>
</html>