<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Accept Customer Details</title>
<style>
.error{
	color: blue;
	font-weight: bold;
}
</style>
</head>
<body style="background-color:gray;">
<div align="center">
<form action="getCustomerDetails" method="post">
<table>
<tr>
<td>Enter Customer ID:</td>
<td><input type="number" name="customerID" pattern="[1-9]{1}[0-9]*" size="30" required="required"/></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
<div align="center">
${customers}
</div>
</body>
</html>