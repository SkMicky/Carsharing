<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 03.11.18
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Users</title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<c:import url="../adminHeader.jsp" />
<c:if test="${requestScope.users.get(0).id ne 0}">
<table>
    <tr>
        <th>Все пользователи</th>
    </tr>
    <tr>
        <td>ID пользователя</td>
        <td>Фамилия</td>
        <td>Имя</td>
        <td>Дата рождения</td>
        <td>Номер телефона</td>
        <td>Электронный адрес</td>
        <td>ИИН</td>
        <td>Домашний адрес</td>
        <td>Водительские права</td>
        <td>Логин</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.lastName}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.birthday}" /></td>
            <td><c:out value="${user.phoneNumber}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.IIN}" /></td>
            <td><c:out value="${user.userAddress}" /></td>
            <td><c:out value="${user.driverLicense}" /></td>
            <td><c:out value="${user.login}" /></td>
            <td><a class="delete" href="/deleteUser?userId=${user.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${requestScope.users.get(0).id eq 0}">
    <h1>Нет пользователей</h1>
</c:if>
<c:import url="../footer.jsp" />
</body>
</html>
