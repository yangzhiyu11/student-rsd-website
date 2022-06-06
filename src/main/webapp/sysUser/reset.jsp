<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>修改密码</title>
    </head>
    <body>
        <h2>修改密码</h2>
        <form method="post" action="/reset">
            <table border="1">
                <input type="hidden" name="id" value="${param.id}"/>
                <tr>
                    <td>原密码</td>
                    <td>
                        <input type="text" name="oldPassword" />
                    </td>
                </tr>
                <tr>
                    <td>新密码</td>
                    <td>
                        <input type="text" name="newPassword" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit"/>
                        <input type="reset">
                    </td>
                </tr>
            </table>
            <div style="color: red">${ww}</div>
        </form>
    </body>
</html>