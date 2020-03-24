<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>newGetCustomerPostPaidAccountAllBillDetailsPage</title>
</head>
<body>
<div align="center">
<table border="2">
<tr>
<th>Bill ID</th>
<th>Bill Month</th>
<th>localSMSAmount</th>
<th>stdSMSAmount</th>
<th>localCallAmount</th>
<th>stdCallAmount</th>
<th>internetDataUsageAmount</th>
<th>servicesTax</th>
<th>totalBillAmount</th>
</tr>
<c:forEach var="list" items="${listOfAllBill}">
<tr>
<td><c:out value="${list.billID}"></c:out></td>
<td><c:out value="${list.billMonth}"></c:out></td>
<td><c:out value="${list.localSMSAmount}"></c:out></td>
<td><c:out value="${list.stdSMSAmount}"></c:out></td>
<td><c:out value="${list.localCallAmount}"></c:out></td>
<td><c:out value="${list.stdCallAmount}"></c:out></td>
<td><c:out value="${list.internetDataUsageAmount}"></c:out></td>
<td><c:out value="${list.servicesTax}"></c:out></td>
<td><c:out value="${list.totalBillAmount}"></c:out></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>