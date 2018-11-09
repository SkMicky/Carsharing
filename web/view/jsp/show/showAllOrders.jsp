<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 03.11.18
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Orders</title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<c:import url="../adminHeader.jsp" />
<c:if test="${requestScope.orders.get(0).id ne 0}">
<table>
    <tr>
        <th>Все заказы</th>
    </tr>
    <tr>
        <td>ID заказы</td>
        <td>Дата заказа</td>
        <td>ID пользователя</td>
        <td>ID авто</td>
        <td>Статус заказа</td>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${order.id}" /></td>
            <td><c:out value="${order.date}" /></td>
            <td><c:out value="${order.userId}" /></td>
            <td><c:out value="${order.carId}" /></td>
            <td>
                <c:if test="${order.status eq 1}">
                    <c:out value="Выполняется" />
                </c:if>
                <c:if test="${order.status eq 2}">
                    <c:out value="Завершён" />
                </c:if>
            </td>
            <td><a class="delete" href="/deleteOrder?orderId=${order.id}&orderStatus=${order.status}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${requestScope.orders.get(0).id eq 0}">
    <h1>Нет заказов</h1>
</c:if>
<c:import url="../footer.jsp" />
</body>
</html>
