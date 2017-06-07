<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 05.06.2017
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>

<div class="container">
    <ul class="nav navbar-nav navbar-right">
        <li>
            <a href="/Controller?command=logout">
                <fmt:message key="header.logout" bundle="${lang}"/>
            </a>
        </li>
    </ul>

    <p style="color:red">${message}</p>

    <div class="col-md-12">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="login.email" bundle="${lang}"/></th>
                <th><fmt:message key="registration.name" bundle="${lang}"/></th>
                <th><fmt:message key="host.setRole" bundle="${lang}"/></th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="r" items="${users}" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td><a href="/Controller?command=readerInfo&id=${r.id}"> ${r.email} </a></td>
                <td>
                        ${r.name}
                </td>
                <td>
                    <form name="setRole" method="POST" action="/Controller">
                        <input type="hidden" name="command" value="setRole"/>
                        <input type="hidden" name="userId" value="${r.id}"/>
                        <input type="hidden" name="admin" value="${r.admin}">
                        <c:choose>
                            <c:when test="${r.admin}">
                                <button type="submit" class="btn btn-danger">Unmake admin</button>

                            </c:when>

                            <c:otherwise>
                                <button type="submit" class="btn btn-primary">Make admin</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
            </c:forEach>
    </div>
</div>

</body>
</html>
