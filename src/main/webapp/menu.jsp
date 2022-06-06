<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body style="background-color: green">

    <c:forEach items="${functionList}" var="function">
        <ul><a href="${function.url}" target="view">${function.name}</a></ul>
    </c:forEach>

</body>