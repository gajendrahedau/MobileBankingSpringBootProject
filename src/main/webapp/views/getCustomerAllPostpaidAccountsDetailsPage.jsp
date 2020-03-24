<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Get Customer All Post Paid Account Details</title>
</head>
<body>
<form method="post" action="getCustomerAllPostpaidAccountsDetails">
<table>
<tr>
<td>Enter Customer ID</td>
<td><input type="number" required="required" pattern="[1-9]{1}[0-9]*" name="customerID"></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
<div align="center">
<h2>${lists}</h2>
</div>
</body>
</html>