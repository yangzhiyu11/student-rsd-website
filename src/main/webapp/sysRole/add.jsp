<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>新增列表</title>
    </head>
    <body>
        <form method="post" action="/sysRole/doAdd">
            <table border="1">
                <tr>
                    <td>角色</td>
                    <td>
                        <input type="text" name="name">
                    </td>
                </tr>
                <tr>
                    <td>功能</td>
                    <td>
                        <c:forEach items="${sysFunctionList}" var="sysFunction">
                            <input type="checkbox" name="functionId" value="${sysFunction.id}"/>${sysFunction.name}
                        </c:forEach>

                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>