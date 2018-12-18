<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 15.10.18
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title>Success</title>
</head>
<body>
    <div class="success-title">
        <h1 class="success-description"><fmt:message key="success" bundle="${bundle}" /></h1>
    </div>
</body>
</html>
