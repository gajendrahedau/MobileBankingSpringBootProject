<!DOCTYPE>
<html>
<head>
<title>Delete Customer Page</title>
</head>
<body>
<div align="center">
<form action="deleteCustomer">
<table>
<tr>
<td>Enter Customer ID</td>
<td><input type="number" name="customerID" pattern="[1-9]{1}[0-9]*" required="required"></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
<div align="center" style="color: green;">
<h2>${message}</h2>
</div>
</body>
</html>