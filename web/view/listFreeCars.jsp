<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 26.10.18
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="UserPage.jsp"%>
<html>
<head>
    <title>Booking Cars</title>
</head>
<body>
    <table>
        <tr>
            <th>Доступные машины:</th>
        </tr>
        <tr>
            <td>Название машины</td>
            <td>Марка машины</td>
            <td>Госномер машины</td>
            <td>Цвет машины</td>
        </tr>
        <c:forEach items="${cars}" var="car" />
        <tr>
            <td><c:out value="${car.firstName}" /></td>
            <td><c:out value="${car.lastName}" /></td>
            <td><c:out value="${car.birthday}" /></td>
            <td><c:out value="${car.phoneNumber}" /></td>
        </tr>
    </table>
</body>
</html>
