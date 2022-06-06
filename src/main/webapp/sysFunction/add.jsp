<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>新增列表</title>
    </head>
    <body>
        <form method="post" action="/sysFunction/doAdd">
            <table border="1">
                <tr>
                    <td>系统功能</td>
                    <td>
                        <input type="text" name="name">
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