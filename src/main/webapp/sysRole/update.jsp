<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>修改列表</title>
    </head>
    <body>
        <h2>修改列表</h2>
        <form method="post" action="/sysRole/doUpdate">
            <input type="hidden" name="id" value="${sysRole.id}"/>
            <table border="1">
                <tr>
                    <td>角色名称</td>
                    <td>
                        <input type="text" name="name" value="${sysRole.name}">
                    </td>
                </tr>
                <tr>
                    <td>功能选择</td>
                    <td>
                        <c:forEach items="${sysFunctionList}" var="sysFunction">
                            <c:set var="isChecked" value="false"/>
                            <c:forEach items="${sysRole.functionId}" var="functionId">
                                <c:if test="${functionId == sysFunction.id}">
                                    <c:set var="isChecked" value="true"/>
                                </c:if>
                            </c:forEach>
                            <input type="checkbox" name="sysFunctionIds" value="${sysFunction.id}"${isChecked?"checked":""}/>${sysFunction.name}
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