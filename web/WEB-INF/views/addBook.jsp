<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 29.05.2017
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
<c:import url="head.jsp"/>
<div class="container">

    <form name="registrationForm" method="POST" action="Controller">
        <input type="hidden" name="command" value="addBook"/>
        <input type="hidden" name="newBook" value="yes"/>
        <div class="form-group">

            Name:
            <input class="form-control" type="text" name="name" placeholder="Book Name" required="required"><br/>
        </div>
        <div class="form-group">

            Amount:
            <input class="form-control" type="number" name="amount" required="required" placeholder="Book Amount" onchange="handleChange(this);"> <br/>
        </div>
        <div class="form-group">
        <select class="selectpicker" name="selectedGenre">
            <c:forEach var="g" items="${genres}">
                <option value="${g.id}">${g.name}</option>
            </c:forEach>
        </select>
        </div>

        <div class="form-group">
        <select name="selectedAuthors" class="selectpicker" required="required" multiple>
            <c:forEach var="a" items="${authors}">
                <option value="${a.id}">${a.name}</option>
            </c:forEach>
        </select>
        </div>


        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    ${error}

    <script>
        function handleChange(input) {
            if (input.value < 0) input.value = 0;
            if (input.value > 100) input.value = 100;
        }

        $("#regions").change(function () {
            if ($("option:selected:last", this).val() == 99) {
                $('#regions option').prop('selected', true);
            } else {
                $('#regions option').prop('selected', false);
            }
        });
    </script>

</div>
</body>
</html>
