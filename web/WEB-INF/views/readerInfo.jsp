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


<h3>${reader.name}</h3>
<div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Authors</th>
            <th>Lend Date</th>
            <th>Return Date</th>
            <c:if test="${isAdmin}">
                <th></th>
            </c:if>
            <th></th>

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
                <c:if test="${isAdmin}">
                    <td>
                        <form action="/Controller" method="POST">
                            <input type="hidden" name="command" value="returnBook"/>
                            <input type="hidden" name="id" value="${rb.id}"/>
                            <input type="hidden" name="page" value="readerInfo">

                            <button type="submit" class="btn btn-success">Return</button>
                        </form>
                    </td>
                </c:if>
                <td>
                    <ct:return-remind returnDate="${rb.returnDate}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>
