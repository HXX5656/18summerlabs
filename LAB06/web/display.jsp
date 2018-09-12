<%--
  Created by IntelliJ IDEA.
  User: MS.hu
  Date: 2018/7/18
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <title>显示购物车内容</title>
</head>
<body>
<h3>你选择的结果是：</h3>
<form action="dataDelete.jsp" method="post" name="table">
    <table>
    <%
        HashMap<String,Integer> goods=  (HashMap<String,Integer>)  session.getAttribute("list");
        if (goods!= null)
            for (String key:goods.keySet())
            {out.print("<tr><td>商品名称："+key + "</td><td>商品数量： "+goods.get(key)+"</td><td><button name="+key+" type=\"submit\">删除</button></td></tr>");}
    %>
    </table>
</form>
</body>
</html>
