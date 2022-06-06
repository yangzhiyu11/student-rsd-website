<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>修改列表</title>
    </head>
    <body>
        <h2>修改列表</h2>
        <form method="post" action="/sysFunction/doUpdate">
            <input type="hidden" name="id" value="${sysFunction.id}"/>
            <table border="1">
                <tr>
                    <td>角色权限</td>
                    <td>
                        <input type="text" name="name" value="${sysFunction.name}">
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