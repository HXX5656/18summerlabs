<%--
  Created by IntelliJ IDEA.
  User: MS.hu
  Date: 2018/7/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物锅类</title>
</head>
<body>

<h4>各种锅大甩卖，一律八块</h4>
<form action="data.jsp" method="post" name="pot" id="pot">
    <input type="checkbox" name="data" value="铁锅">铁锅<br>
    <input type="checkbox" name="data" value="铜球">铜锅<br>
    <input type="checkbox" name="data" value="黑球">黑锅<br><br>
    <input type="submit" value="提交">
    <input type="reset" value="全部重写"><br><br>
    <a href="cart2.jsp">买点别的</a>&nbsp;&nbsp;<a href="display.jsp">查看购物车</a>
</form>

</body>
</html>