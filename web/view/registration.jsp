<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>

    <style>
        body{
            font-family: Montserrat,sans-serif;
            background-image: url("../images/footer_bg.jpg");
            background-repeat: no-repeat;
            background-position: center bottom;
        }

        .header{
            position: relative;
            padding: 0 2%;
            background-color: rgba(255, 255, 255, 0.7);
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            align-items: center;
            z-index: 1;
        }

        .menu-bar{
            flex:1;
            max-width: 550px;
        }

        .list{
            display:grid;
            list-style-type: none;
            grid-template-columns: 1fr 1fr 1fr;
        }

        .header-link{
            display: block;
            text-decoration: none;
            color: #000000;
            font-size: 1em;
            font-weight: bold;
            text-align: center;
            padding:15px 0;
        }

        .header-link:hover{
            color: darkred;
        }

        .login a{
            color:#000000;
        }

        .container-mini {
            margin: 0 auto;
            max-width: 800px;
            width: 100%;
        }

        .main__title {
            width: 100%;
            text-align: center;
            font-size: 2em;
            font-weight: bold;
            text-shadow: 0 0 5px white;
        }

        .main__form {
            display: flex;
            justify-content: center;
            align-content: center;
            flex-wrap: wrap;
            padding: 80px 0 100px;
        }

        .main__input {
            margin: 20px 0 10px;
            width: 80%;
            padding: 15px 10px;
            border-radius: 5px;
            border: 2px solid gold;
            outline: 0;
            font-size: 16px;
            background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0), rgba(230, 230, 230, 1));
        }

        .main__button {
            margin-top: 40px;
            padding: 15px 25px;
            width: 30%;
            transform: translateY(-5px) scale(1.05);
            border: none;
            outline: 0;
            font-size: 1em;
            font-weight: bold;
            text-transform: uppercase;
            box-shadow: 0 5px 5px 0 gray;
            background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0), rgba(100, 100, 100, 1));
            color: white;
            cursor: pointer;
        }

        .main__button:active {
            transform: scale(1);
            box-shadow: none;
        }

        .footer{
            display: grid;
            grid-template-columns: 2fr 1fr 1fr;
            column-gap: 40px;
            align-items: center;
        }

        .footer-logo{
            text-align: center;
            font-size: 2em;
            font-weight: 700;
            color: white;
        }

        .footer-menu{
            color: white;
        }

        .footer-contacts{
            color: white;
        }

        .footer-contacts .phone{
            margin: 0;
            margin-bottom: 12px;
        }

        .copyright{
            grid-column: 1 / -1;
            justify-self: center;
        }

        .copyright h5{
            color: white;
            font-weight: 100;
        }
    </style>
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
    <footer class="footer">
        <div class="footer-logo"><h2>Carsharing</h2></div>
        <div class="footer-menu">
            <nav class="footer-nav">
                <ul class="footer-list">
                    <li class="footer-item">Главная</li>
                    <li class="footer-item">Машины</li>
                    <li class="footer-item">О нас</li>
                </ul>
            </nav>
        </div>
        <div class="footer-contacts">
            <h4 class="phone">Контакты:</h4>
            <span class="phone-number">87715284382</span>
        </div>
        <div class="copyright">
            <h5>Carsharing. Все права защищены!</h5>
        </div>
    </footer>

</body>
</html>