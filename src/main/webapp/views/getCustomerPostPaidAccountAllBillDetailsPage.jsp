<html>
<head>
<title>getMobileBillDetailsPage</title>
</head>
<body>
<div align="center">
<form action="newGetCustomerPostPaidAccountAllBillDetailsPage" method="post">
<table>
<tr>
<td>Customer ID</td>
<td><input type="number" required="required" pattern="[1-9]{1}[0-9]*" name="customerID"></td>
</tr>
<tr>
<td>Mobile Number</td>
<td><input type="number" required="required" pattern="[1-9]{1}[0-9]*" name="mobileNo"></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
<div align="center">
<h2>${listOfAllBill}</h2>
</div>
</body>
</html>