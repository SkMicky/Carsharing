<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 05.11.18
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<fmt:setBundle basename="property.color.colors" var="colorBundle" />
<html>
<head>
    <title>Edit Car</title>
    <link rel="stylesheet" type="text/css" href="/view/css/loginRegForm.css">
</head>
<body>
<c:import url="adminHeader.jsp" />
<main class="main">
    <div class="container-mini">
        <form class="main__form" action="/editCar" method="post">
            <p class="main__title"><fmt:message key="editingCar" bundle="${bundle}" /></p>
            <input type="hidden" name="carId" value="<c:out value="${param['carId']}"/>">
            <input type="text" name="name" class="main__input main__name" placeholder="<fmt:message key="carName" bundle="${bundle}" />" autocomplete="off" required>
            <input type="text" name="gosNo" class="main__input main__gosNo" placeholder="<fmt:message key="gosNo" bundle="${bundle}" />" autocomplete="off" required><br>
            <select name="color" class="main__input main__color" required>
                    <option class="main__input main__color" value="<fmt:message key="chooseColor" bundle="${bundle}" />" disabled><fmt:message key="chooseColor" bundle="${bundle}" /></option>
                <c:forEach items="${colorsList}" var="colors">
                    <option class="main__input main__color" value="${colors}"><fmt:message key="${color.getColorName()}" bundle="${colorBundle}" /></option>
                </c:forEach>
            </select>
            <input type="hidden" name="status" value="<c:out value="${param['status']}"/>">
            <input type="submit" class="main__button" value="<fmt:message key="change" bundle="${bundle}" />">
        </form>
    </div>
</main>
<c:import url="footer.jsp" />
</body>
</html>
