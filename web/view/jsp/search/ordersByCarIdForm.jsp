<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 06.11.18
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Form</title>
    <link rel="stylesheet" type="text/css" href="../../css/loginRegForm.css">
</head>
<body>
<main class="main">
    <div class="form-container">
        <form class="main__form" action="/showOrdersByCarId" method="post">
            <input type="search" name="carId" class="main__input main__search"
                   placeholder="Введите ID машины" autocomplete="off">
            <input type="submit" class="main__button" value="Найти">
        </form>
    </div>
</main>
</body>
</html>
