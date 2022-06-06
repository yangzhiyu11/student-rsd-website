<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>修改账户</title>
    </head>
    <body>
        <h2>修改账户</h2>
        <form method="post" action="/sysUser/doUpdate" enctype="multipart/form-data">
            <table border="1">
                <input type="hidden" name="id" value="${sysUser.id}"/>
                <tr>
                    <td>账号</td>
                    <td>
                        <input type="text" name="loginName" value="${sysUser.loginName}"/>
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <td>密码</td>--%>
<%--                    <td>--%>
<%--                        <input type="password" name="password" value="${sysUser.password}"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="realName" value="${sysUser.realName}"/>
                    </td>
                </tr>
                <tr>
                    <td>头像</td>
                    <td>
                        <img src="${sysUser.headPicPath}" width="50" height="80"/>
                        <input type="file" name="headPicPath"/>
                    </td>
                </tr>
                <tr>
                    <td>角色</td>
                    <td>
                        <c:forEach items="${sysRoleList}" var="sysRole">
                            <input type="radio" value="${sysRole.id}" name="roleId" ${sysUser.roleId==sysRole.id?"checked='checked'":""}}/>${sysRole.name}
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>
                        <input type="radio" value="男" name="sex"  ${sysUser.sex=="男"?"checked='checked'":""}/>男
                        <input type="radio" value="女" name="sex"  ${sysUser.sex=="女"?"checked='checked'":""}/>女
                    </td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="tel" value="${sysUser.tel}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>