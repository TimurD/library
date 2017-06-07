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
${reader.name}

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>
<div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th>Reader Email</th>
            <th>Book Name</th>
            <th>Authors</th>
            <th>Get book to reader</th>
            <th>Decline</th>


        </tr>
        </thead>
        <tbody>

        <c:forEach items="${readerBooks}" var="o" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td><a href="/Controller?command=readerInfo&id=${o.reader.id}"> ${o.reader.email} </a></td>
                <td> ${o.book.name}</td>
                <td>
                    <ct:print-authors authors="${o.book.authors}"/>
                </td>

                <td>
                    <form action="/Controller" method="POST">
                        <input type="hidden" name="command" value="getBook"/>
                        <input type="hidden" name="id" value="${o.id}"/>
                        <div class="col-xs-3">
                            <input class="form-control" type="number" name="days" required="required" placeholder="days"
                                   onchange="handleChange(this);">
                        </div>
                        <button type="submit" class="btn btn-success">GET</button>
                    </form>
                </td>
                <td>
                    <form action="/Controller" method="POST">
                        <input type="hidden" name="command" value="returnBook"/>
                        <input type="hidden" name="id" value="${o.id}"/>
                        <input type="hidden" name="page" value="orders">
                        <button type="submit" class="btn btn-danger">Decline</button>
                    </form>
                </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function handleChange(input) {
        if (input.value < 7) input.value = 7;
        if (input.value > 1000) input.value = 500;
    }

    $("#regions").change(function () {
        if ($("option:selected:last", this).val() == 99) {
            $('#regions option').prop('selected', true);
        } else {
            $('#regions option').prop('selected', false);
        }
    });


</script>
</body>

</html>
