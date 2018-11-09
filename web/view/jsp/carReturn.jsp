<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 30.10.18
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Returning</title>
</head>
<body>
    <c:if test="${requestScope.orders.get(0).id ne 0}">
        Car was returned successful!
        <div class="order-container">
            <c:choose>
                <c:when test="${totalTime == 1}">
                    <span class="totalTime">Время аренды: ${totalTime} минута</span>
                </c:when>
                <c:when test="${totalTime > 1 && totalTime < 5}">
                    <span class="totalTime">Время аренды: ${totalTime} минуты</span>
                </c:when>
                <c:otherwise>
                    <span class="totalTime">Время аренды: ${totalTime} минут</span>
                </c:otherwise>
            </c:choose>
            <h1>Спасибо Вам</h1>
        </div>
    </c:if>
    <c:if test="${requestScope.orders.get(0).id eq 0}">
        <h1>У вас нет заказов</h1>
    </c:if>
</body>
</html>
