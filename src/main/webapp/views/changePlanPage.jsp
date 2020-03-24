<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>Accept Customer Details</title>
<style>
</style>
</head>
<body style="background-color:gray">
<div align="center">
<form action="changePlane" method="post">
<table>
<tr>
<td>Enter Customer ID</td>
<td><input name="customerID" size="30" pattern="[1-9]{1}[0-9]*" required="required"/></td>
</tr>
<tr>
<td>Enter Mobile Number</td>
<td><input name="mobileNo" size="30" pattern="[1-9]{1}[0-9]*" required="required"/></td>
</tr>
<tr>
<td>Select New Plan ID</td>
<td>
<select name="planID">
<option value="1">Plan ID 1</option>
<option value="2">Plan ID 2</option>
<option value="3">Plan ID 3</option>
<option value="4">Plan ID 4</option>
<option value="5">Plan ID 5</option>
<option value="6">Plan ID 6</option>
<option value="7">Plan ID 7</option>
<option value="8">Plan ID 8</option>
<option value="9">Plan ID 9</option>
<option value="10">Plan ID 10</option>
</select>
</td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
<div align="center">
${postPaidAccount}
</div>
</body>
</html>