<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.site" var="bundle" />
<html>
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Carsharing Web App</title>
    <link rel="stylesheet" type="text/css" href="/view/css/header.css">
    <link rel="stylesheet" type="text/css" href="/view/css/main.css">
  </head>
  <body>
  <ul class="lang_menu">
      <li class="lang-item"><fmt:message key="lang" bundle="${bundle}" />
          <ul class="flag-menu">
              <c:forEach items="${langsList}" var="language">
                  <li class="flag-item"><a href="/?lang=${language.getLocale()}">${language}</a></li>
              </c:forEach>
          </ul>
      </li>
  </ul>
    <header class="header">
        <div class = "logo">
            <h1>Carsharing</h1>
        </div>
        <div class="menu-bar">
            <nav class="navigation">
                <ul class="list">
                    <li class="item"><a class="header-link" href="/"><fmt:message key="home" bundle="${bundle}" /></a></li>
                    <li class="item"><a class="header-link" href="#car"><fmt:message key="car-item" bundle="${bundle}" /></a></li>
                    <li class="item"><a class="header-link" href="#about"><fmt:message key="about" bundle="${bundle}" /></a></li>
                </ul>
            </nav>
        </div>
        <div class="login">
            <a href="view/jsp/authorization.jsp"><fmt:message key="login" bundle="${bundle}" /></a> <span><fmt:message key="or" bundle="${bundle}" /></span>
            <a href="view/jsp/registration.jsp"><fmt:message key="registration" bundle="${bundle}" /></a>
        </div>
    </header>
    <div class="main">
        <div class="baner">
            <div class="baner-text">
                <p class="baner-title"><fmt:message key="choise" bundle="${bundle}" /></p>
                <p class="baner-description"><fmt:message key="carsharing" bundle="${bundle}" /></p>
                <p class="baner-price"><fmt:message key="prices" bundle="${bundle}" /></p>
            </div>
            <div class="poster">
                <img class="poster-img" src="/view/images/voditel.jpg" alt="Водитель в машине">
            </div>
        </div>
        <div class="about">
            <h2 class="about-title"><a name="about"><fmt:message key="about-rental" bundle="${bundle}" /></a></h2>
            <div class="description-item">
                <img class="item-img" src="/view/images/item1.svg" alt="Выгода">
                <div class="specification">
                    <p class="description-title"><fmt:message key="benefit" bundle="${bundle}" /></p>
                    <p class="description-text"><fmt:message key="benefit-description" bundle="${bundle}" /></p>
                </div>
            </div>
            <div class="description-item">
                <img class="item-img" src="/view/images/item2.svg" alt="Мобильность">
                <div class="specification">
                    <p class="description-title"><fmt:message key="mobility" bundle="${bundle}" /></p>
                    <p class="description-text"><fmt:message key="mobility-description" bundle="${bundle}" /></p>
                </div>
            </div>
            <div class="description-item">
                <img class="item-img" src="/view/images/item3.svg" alt="Все включено">
                <div class="specification">
                    <p class="description-title"><fmt:message key="inclusiveness" bundle="${bundle}" /></p>
                    <p class="description-text"><fmt:message key="inclusiveness-description" bundle="${bundle}" /></p>
                </div>
            </div>
            <div class="description-item">
                <img class="item-img" src="/view/images/item4.svg" alt="Комфорт">
                <div class="specification">
                    <p class="description-title"><fmt:message key="comfortability" bundle="${bundle}" /></p>
                    <p class="description-text"><fmt:message key="comfortability-description" bundle="${bundle}" /></p>
                </div>
            </div>
        </div>
        <img class="general-car" src="/view/images/car.png" alt="Машина">
        <div class="cars">
            <h1 class="cars-heading"><a name="car"><fmt:message key="car-item" bundle="${bundle}" />:</a></h1>
            <div class="car-info">
                <img class="car-image" src="/view/images/rio.png" alt="kia">
                <p class="car-title">Kia</p>
            </div>
            <div class="car-info">
                <img class="car-image" src="/view/images/priora.png" alt="Lada priora">
                <p class="car-title"><fmt:message key="lada" bundle="${bundle}" /></p>
            </div>
            <div class="car-info">
                <img class="car-image" src="/view/images/hyundai.png" alt="Hyundai">
                <p class="car-title">Hyundai</p>
            </div>
        </div>
        <c:import url="view/jsp/footer.jsp" />
    </div>
  </body>
</html>