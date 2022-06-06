<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>系统功能表</title>
    </head>
    <body>
        <h2>系统功能表</h2>
        <a href="/sysFunction/add.jsp">增加</a>
        <table border="1" width="100%">
            <tr>
                <th>主键</th>
                <th>姓名</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${sysFunctionList}" var="sysFunction">
                <tr>
                    <td>${sysFunction.id}</td>
                    <td>${sysFunction.name}</td>

                    <td>
                        <a href="/sysFunction/toUpdatePage?id=${sysFunction.id}">修改</a>
                        <a href="/sysFunction/doDelete?id=${sysFunction.id}">删除</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>