<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
<c:import url="head.jsp"/>

${reader.name}
<div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Authors</th>
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
                <td><form action="/Controller"  method="POST">
                    <input type="hidden" name="command" value ="returnBook"/>
                    <input type="hidden" name="id" value ="${rb.id}"/>
                    <input type="hidden" name="page" value="readingRoom">
                    <button type="submit" class="btn btn-success">Return</button></form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>
