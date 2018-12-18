<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 15.10.18
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title>Error</title>
</head>
<body>
<div class="error-title">
    <h1 class="error-description"><fmt:message key="error" bundle="${bundle}" /></h1>
</div>
</body>
</html>
