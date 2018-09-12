<%--
  Created by IntelliJ IDEA.
  User: MS.hu
  Date: 2018/7/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>花类购物车</title>
</head>
<body>
<h4>各种花大甩卖，一律十块：</h4>
<form name="flower" id="flower" action="data.jsp" method="post">
    <input type="checkbox" name="data" value="红花">红花<br>
    <input type="checkbox" name="data" value="黄花">黄花<br>
    <input type="checkbox" name="data" value="粉花">粉花<br><br>
    <input type="submit" value="提交">
    <input type="reset" value="全部重写"><br><br>
    <a href="cart1.jsp">买点别的</a>&nbsp;&nbsp;<a href="display.jsp">查看购物车</a>
</form>
</body>
</html>
