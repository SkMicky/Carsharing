<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 03.11.18
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Car</title>
    <link rel="stylesheet" type="text/css" href="/view/css/loginRegForm.css">
</head>
<body>
<c:import url="adminHeader.jsp" />
<main class="main">
    <div class="container-mini">
        <form class="main__form" action="/addCar" method="post">
            <p class="main__title">Добавление машины</p>
            <input type="text" name="name" class="main__input main__name" placeholder="Название" autocomplete="off" required>
            <input type="text" name="gosNo" class="main__input main__gosNo" placeholder="Гос.номер" autocomplete="off" required><br>
            <input type="color" name="color" class="main__input main__color" placeholder="Цвет" autocomplete="off" required>
            <input type="submit" class="main__button" value="Добавить">
        </form>
    </div>
</main>
<c:import url="footer.jsp" />
</body>
</html>
