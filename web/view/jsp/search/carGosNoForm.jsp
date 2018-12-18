<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 06.11.18
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title>Search Form</title>
    <link rel="stylesheet" type="text/css" href="../../css/loginRegForm.css">
</head>
<body>
<c:import url="../adminHeader.jsp" />
<main class="main">
    <div class="form-container">
        <form class="main__form" action="/showCarByGosNo" method="post">
            <input type="search" name="carGosNo" class="main__input main__search"
                   placeholder="<fmt:message key="gosNo" bundle="${bundle}" />" autocomplete="off">
            <input type="submit" class="main__button" value="<fmt:message key="search" bundle="${bundle}" />">
        </form>
    </div>
</main>
<c:import url="../footer.jsp" />
</body>
</html>
