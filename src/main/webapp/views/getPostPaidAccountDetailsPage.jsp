<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<body style="background-color:gray">
<div align="center">
<form action="getPostPaidAccountDetails" method="post">
<table>
<tr>
<td>Enter Customer ID</td>
<td><input type="text" name="customerID" pattern="[1-9]{1}[0-9]*" size="30" required="required" pattern="[1-9]{1}[0-9]*"/></td>
</tr>
<tr>
<td>Enter Mobile Number</td>
<td><input type="text" name="mobileNo" size="30" required="required" pattern="[1-9]{1}[0-9]*"/></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
<div align="center">
${postpaidAccount}
</div>
</body>
</html>