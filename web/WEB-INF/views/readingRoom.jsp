<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 30.05.2017
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>
<c:import url="header.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>


<div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message bundle="${lang}" key="registration.name"/></th>
            <th><fmt:message bundle="${lang}" key="main.authors"/></th>
            <th><fmt:message bundle="${lang}" key="return"/></th>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="rb" items="${readerBooks}" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td> ${rb.book.name}</td>
                <td>
                    <ct:print-authors authors="${rb.book.authors}"/>
                </td>
                <td><form action="/Controller"  method="POST">
                    <input type="hidden" name="command" value ="returnBook"/>
                    <input type="hidden" name="id" value ="${rb.id}"/>
                    <input type="hidden" name="page" value="readingRoom">
                    <button type="submit" class="btn btn-success"><fmt:message bundle="${lang}" key="return"/></button></form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>
