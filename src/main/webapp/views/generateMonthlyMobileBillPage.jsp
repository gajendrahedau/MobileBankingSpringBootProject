<!DOCTYPE>
<html>
<head>
<title>Generate Monthly Mobile Bill Page</title>
</head>
<body>
<div align="center">
<form action="generateMonthlyMobileBill" method="post">
<table>
<tr>
<td>Enter Customer ID</td>
<td><input type="number" name="customerID" pattern="[1-9]{1}[0-9]*" required="required"></td>
</tr>
<tr>
<td>Enter Mobile Number</td>
<td><input type="number" name="mobileNo" pattern="[1-9]{1}[0-9]*" required="required"></td>
</tr>
<tr>
<td>Enter Bill Month</td>
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
<td>Enter noOfLocalSMS</td>
<td><input type="number" name="noOfLocalSMS" required="required" min="0" max="1000"></td>
</tr>
 <tr>
<td>Enter noOfStdSMS</td>
<td><input type="number" name="noOfStdSMS" required="required" min="0" max="2000"></td>
</tr>
 <tr>
<td>Enter noOfLocalCalls</td>
<td><input type="number" name="noOfLocalCalls" required="required" min="0" max="5000"></td>
</tr>
 <tr>
<td>Enter noOfStdCalls</td>
<td><input type="number" name="noOfStdCalls" required="required" min="0" max="5000"></td>
</tr>
 <tr>
<td>Enter internetDataUsageUnits</td>
<td><input type="number" name="internetDataUsageUnits" required="required" min="0" max="8000"></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
<div align="center" style="color: green;">
<h2>${billID}</h2>
</div>
</body>
</html>