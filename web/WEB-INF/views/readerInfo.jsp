<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
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

<h3>${reader.name}</h3>
<div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message bundle="${lang}" key="registration.name"/></th>
            <th><fmt:message bundle="${lang}" key="main.authors"/></th>
            <th><fmt:message bundle="${lang}" key="llendDate"/></th>
            <th><fmt:message bundle="${lang}" key="returnDate"/></th>
            <c:if test="${user.admin}">
                <th><fmt:message key="return" bundle="${lang}"/></th>
            </c:if>
            <th><fmt:message key="daysLeft" bundle="${lang}"/></th>
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
                <td>
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${rb.lendDate}"/>
                </td>
                <td>
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${rb.returnDate}"/>
                </td>
                <c:if test="${user.admin}">
                    <td>
                        <form action="/Controller" method="POST">
                            <input type="hidden" name="command" value="returnBook"/>
                            <input type="hidden" name="id" value="${rb.id}"/>
                            <input type="hidden" name="page" value="readerInfo">

                            <button type="submit" class="btn btn-success"><fmt:message bundle="${lang}" key="return"/></button>
                        </form>
                    </td>
                </c:if>
                <td>
                    <ct:days-left returnDate="${rb.returnDate}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>
