<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>文件上传</title>
    </head>
    <body>
        <form action="/servlet/upload" method="post" enctype="multipart/form-data">
            选择文件
            <input type="file" name="headPic"/>
            <input type="submit"/>
        </form>
    </body>
</html>
