<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 15.10.18
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<fmt:setBundle basename="property.color.colors" var="colorBundle" />
<html>
<head>
    <title>Booking Cars</title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<c:import url="../userHeader.jsp" />
    <c:if test="${requestScope.cars.size() ne 0}">
    <table>
        <tr>
            <th><fmt:message key="freeCars" bundle="${bundle}" /></th>
        </tr>
        <tr>
            <td><fmt:message key="carName" bundle="${bundle}" /></td>
            <td><fmt:message key="color" bundle="${bundle}" /></td>
            <td><fmt:message key="gosNo" bundle="${bundle}" /></td>
        </tr>
        <c:forEach items="${cars}" var="car">
        <tr>
            <td><c:out value="${car.name}" /></td>
            <td><fmt:message key="${car.color.getColorName()}" bundle="${colorBundle}" /></td>
            <td><c:out value="${car.gosNo}" /></td>
            <td><a class="rental" href="/booking?carId=${car.id}&carName=${car.name}&carColor=${car.color}&carGosNo=${car.gosNo}"><fmt:message key="rentalButton" bundle="${bundle}" /></a></td>
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