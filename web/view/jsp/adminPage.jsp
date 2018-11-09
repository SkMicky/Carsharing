<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 31.10.18
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <h2>Фамилия: ${authorizedUser.lastName}</h2>
                <h2>Имя: ${authorizedUser.firstName}</h2>
                <h2>Дата рождения: ${authorizedUser.birthday}</h2>
                <h2>Номер телефона: ${authorizedUser.phoneNumber}</h2>
                <h2>E-mail: ${authorizedUser.email}</h2>
                <h2>ИИН: ${authorizedUser.IIN}</h2>
                <h2>Адрес: ${authorizedUser.userAddress}</h2>
                <h2>Водительские права: ${authorizedUser.driverLicense}</h2>
                <h2>Логин: ${authorizedUser.login}</h2>
                <input type="submit" class="main__button edit_user" value="Изменить профиль">
            </form>
        </div>
    </main>
    <c:import url="footer.jsp" />
</body>
</html>