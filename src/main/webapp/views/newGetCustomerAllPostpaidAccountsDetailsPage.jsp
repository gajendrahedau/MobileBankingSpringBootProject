<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<title>newGetCustomerAllPostpaidAccountsDetailsPage.jsp</title>
</head>
<body>
<div align="center">
<table border="2">
<tr>
<th>Mobile Number</th>
<th>Plan ID</th>
<th>Plan Name</th>
<th>Plan Circle</th>
</tr>
<c:forEach var="list" items="${lists}">
<tr>
<td><c:out value="${list.mobileNo}"></c:out></td>
<td><c:out value="${list.plan.planID}"></c:out></td>
<td><c:out value="${list.plan.planName}"></c:out></td>
<td><c:out value="${list.plan.planCircle}"></c:out></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>