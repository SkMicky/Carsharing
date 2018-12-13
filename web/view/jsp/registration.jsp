<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="/view/css/header.css">
    <link rel="stylesheet" type="text/css" href="/view/css/loginRegForm.css">
</head>
<body>
    <header class="header">
        <div class = "logo">
            <h1>Carsharing</h1>
        </div>
        <div class="menu-bar">
            <nav class="navigation">
                <ul class="list">
                    <li class="item"><a class="header-link" href="/"><fmt:message key="home" bundle="${bundle}" /></a></li>
                    <li class="item"><a class="header-link" href="/#car"><fmt:message key="car-item" bundle="${bundle}" /></a></li>
                    <li class="item"><a class="header-link" href="/#about"><fmt:message key="about" bundle="${bundle}" /></a></li>
                </ul>
            </nav>
        </div>
    </header>
    <main class="main">
        <div class="container-mini">
            <form class="main__form" action="/registration" method="post">
                <p class="main__title"><fmt:message key="registration" bundle="${bundle}" /></p>
                <input type="text" name="lastName" class="main__input main__lastName" placeholder="<fmt:message key="lastName" bundle="${bundle}" />" autocomplete="off">
                <input type="text" name="firstName" class="main__input main__firstName" placeholder="<fmt:message key="firstName" bundle="${bundle}" />" autocomplete="off">
                <input type="date" name="birthDate" class="main__input main__birthDate" placeholder="<fmt:message key="birthDate" bundle="${bundle}" />">
                <input type="tel" name="phoneNumber" class="main__input main__phoneNumber" placeholder="<fmt:message key="phoneNumber" bundle="${bundle}" />" autocomplete="off" required>
                <input type="email" name="email" class="main__input main__email" placeholder="e-mail" required autocomplete="off">
                <input type="number" name="iin" class="main__input main__iin" placeholder="<fmt:message key="iin" bundle="${bundle}" />" required autocomplete="off">
                <input type="text" name="address" class="main__input main__address" placeholder="<fmt:message key="address" bundle="${bundle}" />" autocomplete="off">
                <input type="number" name="driverLicense" class="main__input main__driverLicense" placeholder="<fmt:message key="driverLicense" bundle="${bundle}" />" required autocomplete="off">
                <input type="text" name="login" class="main__input main__login" placeholder="<fmt:message key="userLogin" bundle="${bundle}" />" required autocomplete="off">
                <input type="password" name="password" class="main__input main__password" placeholder="<fmt:message key="password" bundle="${bundle}" />" required><br>
                <input type="submit" class="main__button" value="<fmt:message key="doRegistration" bundle="${bundle}" />">
            </form>
        </div>
    </main>
    <c:import url="footer.jsp" />
</body>
</html>