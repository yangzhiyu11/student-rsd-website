<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript">
            function toAddPage() {
                location.href = "/sysFiles/add.jsp"
            }

            function toUpdatePage(id) {
                location.href = "/sysFiles/toUpdatePage?id=" + id;
            }

            function doDelete(id) {
                if (confirm("您确定删除么？")){
                    location.href = "/sysFiles/doDelete?id=" + id;
                }
            }

            function downLoad(id) {
                location.href = "/sysFiles/downLoad?id=" + id;
            }
        </script>
    </head>
    <body>
        <h2>文件上传管理</h2>
        <input type="button" value="新增" onclick="toAddPage()"/>
        <table border="1" width="100%">
            <tr>
                <th>主键</th>
                <th>文件名称</th>
                <th>路径</th>
                <th>文件大小</th>
                <th>上传时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list}" var="sysFiles" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${sysFiles.name}</td>
                    <td>${sysFiles.path}</td>
                    <td>${sysFiles.fileSize}K</td>
                    <td>${sysFiles.createTime}</td>
                    <td>
                        <input type="button" value="下载" onclick="downLoad(${sysFiles.id});"/>
                        <input type="button" value="修改" onclick="toUpdatePage(${sysFiles.id});"/>
                        <input type="button" value="删除" onclick="doDelete(${sysFiles.id});"/>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
