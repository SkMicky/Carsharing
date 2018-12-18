<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 03.11.18
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title><fmt:message key="allOrders" bundle="${bundle}" /></title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<c:import url="../adminHeader.jsp" />
<c:if test="${requestScope.orders.size() ne 0}">
<table>
    <tr>
        <th><fmt:message key="allOrders" bundle="${bundle}" /></th>
    </tr>
    <tr>
        <td><fmt:message key="orderId" bundle="${bundle}" /></td>
        <td><fmt:message key="orderDate" bundle="${bundle}" /></td>
        <td><fmt:message key="userId" bundle="${bundle}" /></td>
        <td><fmt:message key="carId" bundle="${bundle}" /></td>
        <td><fmt:message key="orderStatus" bundle="${bundle}" /></td>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${order.id}" /></td>
            <td><c:out value="${order.date}" /></td>
            <td><c:out value="${order.userId}" /></td>
            <td><c:out value="${order.carId}" /></td>
            <td>
                <c:if test="${order.status eq 1}">
                    <fmt:message key="executing" bundle="${bundle}" />
                </c:if>
                <c:if test="${order.status eq 2}">
                    <fmt:message key="completed" bundle="${bundle}" />
                </c:if>
            </td>
            <td><a class="delete" href="/deleteOrder?orderId=${order.id}&orderStatus=${order.status}"><fmt:message key="deleteButton" bundle="${bundle}" /></a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${requestScope.orders.size() eq 0}">
    <h1><fmt:message key="noOrders" bundle="${bundle}" /></h1>
</c:if>
<c:import url="../footer.jsp" />
</body>
</html>