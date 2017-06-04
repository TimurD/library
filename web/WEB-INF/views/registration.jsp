<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 29.05.2017
  Time: 16:23
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
<body data-gr-c-s-loaded="true">
<div class="container">
<form class="form-signin" name="registrationForm" method="POST" action="/Controller">
    <input class="form-control" type="hidden" name="command" value ="registration"/>
    Name:<br/>
    <input class="form-control" type="text" name="name" placeholder="Name" required="required" value="${name}"> ${nameError} <br/>
    Email:<br/>
    <input class="form-control" type="text" name="email" placeholder="Email" required="required" value="${email}"> ${emailError} <br/>
    Password:<br/>
    <input class="form-control" type="password" name ="password" placeholder="Password" required="required"> ${passwordError}
    <br/>
    <button class="btn btn-lg btn-primary btn-block"  type="submit" >Registration</button>

    ${error}
</form>
</div>
</body>
</html>
