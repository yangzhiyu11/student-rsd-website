<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h2>文件上传修改</h2>
        <form action="/sysFiles/doUpdate" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${sysFiles.id}">
            <input type="file" name="myFile"/>
            <input type="submit"/>
        </form>
    </body>
</html>
