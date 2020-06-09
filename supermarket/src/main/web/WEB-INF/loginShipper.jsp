<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Shipper</title>
    <link rel="stylesheet" type="text/css" href="resources/login.css">
</head>
<body>
    <div class="login-page">
        <div class="form">
            <form action="/loginShipper" method="post" class="login-form">
                <% if (request.getAttribute("errorEmailPasswordShipper") != null) {%>
                <span style="color: red">
                             <%=request.getAttribute("errorEmailPasswordShipper")%>
                         </span>
                <%}%>
                <% if (request.getAttribute("errorEmailShipperLogin") != null) {%>
                <span style="color: red">
                             <%=request.getAttribute("errorEmailShipperLogin")%>
                         </span>
                <%}%>
                <input name="emailShipper" type="text" placeholder="email"/>
                <% if (request.getAttribute("errorPasswordShipperLogin") != null) {%>
                <span style="color: red">
                    <%=request.getAttribute("errorPasswordShipperLogin")%>
                    </span>
                <%}%>
                <input name="passwordShipper" type="password" placeholder="password"/>
                <button>login</button>
                <!--<p class="message">Not registered? <a href="#">Cre</a></p>-->
            </form>
        </div>
    </div>
<!--</form>-->
</body>
</html>
