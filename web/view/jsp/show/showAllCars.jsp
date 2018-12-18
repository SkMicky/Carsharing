<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 01.11.18
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<fmt:setBundle basename="property.color.colors" var="colorBundle" />
<html>
<head>
    <title>Show All Cars</title>
    <link rel="stylesheet" type="text/css" href="/view/css/showGeneral.css">
</head>
<body>
<c:if test="${requestScope.cars.size() ne 0}">
<c:import url="../adminHeader.jsp" />
<table>
    <tr>
        <th><fmt:message key="allCars" bundle="${bundle}" /></th>
    </tr>
    <tr>
        <td><fmt:message key="carId" bundle="${bundle}" /></td>
        <td><fmt:message key="carName" bundle="${bundle}" /></td>
        <td><fmt:message key="color" bundle="${bundle}" /></td>
        <td><fmt:message key="gosNo" bundle="${bundle}" /></td>
        <td><fmt:message key="carStatus" bundle="${bundle}" /></td>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td><c:out value="${car.id}" /></td>
            <td><c:out value="${car.name}" /></td>
            <td><fmt:message key="${car.color.getColorName()}" bundle="${colorBundle}" /></td>
            <td><c:out value="${car.gosNo}" /></td>
            <td>
                <c:if test="${car.status.id eq 1}">
                    <fmt:message key="isRent" bundle="${bundle}" />
                </c:if>
                <c:if test="${car.status.id eq 2}">
                    <fmt:message key="isFree" bundle="${bundle}" />
                </c:if>
            </td>
            <td><a class="edit" href="view/jsp/editCar.jsp?carId=${car.id}&status=${car.status.id}"><fmt:message key="editButton" bundle="${bundle}" /></a></td>
            <td><a class="delete" href="/deleteCar?carId=${car.id}&status=${car.status.id}"><fmt:message key="deleteButton" bundle="${bundle}" /></a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${requestScope.cars.size() eq 0}">
    <h1><fmt:message key="noCars" bundle="${bundle}" /></h1>
</c:if>
<c:import url="../footer.jsp" />
</body>
</html>