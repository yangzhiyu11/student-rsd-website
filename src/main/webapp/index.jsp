<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>我的项目</title>
    </head>
    <frameset rows="80px,*,50px" frameborder="no" framespacing="0" border="0">
        <frame src="top.jsp" scrolling="no"/>

        <frameset cols="150px,*">//左边是150
            <frame src="/servlet/menu">
            <frame src="main.jsp"name="view">
        </frameset>

        <frame src="bottom.jsp" />
    </frameset>
    <body>

    </body>
</html>