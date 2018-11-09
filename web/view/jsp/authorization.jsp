<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Autorization</title>
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
            <form class="main__form" action="/authorization" method="post">
                <p class="main__title">Вход</p>
                <input type="text" name="login" class="main__input main__login" placeholder="login" autocomplete="off">
                <input type="password" name="password" class="main__input main__password" placeholder="password"><br>
                <input type="submit" class="main__button" value="Войти">
            </form>
        </div>
    </main>
    <c:import url="footer.jsp" />
</body>
</html>