<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 01.11.18
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Cars</title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<c:if test="${requestScope.cars.get(0).id ne 0}">
<table>
    <tr>
        <th>Все авто</th>
    </tr>
    <tr>
        <td>ID авто</td>
        <td>Название авто</td>
        <td>Цвет авто</td>
        <td>Гос.номер авто</td>
        <td>Статус авто</td>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td><c:out value="${car.id}" /></td>
            <td><c:out value="${car.name}" /></td>
            <td><c:out value="${car.color}" /></td>
            <td><c:out value="${car.gosNo}" /></td>
            <td>
                <c:if test="${car.status.id eq 1}">
                    <c:out value="Арендовано" />
                </c:if>
                <c:if test="${car.status.id eq 2}">
                    <c:out value="Свободно" />
                </c:if>
            </td>
            <td><a class="edit" href="view/jsp/editCar.jsp?carId=${car.id}&status=${car.status.id}">Редактировать</a></td>
            <td><a class="delete" href="/deleteCar?carId=${car.id}&status=${car.status.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${requestScope.cars.get(0).id eq 0}">
    <h1>Нет машин</h1>
</c:if>
</body>
</html>
