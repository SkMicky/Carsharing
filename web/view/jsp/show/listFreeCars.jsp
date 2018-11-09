<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 15.10.18
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../userPage.jsp"%>
<html>
<head>
    <title>Booking Cars</title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
    <c:if test="${requestScope.cars.get(0).id ne 0}">
    <table>
        <tr>
            <th>Доступные авто</th>
        </tr>
        <tr>
            <td>Название авто</td>
            <td>Цвет авто</td>
            <td>Гос.номер авто</td>
        </tr>
        <c:forEach items="${cars}" var="car">
        <tr>
            <td><c:out value="${car.name}" /></td>
            <td><c:out value="${car.color}" /></td>
            <td><c:out value="${car.gosNo}" /></td>
            <td><a href="/booking?carId=${car.id}&carName=${car.name}&carColor=${car.color}&carGosNo=${car.gosNo}">Забронировать</a></td>
        </tr>
        </c:forEach>
    </table>
    </c:if>
    <c:if test="${requestScope.cars.get(0).id eq 0}">
        <h1>Нет свободных машин</h1>
    </c:if>
</body>
</html>