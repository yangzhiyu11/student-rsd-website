<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>新增账户</title>
        <script>
            function checkForm() {
                var loginName = document.getElementById("loginName");
                // alert(loginName.value);
                var realName = document.getElementById("realName");
                var tel = document.getElementById("tel");
                var roleIds = document.getElementsByName("roleId");
                var flag = false;
                for (var i = 0; i < roleIds.length; i ++) {
                    if (roleIds[i].checked) {
                        flag = true;
                    }
                }
                // alert(flag);
                var sex = document.getElementById("sex");
                if (loginName.value == "") {
                    alert("账号不能为空！")
                    return;
                }
                if (realName.value == "") {
                    alert("姓名不能为空！")
                    return;
                }
                if (flag == false) {
                    alert("请选择角色!")
                    return;
                }
                if (sex.value == "") {
                    alert("请选择性别!")
                    return;
                }
                if (tel.value == "") {
                    alert("电话不能为空！");
                    return;
                }


                var sysUserForm = document.getElementById("sysUserForm")
                    sysUserForm.submit();
            }
        </script>
    </head>
    <body>
        <form id="sysUserForm" method="post" action="/sysUser/doAdd" enctype="multipart/form-data">
            <table border="1">
                <tr>
                    <td>账号</td>
                    <td>
                        <input type="text" name="loginName" id="loginName"/>
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <td>密码</td>--%>
<%--                    <td>--%>
<%--                        <input type="password" name="password"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="realName" id="realName"/>
                    </td>
                </tr>
                <tr>
                    <td>头像</td>
                    <td>
                        <input type="file" name="headPicPath" id="headPicPath"/>
                    </td>
                </tr>
                <tr>
                    <td>角色</td>
                    <td>
                        <c:forEach items="${sysRoleList}" var="sysRole">
                            <input type="radio" value="${sysRole.id}" name="roleId"/>${sysRole.name}
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>
                        <select name="sex" id="sex">
                            <option value="">请选择</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                            <option value="0">0</option>
                            <option value="1">1</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td colspan="2">
                        <input type="text" name="tel" id="tel">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="button" onclick="checkForm();" value="保存"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>