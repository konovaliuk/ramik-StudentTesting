<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>
    <title>Login Page</title>
  </head>
  <body>

  <h3>Выполните вход или зарегистрируйтесь</h3>
  <form action="MyServlet" method="post">
    <p> Login </p><input name="email" type="text"/>
    <p> Password </p><input name="password" type="password"/>
    <br/>
    <input type="submit" value="submit" name="command"/>
    <input type="submit" value="Registration" name="command"  />

  </form>
  </body>
</html>
