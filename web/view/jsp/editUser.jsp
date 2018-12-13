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
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" type="text/css" href="/view/css/loginRegForm.css">
</head>
<body>
<c:import url="adminHeader.jsp" />
<main class="main">
    <div class="container-mini">
        <form class="main__form" action="/editUser" method="post">
            <p class="main__title"><fmt:message key="changeProfile" bundle="${bundle}" /></p>
            <input type="text" name="lastName" class="main__input main__lastName" placeholder="<fmt:message key="lastName" bundle="${bundle}" />" autocomplete="off">
            <input type="text" name="firstName" class="main__input main__firstName" placeholder="<fmt:message key="firstName" bundle="${bundle}" />" autocomplete="off">
            <input type="date" name="birthDate" class="main__input main__birthDate" placeholder="<fmt:message key="birthDate" bundle="${bundle}" />">
            <input type="tel" name="phoneNumber" class="main__input main__phoneNumber" placeholder="<fmt:message key="phoneNumber" bundle="${bundle}" />" autocomplete="off" required>
            <input type="email" name="email" class="main__input main__email" placeholder="e-mail" required autocomplete="off">
            <input type="number" name="iin" class="main__input main__iin" placeholder="<fmt:message key="iin" bundle="${bundle}" />" required autocomplete="off">
            <input type="text" name="address" class="main__input main__address" placeholder="<fmt:message key="address" bundle="${bundle}" />" autocomplete="off">
            <input type="number" name="driverLicense" class="main__input main__driverLicense" placeholder="<fmt:message key="driverLicense" bundle="${bundle}" />" required autocomplete="off">
            <input type="text" name="login" class="main__input main__login" placeholder="<fmt:message key="userLogin" bundle="${bundle}" />" required autocomplete="off">
            <input type="submit" class="main__button" value="<fmt:message key="change" bundle="${bundle}" />">
        </form>
    </div>
</main>
<c:import url="footer.jsp" />
</body>
</html>

