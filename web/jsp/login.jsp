<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 19.05.2017
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="../WEB-INF/views/head.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>


<div class="container">
    <form class="form-signin" name="loginForm" method="POST" action="Controller">
        <input type="hidden" name="command" value="login"/>
        <fmt:message key="login.email" bundle="${lang}"/> :<br/>
        <input class="form-control" required="required" placeholder="<fmt:message key="login.email" bundle="${lang}"/>"
               type="text" name="login" value="${email}"> <br/>
        <fmt:message key="login.password" bundle="${lang}"/>:<br/>
        <input class="form-control" required="required"
               placeholder="<fmt:message key="login.password" bundle="${lang}"/>" type="password" name="password"
               value="">

        <br/>
        <a href="/Controller?command=registrationPage"><fmt:message key="login.registration" bundle="${lang}"/></a>
        <button class="btn btn-lg btn-primary btn-block" type="submit" value="Enter"><fmt:message key="login.login"
                                                                                                  bundle="${lang}"/></button>
    </form>
    <a href="/Controller?command=switchLang&lang=ru_RU&&page=login">
        <fmt:message key="header.ru" bundle="${lang}"/>
    </a>
    <a href="/Controller?command=switchLang&lang=en_US&&page=login">
        <fmt:message key="header.en" bundle="${lang}"/>
    </a>

    <p style="color:red">${error}</p>

</div>
</body>
</html>

