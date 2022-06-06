<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript">
            function randomName1() {
                document.location.href = "/tool/random1";
            }

            function randomName2() {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function (){
                    if (xhr.readyState == 4 && xhr.status == 200){
                        var data = xhr.responseText;
                        document.getElementById("showName").innerText = data;
                    }
                }
                xhr.open("post","/tool/random2",true);
                xhr.send();
            }

            function randomName3() {
                var random = Math.random();
                var value = Math.floor(random * 6);
                var students = ["樊书琪","杨治宇","毛晓睿","许文韬","高超","杨争鸣"];
                document.getElementById("showName2").innerText = students[value];
            }
        </script>
    </head>
    <body>
        <input type="button" value="点名servlet" onclick="randomName1();"/>&nbsp;&nbsp;${studentName}
        <br/>
        <br/>
        <input type="button" value="点名ajax" onclick="randomName2();"/>&nbsp;&nbsp;<span id="showName"></span>
        <br/>
        <br/>
        <input type="button" value="点名js" onclick="randomName3();"/>&nbsp;&nbsp;<span id="showName2"></span>
    </body>
</html>
