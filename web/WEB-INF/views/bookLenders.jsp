<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 29.05.2017
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:import url="head.jsp"/>
<c:import url="header.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>

${book.name}
<div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="registration.name" bundle="${lang}"/></th>
            <th><fmt:message key="login.email" bundle="${lang}"/></th>
            <th><fmt:message key="llendDate" bundle="${lang}"/></th>
            <th><fmt:message key="returnDate" bundle="${lang}"/></th>
            <th><fmt:message key="return" bundle="${lang}"/></th>
            <th><fmt:message key="daysLeft" bundle="${lang}"/></th>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="rb" items="${readerBooks}" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td> ${rb.reader.name}</td>
                <td>  <a href="/Controller?command=readerInfo&id=${rb.reader.id}"> ${rb.reader.email} </a></td>
                <td>
                    <fmt:formatDate pattern = "yyyy-MM-dd" value = "${rb.lendDate}"/>
                </td>
                <td>
                    <fmt:formatDate pattern = "yyyy-MM-dd" value = "${rb.returnDate}"/>
                </td>
                <td><form action="/Controller"  method="POST">
                    <input type="hidden" name="command" value ="returnBook"/>
                    <input type="hidden" name="id" value ="${rb.id}"/>
                    <input type="hidden" name="page" value="bookLenders">
                    <button type="submit" class="btn btn-success"><fmt:message key="return" bundle="${lang}"/></button></form>
                </td>
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
