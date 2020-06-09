
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To Order</title>
    <link rel="stylesheet" type="text/css" href="resources/order.css">
</head>
<body>
<!--<form method="post" action="/order">
    <div class="quantity-block-user">
        Please input phone number<input class="quantity-num-user" type="number" name="phoneNumber" value="+374"/>
        <br>
        Please input adress<input class="quantity-num-user" type="text" name="adress" value=""/>
        <br>
        Please input name <input class="quantity-num-user" type="text" name="name" value=""/>
        <br>
        <input type="submit" value="To order"/>
    </div>
</form>
-->


<div class="login-page">
    <div class="form">
        <form action="/order" method="post" class="login-form">
            <input name="name" type="text" placeholder="Անուն Ազգանուն"/>
            <!--<input name="phoneNumber" type="number" placeholder="Please input phone number"/>-->
            <input type="tel" name="phoneNumber" placeholder="Բջջային հեռախոսահամար" pattern="[0]{1}[0-9]{8}" required><br>
            <input name="adress" type="text" placeholder="Հասցե"/>
            <button>Պատվիրել</button>
            <!--<p class="message">Not registered? <a href="#">Cre</a></p>-->
        </form>
    </div>
</div>
</body>
</html>
