<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>计算器</title>
    </head>
    <body>
        <form action="/servlet/calculator" method="post">
            <h2>计算器</h2>
            <table>
                <tr>
                    <td><input type="text" name="nub1" value="${calculator.nub1}"></td>
                    <td>
                        <select name="myselect">
                            <option value="+" ${calculator.myselect == '+'?"selected":""}>+</option>
                            <option value="-" ${calculator.myselect == '-'?"selected":""}>-</option>
                            <option value="*" ${calculator.myselect == '*'?"selected":""}>*</option>
                            <option value="/" ${calculator.myselect == '/'?"selected":""}>/</option>
                        </select>
                    </td>
                    <td><input type="text" name="nub2" value="${calculator.nub2}"></td>
                    <td><input type="submit" value="="/></td>
                    <td><input type="text" name="nub3" value="${calculator.nub3}"></td>
                </tr>
            </table>
        </form>
        <h2>历史记录</h2>
        <ul>
            <c:forEach items="${historyList}" var="history">
                <li>${history}</li>
            </c:forEach>
        </ul>
        <a href="/servlet/doDelete">清除历史数据</a>
    </body>
</html>