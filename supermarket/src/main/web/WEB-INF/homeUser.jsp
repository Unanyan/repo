<%@ page import="java.util.List" %>
<%@ page import="am.vtc.chat.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: vrezh
  Date: 22.04.20
  Time: 00:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>store</title>

    <link rel="icon" href="img/core-img/favicon.ico">

    <link rel="stylesheet" href="resources/core-style.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" type="text/css" href="resources/menu.css">

</head>

<body>

<ul class="h-menu">
    <li><a href="">For mens</a>
        <ul>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forMenWatch"/>
                    <input type="submit" id="forMenWatch"/>
                </form>
                <a><label for="forMenWatch">Watch</label></a>
            </li>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forMenPerfume"/>
                    <input type="submit" id="forMenPerfume"/>
                </form>
                <a><label for="forMenPerfume">Perfume</label></a>
            </li>
        </ul>
    </li>
    <li><a href="">For womens</a>
        <ul>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forWomenWatch"/>
                    <input type="submit" id="forWomenWatch"/>
                </form>
                <a><label for="forWomenWatch">Watch</label></a>
            </li>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forWomenPerfume"/>
                    <input type="submit" id="forWomenPerfume"/>
                </form>
                <a><label for="forWomenPerfume">Perfume</label></a>
            </li>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forWomenCosmetic"/>
                    <input type="submit" id="forWomenCosmetic"/>
                </form>
                <a><label for="forWomenCosmetic">Cosmetic</label></a>
            </li>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forWomenFlower"/>
                    <input type="submit" id="forWomenFlower"/>
                </form>
                <a><label for="forWomenFlower">Flower</label></a>
            </li>
        </ul>
    </li>
    <li><a href="">For childrens</a>
        <ul>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forChildrenTractor"/>
                    <input type="submit" id="forChildrenTractor"/>
                </form>
                <a><label for="forChildrenTractor">Tractor</label></a>
            </li>
            <li>
                <form method="post" action="/homeUser">
                    <input type="hidden" name="typeProduct" value="forChildrenCar"/>
                    <input type="submit" id="forChildrenCar"/>
                </form>
                <a><label for="forChildrenCar">Car</label></a>
            </li>
        </ul>
    </li>
</ul>
<br>

<% if (request.getSession().getAttribute("orderSuccesfuly") != null) {%>
<span style="color: blue">
            <%=request.getSession().getAttribute("orderSuccesfuly")%>
            <%request.getSession().removeAttribute("orderSuccesfuly");%>
            <br>
        </span>
<%}%>

<div class="main-content-wrapper d-flex clearfix">

    <!-- Mobile Nav (max width 767px)-->
    <div class="mobile-nav">
        <!-- Navbar Brand -->
        <div class="amado-navbar-brand">
            <a href="index.html"><img src="img/core-img/logo.png" alt=""></a>
        </div>
        <!-- Navbar Toggler -->
        <div class="amado-navbar-toggler">
            <span></span><span></span><span></span>
        </div>
    </div>

    <!-- Header Area Start -->
    <header class="header-area clearfix">
        <!-- Close Icon -->
        <div class="nav-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <!-- Logo -->
        <div class="logo">
            <a href="index.html"><img src="img/core-img/logo.png" alt=""></a>
        </div>
        <!-- Amado Nav -->
        <nav class="amado-nav">
            <ul>
                <li class="active"><a href="/homeUser">Home</a></li>
                <li><a href="/cart">Cart</a></li>
            </ul>
        </nav>

    </header>
    <!-- Header Area End -->


    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8">
                    <div class="cart-title mt-50">
                        <h1>Products</h1>
                    </div>

                    <div class="cart-table clearfix">
                        <table class="table table-responsive">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%
                                List<Product> products = (ArrayList<Product>)request.getSession().getAttribute("products");
                                 Iterator<Product> iterator = products.iterator();
                                 while (iterator.hasNext()) {
                                     Product product = iterator.next();
                            %>


                            <tr>
                                <td class="cart_product_img">
                                    <a href="#"><img src="<%=product.getImagePath()%>" alt="Product"></a>
                                </td>
                                <td class="cart_product_desc">
                                    <h4><%=product.getName()%></h4>
                                    <h5>(<%=product.getAboutProduct()%>)</h5>
                                </td>
                                <td class="price">
                                    <span><%=product.getPrice()%>դր</span>
                                </td>
                                <td class="qty">
                                    <div class="qty-btn d-flex">
                                        <!--<p>Qty</p>
                                        <div class="quantity">
                                            <span class="qty-minus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                            <input type="number" class="qty-text" id="qty" step="1" min="1" max="300" name="quantity" value="1">
                                            <span class="qty-plus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i class="fa fa-plus" aria-hidden="true"></i></span>
                                        </div>-->
                                       <!-- <form method="post" action="/cart">
                                            <input type="hidden" id="productId" name="productId" value="<%//product.getId()%>"/>
                                            <input type="hidden" id="isFromCart" name="isFromCart" value="fromCart"/>
                                            <div class="quantity-block">
                                                <input class="quantity-num" type="number" name="countProduct" value="<%//count%>"/>
                                                <input type="submit" value="Add"/>
                                            </div>
                                        </form>-->
                                        <form method="post" action="/cart">
                                            <input type="hidden" id="<%=product.getId()%>" name="productId" value="<%=product.getId()%>"/>
                                            <div class="quantity-block">
                                                <input class="quantity-num" type="number" size="1" name="countProduct" min="1" step="1" value="1"/>
                                                <input type="submit" value="Add"/>
                                            </div>
                                        </form>
                                    </div>
                                </td>
                            </tr>

                            <%}%>

                            </tbody>
                        </table>
                    </div>
                </div>
               <!-- <div class="col-12 col-lg-4">
                    <div class="cart-summary">
                        <h5>Cart Total</h5>
                        <ul class="summary-table">
                            <li><span>total:</span> <span>դր</span></li>
                            <li><span>delivery:</span> <span>Free</span></li>

                        </ul>
                        <div class="cart-btn mt-100">
                            <a href="/order" class="btn amado-btn w-100">To Order</a>
                        </div>
                    </div>
                </div>-->
            </div>
        </div>
    </div>
</div>

</body>

</html>