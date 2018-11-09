<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 03.11.18
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" type="text/css" href="/view/css/header-admin.css">
</head>
<body>
<h5 class="welcome">Добро пожаловать ${authorizedUser.lastName} ${authorizedUser.firstName}</h5>
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
                <li class="item"><a class="header-link" href="/view/jsp/adminPage.jsp">Главная</a></li>
                <li class="item">Машины
                    <ul class="submenu">
                        <li><a class="header-link" href="/showAllCars">Все машины</a></li>
                        <li><a class="header-link" href="/view/jsp/search/carIdForm.jsp">Машины по ID</a></li>
                        <li><a class="header-link" href="/view/jsp/search/carGosNoForm.jsp">Машины по гос.номеру</a></li>
                        <li><a class="header-link" href="/view/jsp/search/carsColorForm.jsp">Машины по цвету</a></li>
                        <li><a class="header-link" href="/view/jsp/addCar.jsp">Добавить машину</a></li>
                    </ul>
                </li>
                <li class="item">Пользователи
                    <ul class="submenu">
                        <li><a class="header-link" href="/showAllUsers">Все пользователи</a></li>
                        <li><a class="header-link" href="/view/jsp/search/userIdForm.jsp">Пользователь по ID</a></li>
                        <li><a class="header-link" href="/view/jsp/search/userLoginForm.jsp">Пользователь по логину</a></li>
                    </ul>
                </li>
                <li class="item">Заказы
                    <ul class="submenu">
                        <li><a class="header-link" href="/showAllOrders">Все заказы</a></li>
                        <li><a class="header-link" href="/view/jsp/search/orderIdForm.jsp">Заказы по ID</a></li>
                        <li><a class="header-link" href="/view/jsp/search/ordersByUserIdForm.jsp">Заказы по ID пользователя</a></li>
                        <li><a class="header-link" href="/view/jsp/search/ordersByCarIdForm.jsp">Заказы по ID машины</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="login">
        <a href="/exit">Выход</a>
    </div>
</header>
</body>
</html>
