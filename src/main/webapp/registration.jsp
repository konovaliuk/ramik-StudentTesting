<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<head>
    <c:set var="currentPage" value="login" scope="request"/>
    <title>
        Register new user
    </title>
</head>
<body>
<h3>Register new user</h3><hr/>
<form method="post">
    Enter e-mail
    <input type="text" name ="email"/>
    <br/>
    Enter password
    <input type="password" name ="password"/>
    <br/>
    Enter first name
    <input type="text" name ="name"/>
    <br/>
    Enter last name
    <input type="text" name ="surname"/>
    <br/>
    <input type="submit" name="command" value="RegistrationNewUser" />
</form>

