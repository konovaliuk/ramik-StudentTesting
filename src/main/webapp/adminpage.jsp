<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<head><title>Title</title></head>
<body>
<h1>Hello Admin!</h1><br />
<h1>All users</h1><br />

<c:forEach var ="user" items="${requestScope.users}">

    <ul>
       Пользователь: <c:out value="${user.firstName}"/>
         <c:out value="${user.lastName}"/>
        Email: <c:out value="${user.email}"/>


    </ul>
</c:forEach>

<h1>Chapters</h1>

<c:forEach var ="chapter" items="${requestScope.chapter}">
        Chapter <c:out value="${chapter.name}"/>
</c:forEach>



<h1>All results</h1>

</body>
