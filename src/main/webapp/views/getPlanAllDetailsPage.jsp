<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Plan Details</title>
</head>
<body>
<div align="center" style="color: black; size: em;">
<c:forEach var="listOfPlan" items="${listOfPlans}">
<table border="2">
<tr>
<th>planID</th>
<th>freeLocalCalls</th>
<th>freeStdCalls</th>
<th>freeLocalSMS</th>
<th>freeStdSMS</th>
<th>freeInternetDataUsageUnits</th>
<th>localCallRate</th>
<th>stdCallRate</th>
<th>localSMSRate</th>
<th>stdSMSRate</th>
<th>internetDataUsageRate</th>
<th>planCircle</th>
<th>planName</th>
</tr>
<tr>
<td> <c:out value="${listOfPlan.planID}"/></td>
<td> <c:out value="${listOfPlan.freeLocalCalls}"/></td>
<td> <c:out value="${listOfPlan.freeStdCalls}"/></td>
<td> <c:out value="${listOfPlan.freeLocalSMS}"/></td>
<td> <c:out value="${listOfPlan.freeStdSMS}"/></td>
<td> <c:out value="${listOfPlan.freeInternetDataUsageUnits}"/></td>
<td> <c:out value="${listOfPlan.localCallRate}"/></td>
<td> <c:out value="${listOfPlan.stdCallRate}"/></td>
<td> <c:out value="${listOfPlan.localSMSRate}"/></td>
<td> <c:out value="${listOfPlan.stdSMSRate}"/></td>
<td> <c:out value="${listOfPlan.internetDataUsageRate}"/></td>
<td> <c:out value="${listOfPlan.planCircle}"/></td>
<td> <c:out value="${listOfPlan.planName}"/></td>
</tr>
</table>
</c:forEach>
</div>
</body>
</html>