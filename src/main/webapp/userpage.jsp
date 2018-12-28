<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

    <html>
    <head><title>Title</title></head>
    <body>

    <h3> welcome
        <c:out value="${user.firstName}"/>
        <c:out value="${user.lastName}"/>
    </h3>

    <h1>Chapters</h1>

    <c:forEach var ="chapter" items="${requestScope.chapter}">
        Chapter <c:out value="${chapter.name}"/>
    </c:forEach>





    </body>
    </html>

