<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Carsharing Web App</title>
    <link rel="stylesheet" type="text/css" href="main.css">
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
                    <li class="item"><a class="header-link" href="#car">Машины</a></li>
                    <li class="item"><a class="header-link" href="#about">О нас</a></li>
                </ul>
            </nav>
        </div>
        <div class="login">
            <a href="view/authorization.jsp">Вход</a> <span>или</span>
            <a href="view/registration.jsp">Регистрация</a>
        </div>
    </header>
    <div class="main">
        <div class="baner">
            <div class="baner-text">
                <p class="baner-title">Когда есть выбор!</p>
                <p class="baner-description">Каршеринг-почасовая аренда авто когда и где удобно.</p>
                <p class="baner-price">от 1500 тг/час</p>
            </div>
            <div class="poster">
                <img class="poster-img" src="images/voditel.jpg" alt="Водитель в машине">
            </div>
        </div>
        <div class="about">
            <h2 class="about-title"><a name="about">Немного об аренде авто</a></h2>
            <div class="description-item">
                <img class="item-img" src="images/item1.svg" alt="Выгода">
                <div class="specification">
                    <p class="description-title">Всегда выгодно</p>
                    <p class="description-text">Начните пользоваться нашим сервисом и
                        вы забудете о расходах на личный авто</p>
                </div>
            </div>
            <div class="description-item">
                <img class="item-img" src="images/item2.svg" alt="Мобильность">
                <div class="specification">
                    <p class="description-title">Мобильность</p>
                    <p class="description-text">Управляйте своим временем вместе с нами.
                        Аренда авто когда и где угодно</p>
                </div>
            </div>
            <div class="description-item">
                <img class="item-img" src="images/item3.svg" alt="Все включено">
                <div class="specification">
                    <p class="description-title">Все включено</p>
                    <p class="description-text">Расходы на бензин, мойку и парковку мы берем на себя</p>
                </div>
            </div>
            <div class="description-item">
                <img class="item-img" src="images/item4.svg" alt="Комфорт">
                <div class="specification">
                    <p class="description-title">Комфортнее чем автобус или такси</p>
                    <p class="description-text">Все удобства личного авто для Вас</p>
                </div>
            </div>
        </div>
        <img class="general-car" src="images/car.png" alt="Машина">
        <div class="cars">
            <h1 class="cars-heading"><a name="car">Машины:</a></h1>
            <div class="car-info">
                <img class="car-image" src="images/rio.png" alt="kia">
                <p class="car-title">Kia</p>
            </div>
            <div class="car-info">
                <img class="car-image" src="images/priora.png" alt="Lada priora">
                <p class="car-title">Lada</p>
            </div>
            <div class="car-info">
                <img class="car-image" src="images/hyundai.png" alt="Hyundai">
                <p class="car-title">Hyundai</p>
            </div>
        </div>
    </div>
    <footer class="footer">
        <div class="footer-logo"><h2>Carsharing</h2></div>
        <div class="footer-menu">
            <nav class="footer-nav">
                <ul class="footer-list">
                    <li class="footer-item"><a href="index.jsp">Главная</a></li>
                    <li class="footer-item"><a href="#car">Машины</a></li>
                    <li class="footer-item"><a href="#about">О нас</a></li>
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