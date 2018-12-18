<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 31.10.18
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
<head>
    <title>Administrator Page</title>
    <link rel="stylesheet" href="/view/css/loginRegForm.css">
</head>
<body>
    <c:import url="adminHeader.jsp" />
    <main class="main">
        <div class="container-mini">
            <form action="/view/jsp/editUser.jsp" method="post">
                <h2><fmt:message key="lastName" bundle="${bundle}" />: ${authorizedUser.lastName}</h2>
                <h2><fmt:message key="firstName" bundle="${bundle}" />: ${authorizedUser.firstName}</h2>
                <h2><fmt:message key="birthDate" bundle="${bundle}" />: ${authorizedUser.birthday}</h2>
                <h2><fmt:message key="phoneNumber" bundle="${bundle}" />: ${authorizedUser.phoneNumber}</h2>
                <h2>E-mail: ${authorizedUser.email}</h2>
                <h2><fmt:message key="iin" bundle="${bundle}" />: ${authorizedUser.iin}</h2>
                <h2><fmt:message key="address" bundle="${bundle}" />: ${authorizedUser.userAddress}</h2>
                <h2><fmt:message key="driverLicense" bundle="${bundle}" />: ${authorizedUser.driverLicense}</h2>
                <h2><fmt:message key="userLogin" bundle="${bundle}" />: ${authorizedUser.login}</h2>
                <input type="submit" class="main__button edit_user" value="<fmt:message key="changeProfile" bundle="${bundle}" />">
            </form>
        </div>
    </main>
    <c:import url="footer.jsp" />
</body>
</html>