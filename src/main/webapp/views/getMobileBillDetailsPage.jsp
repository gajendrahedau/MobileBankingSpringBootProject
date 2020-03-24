<html>
<head>
<title>getMobileBillDetailsPage</title>
</head>
<body>
<div align="center">
<form action="newGetMobileBillDetailsPage" method="post">
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
<td>Bill Month</td>
<td>
<select name="billMonth">
<option value="January">January</option>
<option value="February">February</option>
<option value="March">March</option>
<option value="Aprail">Aprail</option>
<option value="May">May</option>
<option value="June">June</option>
<option value="July">July</option>
<option value="August">August</option>
<option value="September">September</option>
<option value="October">October</option>
<option value="November">November</option>
<option value="December">December</option>
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
<h2>${bill}</h2>
</div>
</body>
</html>