<%--
  Created by IntelliJ IDEA.
  User: MS.hu
  Date: 2018/7/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <title>data</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String goods[] = request.getParameterValues("data");

    HashMap<String,Integer> now=  (HashMap<String,Integer>)  session.getAttribute("list");

    HashMap<String,Integer> set= new HashMap<String,Integer>();
    if(now!=null)
        set=now;
    if(goods!=null)
    {
        for (int i = 0; i < goods.length; i++) {
            String good=goods[i];
            if(set.containsKey(good)){
                int num=set.get(good);
                set.put(good,(num+1));
            }
            else{
                set.put(good,1);
            }
        }
    }
    session.setAttribute("list",set);
%>
<script language="JavaScript">
    window.location = document.referrer;
</script>
</body>

</html>
