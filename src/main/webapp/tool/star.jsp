<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>打印星星</title>
    </head>
    <body>
        <h2>打印星星</h2>
        <form action="/tool/star" method="post">
            <div>
                行数<input type="number" name="showNumber" value="${showNumber}"/>
                <input type="submit"/>
            </div>
        </form>
        ${stars}
        <br/>
        <c:forEach items="${starList}" var="star">
            ${star}<br/>
        </c:forEach>
        <br/>
        <c:forEach items="${ssList}" var="ss">
            ${ss}<br/>
        </c:forEach>
    </body>
</html>
