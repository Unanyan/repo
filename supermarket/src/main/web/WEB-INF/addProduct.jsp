<%--
  Created by IntelliJ IDEA.
  User: vrezh
  Date: 22.04.20
  Time: 00:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/addProduct" method="post">
    <label for="name"> Name </label>
    <input id="name" name="nameProduct" type="text"/>
    <br>

    <label for="price"> Name </label>
    <input id="price" name="priceProduct" type="text"/>
    <br>

    <label for="imagePath"> Image Path </label>
    <input id="imagePath" name="imagePathProduct" type="text"/>
    <br>

    <label for="aboutProduct"> About Product </label>
    <input id="aboutProduct" name="aboutProduct" type="text"/>
    <br>

    <label for="type"> Product Type </label>
    <input id="type" name="typeProduct" type="text"/>
    <br>

    <label for="count"> Product Count </label>
    <input id="count" name="countProduct" type="number"/>
    <br>

    <label for="weight"> Product Weight </label>
    <input id="weight" name="weightProduct" type="number"/>
    <br>

    <input type="submit" value="Add Product">
    <a href="/register">Register</a>
</form>
</body>
</html>-->

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="resources/addProduct.css">
</head>
<body>

<div class="login-page">
    <div class="form">
        <form action="/addProduct" method="post" class="login-form">

            <input name="nameProduct" type="text" placeholder="Name"/>
            <input name="priceProduct" type="text" placeholder="Price"/>
            <input name="imagePathProduct" type="text" placeholder="Image path"/>
            <!--<label>Photo<input type="file" name="photo" size="50"></label>-->
            <input name="aboutProduct" type="text" placeholder="About product"/>
            <input name="typeProduct" type="text" placeholder="Product type"/>
            <input name="countProduct" type="number" placeholder="Product count"/>
            <input name="weightProduct" type="number" placeholder="Product weight"/>
            <button>Add Product</button>
            <!--<p class="message">Not registered? <a href="#">Cre</a></p>-->
        </form>
    </div>
</div>
</body>
</html>
