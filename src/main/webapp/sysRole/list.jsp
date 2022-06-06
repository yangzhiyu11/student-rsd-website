<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>系统角色表</title>
    </head>
    <body>
        <h2>系统角色表</h2>
        <a href="/sysRole/toAddPage">增加</a>
        <table border="1" width="100%">
            <tr>
                <th>主键</th>
                <th>姓名</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            <jstl:forEach items="${sysRoleList}" var="sysRole">
                <tr>
                    <td>${sysRole.id}</td>
                    <td>${sysRole.name}</td>
<%--                    <td>${sysRole.createTime}</td>--%>
                    <td><fmt:formatDate value="${sysRole.createTime}" pattern="YYYY-MM-dd HH:ss:mm"/></td>

                    <td>
                        <a href="/sysRole/toUpdatePage?id=${sysRole.id}">修改</a>
                        <a href="/sysRole/doDelete?id=${sysRole.id}">删除</a>
                    </td>
                </tr>

            </jstl:forEach>
        </table>
    </body>
</html>