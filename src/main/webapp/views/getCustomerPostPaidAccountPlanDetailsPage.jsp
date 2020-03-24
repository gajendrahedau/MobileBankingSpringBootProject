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
<form action="getCustomerPostPaidAccountPlanDetails" method="post">
<table>
<tr>
<td>Enter Customer ID</td>
<td><input type="number" name="customerID" pattern="[1-9]{1}[0-9]*" size="30" required="required"/></td>
</tr>
<tr>
<td>Enter Mobile Number</td>
<td><input type="number" name="mobileNo" pattern="[1-9]{1}[0-9]*" size="30" required="required"/></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
<div align="center" style="color: red">
<h2>${postpaidAccount}</h2>
</div>
</body>
</html>