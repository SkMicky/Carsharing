<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 11.12.18
  Time: 15:16
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
<header class="header">
    <div class = "logo">
        <h1>Carsharing</h1>
    </div>
    <div class="menu-bar">
        <nav class="navigation">
            <ul class="list">
                <li class="item"><a class="header-link" href="/listFreeCars"><fmt:message key="rentCar" bundle="${bundle}" /></a></li>
                <li class="item"><a class="header-link" href="/carReturn"><fmt:message key="completingOrder" bundle="${bundle}" /></a></li>
            </ul>
        </nav>
    </div>
    <div class="login">
        <a href="/exit"><fmt:message key="exit" bundle="${bundle}" /></a>
    </div>
</header>
</body>
</html>
