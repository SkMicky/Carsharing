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

<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title>Car Returning</title>
    <link rel="stylesheet" type="text/css" href="/view/css/main.css">
</head>
<body>
<c:import url="userHeader.jsp" />
    <c:if test="${requestScope.orders.size() ne 0}">
        <div class="order-container">
            <h1><fmt:message key="returnCar" bundle="${bundle}" />!</h1>
            <c:choose>
                <c:when test="${totalTime == 1}">
                    <span class="totalTime"><fmt:message key="rentTime" bundle="${bundle}" />: ${totalTime} <fmt:message key="minute" bundle="${bundle}" /></span>
                </c:when>
                <c:when test="${totalTime > 1 && totalTime < 5}">
                    <span class="totalTime"><fmt:message key="rentTime" bundle="${bundle}" />: ${totalTime} <fmt:message key="minutes" bundle="${bundle}" /></span>
                </c:when>
                <c:otherwise>
                    <span class="totalTime"><fmt:message key="rentTime" bundle="${bundle}" />: ${totalTime} <fmt:message key="minut" bundle="${bundle}" /></span>
                </c:otherwise>
            </c:choose>
            <h1><fmt:message key="thanks" bundle="${bundle}" />!</h1>
        </div>
    </c:if>
    <c:if test="${requestScope.orders.size() eq 0}">
        <h1><fmt:message key="haven'tOrders" bundle="${bundle}" />.</h1>
    </c:if>
<c:import url="footer.jsp" />
</body>
</html>
