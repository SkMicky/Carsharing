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
    <title>Show User By</title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<table>
    <tr>
        <th>Пользователь по параметру</th>
    </tr>
    <tr>
        <td>ID авто</td>
        <td>Название авто</td>
        <td>Цвет авто</td>
        <td>Гос.номер авто</td>
        <td>Статус авто</td>
    </tr>
    <tr>
        <td><c:out value="${car.id}" /></td>
        <td><c:out value="${car.name}" /></td>
        <td><c:out value="${car.color}" /></td>
        <td><c:out value="${car.gosNo}" /></td>
        <td>
            <c:if test="${car.status eq 1}">
                <c:out value="Арендовано" />
            </c:if>
            <c:if test="${car.status eq 2}">
                <c:out value="Свободно" />
            </c:if>
        </td>
        <td><a class="edit" href="../editCar.jsp?carId=${car.id}">Редактировать</a></td>
        <td><a class="delete" href="/deleteCar?carId=${car.id}&status=${car.status}">Удалить</a></td>
    </tr>
</table>
</body>
</html>

</body>
</html>
