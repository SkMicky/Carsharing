<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Autorization</title>
    <link rel="stylesheet" type="text/css" href="/view/css/header.css">
    <link rel="stylesheet" type="text/css" href="/view/css/loginRegForm.css">
</head>
<body>
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
            <form class="main__form" action="/authorization" method="post">
                <p class="main__title"><fmt:message key="login" bundle="${bundle}" /></p>
                <input type="text" name="login" class="main__input main__login" placeholder="<fmt:message key="userLogin" bundle="${bundle}" />" autocomplete="off">
                <input type="password" name="password" class="main__input main__password" placeholder="<fmt:message key="password" bundle="${bundle}" />"><br>
                <input type="submit" class="main__button" value="<fmt:message key="login" bundle="${bundle}" />">
            </form>
        </div>
    </main>
    <c:import url="footer.jsp" />
</body>
</html>