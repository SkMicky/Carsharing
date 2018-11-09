<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 05.11.18
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <p class="main__title">Изменение профиля пользователя</p>
            <input type="text" name="lastName" class="main__input main__lastName" placeholder="Фамилия" autocomplete="off">
            <input type="text" name="firstName" class="main__input main__firstName" placeholder="Имя" autocomplete="off">
            <input type="date" name="birthDate" class="main__input main__birthDate" placeholder="Дата рождения">
            <input type="tel" name="phoneNumber" class="main__input main__phoneNumber" placeholder="Номер телефона" autocomplete="off" required>
            <input type="email" name="email" class="main__input main__email" placeholder="e-mail" required autocomplete="off">
            <input type="number" name="iin" class="main__input main__iin" placeholder="ИИН" required autocomplete="off">
            <input type="text" name="address" class="main__input main__address" placeholder="Адрес" autocomplete="off">
            <input type="number" name="driverLicense" class="main__input main__driverLicense" placeholder="Водительское удостоверение" required autocomplete="off">
            <input type="text" name="login" class="main__input main__login" placeholder="Логин" required autocomplete="off">
            <input type="submit" class="main__button" value="Изменить">
        </form>
    </div>
</main>
<c:import url="footer.jsp" />
</body>
</html>

