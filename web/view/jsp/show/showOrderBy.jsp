<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 05.11.18
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Order By</title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<table>
    <tr>
        <th>Заказ по параметру</th>
    </tr>
    <tr>
        <td>ID заказа</td>
        <td>Дата заказа</td>
        <td>ID пользователя</td>
        <td>ID машины</td>
        <td>Статус заказа</td>
    </tr>
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
                <c:out value="Выполнен" />
            </c:if>
        </td>
        <td><a class="delete" href="/deleteOrder?orderId=${order.id}&status=${order.status}">Удалить</a></td>
    </tr>
</table>
</body>
</html>

