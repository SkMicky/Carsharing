<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 31.10.18
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/view/css/footer.css">
</head>
<body>
<footer class="footer">
    <div class="footer-logo"><h2>Carsharing</h2></div>
    <div class="footer-menu">
        <nav class="footer-nav">
            <ul class="footer-list">
                <li class="footer-item"><a href="../../index.jsp"><fmt:message key="home" bundle="${bundle}" /></a></li>
                <li class="footer-item"><a href="../../index.jsp#car"><fmt:message key="car-item" bundle="${bundle}" /></a></li>
                <li class="footer-item"><a href="../../index.jsp#about"><fmt:message key="about" bundle="${bundle}" /></a></li>
            </ul>
        </nav>
    </div>
    <div class="footer-contacts">
        <h4 class="phone"><fmt:message key="contacts" bundle="${bundle}" />:</h4>
        <span class="phone-number">87715284382</span>
    </div>
    <div class="copyright">
        <h5>Carsharing. <fmt:message key="rights" bundle="${bundle}" />!</h5>
    </div>
</footer>
</body>
</html>
