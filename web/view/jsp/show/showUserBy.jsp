<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 05.11.18
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title><fmt:message key="userBy" bundle="${bundle}" /></title>
    <link rel="stylesheet" type="text/css" href="../../css/showGeneral.css">
</head>
<body>
<c:import url="../adminHeader.jsp" />
<c:if test="${requestScope.user.id ne 0}">
<table>
    <tr>
        <th><fmt:message key="userBy" bundle="${bundle}" /></th>
    </tr>
    <tr>
        <td><fmt:message key="userId" bundle="${bundle}" /></td>
        <td><fmt:message key="lastName" bundle="${bundle}" /></td>
        <td><fmt:message key="firstName" bundle="${bundle}" /></td>
        <td><fmt:message key="birthDate" bundle="${bundle}" /></td>
        <td><fmt:message key="phoneNumber" bundle="${bundle}" /></td>
        <td>E-mail</td>
        <td><fmt:message key="iin" bundle="${bundle}" /></td>
        <td><fmt:message key="address" bundle="${bundle}" /></td>
        <td><fmt:message key="driverLicense" bundle="${bundle}" /></td>
        <td><fmt:message key="userLogin" bundle="${bundle}" /></td>
    </tr>
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.lastName}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.birthday}" /></td>
            <td><c:out value="${user.phoneNumber}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.iin}" /></td>
            <td><c:out value="${user.userAddress}" /></td>
            <td><c:out value="${user.driverLicense}" /></td>
            <td><c:out value="${user.login}" /></td>
            <td><a class="delete" href="/deleteUser?userId=${user.id}"><fmt:message key="deleteButton" bundle="${bundle}" /></a></td>
        </tr>
</table>
</c:if>
<c:if test="${requestScope.user.id eq 0}">
    <h1><fmt:message key="noUsers" bundle="${bundle}" /></h1>
</c:if>
<c:import url="../footer.jsp" />
</body>
</html>