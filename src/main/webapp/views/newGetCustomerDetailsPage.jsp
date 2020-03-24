<html>
<head>
<title>A</title>
</head>
<body>
<div align="center">
<h1>Customer Details:</h1>
<table border="3">
<tr>
<th>Customer ID</th>
<td>${customer.customerID}</td>
</tr>
<tr>
<th>First Name</th>
<td>${customer.firstName}</td>
</tr>
<tr>
<th>Last Name</th>
<td>${customer.lastName}</td>
</tr>
<tr>
<th>Email ID</th>
<td>${customer.emailID}</td>
</tr>
<tr>
<th>DOB</th>
<td>${customer.dateOfBirth}</td>
</tr>
<tr>
<th>City</th>
<td>${customer.billingAddress.city}</td>
</tr>
<tr>
<th>State</th>
<td>${customer.billingAddress.state}</td>
</tr>
<tr>
<th>PIN Code</th>
<td>${customer.billingAddress.pinCode}</td>
</tr>
</table>
</div>
</body>
</html>