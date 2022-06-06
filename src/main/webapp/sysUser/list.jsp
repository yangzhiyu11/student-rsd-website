<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>系统用户表</title>
        <script>
            function doDelete(id) {
                if (confirm("您确定删除么？")){
                    window.location.href = "/sysUser/doDelete?id="+id;
                }
            }
            function toAddPage() {
                window.location.href = "/sysUser/toAddPage";
            }
        </script>
    </head>

    <body>
        <h2>系统用户表</h2>
        <form action="/sysUser/list" method="post">
            <input type="search" name="searchLoginName" title="请输入账号" placeholder="账号" value="${searchSysUser.loginName}"/>
            <input type="search" name="searchRealName" title="请输入姓名" placeholder="姓名" value="${searchSysUser.realName}"/>
            <input type="search" name="searchTel" title="请输入号码" placeholder="号码" value="${searchSysUser.tel}"/>
            <select name="searchRoleId">
                <option value="">选择所有角色</option>
                <jstl:forEach items="${sysRoleList}" var="sysRole">
                    <option value="${sysRole.id}" ${sysRole.id==searchSysUser.roleId?"selected":""}>${sysRole.name}</option>
                </jstl:forEach>
            </select>
            <select name="searchSex">
                <option value="">选择所有性别</option>
                <option value="男" ${searchSysUser.sex =='男'?"selected":""}>男</option>
                <option value="女" ${searchSysUser.sex =='女'?"selected":""}>女</option>
                <option value="1" ${searchSysUser.sex =='1'?"selected":""}>1</option>
                <option value="0" ${searchSysUser.sex =='0'?"selected":""}>0</option>
            </select>
            开始时间:<input type="date" name="searchStartTime" value="${searchSysUser.searchStartTime}"/>
            结束时间:<input type="date" name="searchEndTime" value="${searchSysUser.searchEndTime}"/>
            <input type="submit" value="查询">
        </form>
        <a href="javascript:toAddPage()">增加</a>
        <table border="1" width="100%">

            <tr>
                <th>主键</th>
                <th>账号</th>
<%--                <th>密码</th>--%>
                <th>姓名</th>
                <th>角色</th>
                <th>性别</th>
                <th>电话</th>
                <th>时间</th>
                <th>操作</th>
            </tr>

            <jstl:forEach items="${sysUserList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.loginName}</td>
<%--                    <td>${user.password}</td>--%>
                    <td>${user.realName}</td>
                    <td>${user.roleName}</td>
                    <td>${user.sex}</td>
                    <td>${user.tel}</td>
<%--                    <td>${user.createTime}</td>--%>
                    <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:ss:mm"/></td>
                    <td>
                        <a href="/sysUser/toUpdatePage?id=${user.id}">修改</a>
                        <a href="javascript:doDelete(${user.id})">删除</a>
                        <a href="/sysUser/reset.jsp?id=${user.id}">密码重置</a>
                    </td>
                </tr>
            </jstl:forEach>
        </table>
    </body>
</html>