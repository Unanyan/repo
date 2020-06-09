<%@ page import="java.io.IOException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<html>
<head>
    <title>Title</title>
</head>
<body>-->
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="resources/addShipper.css">
</head>
<body>
<%!
    String getParamValue(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return value != null ? value : "";
    }
%>

<div class="login-page">
    <div class="form">
        <form action="/registerShipper" method="post" class="login-form">

            <label for="name"> Name </label>
            <br>
            <c:if test="${requestScope.errorNameShipper != null}">
            <span style="color: red">
                    <c:out value="${requestScope.errorNameShipper}"/>
                </span>
            </c:if>
            <br>
            <input id="name" name="name" type="text" value="<%=getParamValue(request, "name")%>"/>
            <br>
            <label for="surname"> Surname </label>
            <br>
            <c:if test="${requestScope.errorSurnameShipper != null}">
            <span style="color: red">
                    <c:out value="${requestScope.errorSurnameShipper}"/>
                </span>
            </c:if>
            <br>
            <input id="surname" name="surname" type="text" value="<%=getParamValue(request, "surname")%>"/>
            <br>
            <label for="email"> Email </label>
            <br>
            <c:if test="${requestScope.errorEmailShipper != null}">
            <span style="color: red">
                    <c:out value="${requestScope.errorEmailShipper}"/>
                </span>
            </c:if>
            <br>
            <input id="email" name="email" type="text" value="<%=getParamValue(request, "email")%>"/>
            <br>
            <label for="password"> Password </label>
            <br>
            <c:if test="${requestScope.errorPasswordShipper != null}">
            <span style="color: red">
                    <c:out value="${requestScope.errorPasswordShipper}"/>
                </span>
            </c:if>
            <br>
            <input id="password" name="password" type="password"/>
            <br>
            <label for="confirmPassword"> Confirm Password </label>
            <br>
            <c:if test="${requestScope.errorConfirmPasswordShipper != null}">
            <span style="color: red">
                    <c:out value="${requestScope.errorConfirmPasswordShipper}"/>
                </span>
            </c:if>
            <br>
            <input id="confirmPassword" name="confirmPassword" type="password"/>
            <br>
            <button>Register shipper</button>
           <!-- <a href="/login">Login</a>-->
        </form>
    </div>
    </div>
</body>
</html>
