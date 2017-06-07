<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 29.05.2017
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>


<body data-gr-c-s-loaded="true">
<div class="container">
    <form class="form-signin" name="registrationForm" method="POST" action="/Controller">
        <input class="form-control" type="hidden" name="command" value="registration"/>
        <fmt:message key="registration.name" bundle="${lang}"/>:<br/>
        <input class="form-control" type="text" name="name"
               placeholder="<fmt:message key="registration.name" bundle="${lang}"/>" required="required"
               value="${name}">
        <p style="color:red">${nameError}</p>
        <fmt:message key="login.email" bundle="${lang}"/> :<br/>
        <input class="form-control" type="text" name="email"
               placeholder="<fmt:message key="login.email" bundle="${lang}"/>" required="required" value="${email}">
        <p style="color:red">${emailError}</p>
        <fmt:message key="login.password" bundle="${lang}"/>:<br/>
        <input class="form-control" type="password" name="password"
               placeholder="<fmt:message key="login.password" bundle="${lang}"/>" required="required">
        <p style="color:red">${passwordError}</p>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.registration"
                                                                                    bundle="${lang}"/></button>

    </form>

    <a href="/Controller?command=switchLang&lang=ru_RU&&page=registration">
        <fmt:message key="header.ru" bundle="${lang}"/>
    </a>
    <a href="/Controller?command=switchLang&lang=en_US&&page=registration">
        <fmt:message key="header.en" bundle="${lang}"/>
    </a>

</div>
</body>
</html>
