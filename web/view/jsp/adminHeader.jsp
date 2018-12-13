<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 03.11.18
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" type="text/css" href="/view/css/header.css">
</head>
<body>
<h5 class="welcome"><fmt:message key="welcome" bundle="${bundle}" /> ${authorizedUser.lastName} ${authorizedUser.firstName}</h5>
<c:if test="${requestScope['javax.servlet.forward.request_uri'] eq '/view/jsp/success.jsp'}">
    <div class="success">
        <c:import url="/view/jsp/success.jsp" /> ${requestScope.success}
    </div>
</c:if>
<c:if test="${requestScope['javax.servlet.forward.request_uri'] eq '/view/jsp/error.jsp'}">
    <div class="error">
        <c:import url="/view/jsp/error.jsp" /> ${requestScope.error}
    </div>
</c:if>
<header class="header">
    <div class="logo">
        <h1>Carsharing</h1>
    </div>
    <div class="menu-bar">
        <nav class="navigation">
            <ul class="list">
                <li class="item"><a class="header-link" href="/view/jsp/adminPage.jsp"><fmt:message key="home" bundle="${bundle}" /></a></li>
                <li class="item"><fmt:message key="car-item" bundle="${bundle}" />
                    <ul class="submenu">
                        <li><a class="header-link" href="/showAllCars"><fmt:message key="allCars" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/carIdForm.jsp"><fmt:message key="carsById" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/carGosNoForm.jsp"><fmt:message key="carsByGosNo" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/carsColorForm.jsp"><fmt:message key="carsByColor" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/addCar.jsp"><fmt:message key="addCar" bundle="${bundle}" /></a></li>
                    </ul>
                </li>
                <li class="item"><fmt:message key="users" bundle="${bundle}" />
                    <ul class="submenu">
                        <li><a class="header-link" href="/showAllUsers"><fmt:message key="allUsers" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/userIdForm.jsp"><fmt:message key="userById" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/userLoginForm.jsp"><fmt:message key="userByLogin" bundle="${bundle}" /></a></li>
                    </ul>
                </li>
                <li class="item"><fmt:message key="orders" bundle="${bundle}" />
                    <ul class="submenu">
                        <li><a class="header-link" href="/showAllOrders"><fmt:message key="allOrders" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/orderIdForm.jsp"><fmt:message key="orderById" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/ordersByUserIdForm.jsp"><fmt:message key="orderByUserId" bundle="${bundle}" /></a></li>
                        <li><a class="header-link" href="/view/jsp/search/ordersByCarIdForm.jsp"><fmt:message key="orderByCarId" bundle="${bundle}" /></a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="login">
        <a href="/exit"><fmt:message key="exit" bundle="${bundle}" /></a>
    </div>
</header>
</body>
</html>
