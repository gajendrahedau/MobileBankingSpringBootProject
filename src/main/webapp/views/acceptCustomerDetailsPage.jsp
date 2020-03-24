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
<form action="acceptCustomerDetails" method="post">
<table>
<tr>
<td>First Name</td>
<td><input name="firstName" type="text"  size="30" required="required" pattern="[a-zA-Z]+"/></td>
</tr>
<tr>
<td>Last Name</td>
<td><input name="lastName" size="30" type="text" required="required"/></td>
</tr>
<tr>
<td>Email ID</td>
<td><input name="emailID" type="email" size="30" required="required"/></td>

</tr>
<tr>
<td>DateOfBirth</td>
<td><input name="dateOfBirth"  type="date" size="30" required="required"/></td>

</tr>
<tr>
<td>Billing Address City</td>
<td><input name="city" size="30" type="text" required="required"/></td>

</tr>
<tr>
<td>Billing Address State</td>
<td><input name="state" type="text" size="30" required="required"/></td>
</tr>
<tr>
<td>Billing Address PIN code</td>
<td><input name="pinCode" type="text" required="required" size="30" pattern="[1-9]{1}[0-9]{5}" /></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
</body>
</html>



