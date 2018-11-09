<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 15.10.18
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
        <a href="/listFreeCars">Заказать машину</a>
        <a href="/carReturn">Завершить заказ</a>
        <a href="/exit">Выход</a>
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
</body>
<c:import url="../footer.jsp" />
</html>
