<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
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
                    <li class="item"><a class="header-link" href="/">Главная</a></li>
                    <li class="item"><a class="header-link" href="/#car">Машины</a></li>
                    <li class="item"><a class="header-link" href="/#about">О нас</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <main class="main">
        <div class="container-mini">
            <form class="main__form" action="/registration" method="post">
                <p class="main__title">Регистрация</p>
                <input type="text" name="lastName" class="main__input main__lastName" placeholder="Фамилия" autocomplete="off">
                <input type="text" name="firstName" class="main__input main__firstName" placeholder="Имя" autocomplete="off">
                <input type="date" name="birthDate" class="main__input main__birthDate" placeholder="Дата рождения">
                <input type="tel" name="phoneNumber" class="main__input main__phoneNumber" placeholder="Номер телефона" autocomplete="off" required>
                <input type="email" name="email" class="main__input main__email" placeholder="e-mail" required autocomplete="off">
                <input type="number" name="iin" class="main__input main__iin" placeholder="ИИН" required autocomplete="off">
                <input type="text" name="address" class="main__input main__address" placeholder="Адрес" autocomplete="off">
                <input type="number" name="driverLicense" class="main__input main__driverLicense" placeholder="Водительское удостоверение" required autocomplete="off">
                <input type="text" name="login" class="main__input main__login" placeholder="Логин" required autocomplete="off">
                <input type="password" name="password" class="main__input main__password" placeholder="Password" required><br>
                <input type="submit" class="main__button" value="Зарегистрироваться">
            </form>
        </div>
    </main>
    <c:import url="footer.jsp" />
</body>
</html>