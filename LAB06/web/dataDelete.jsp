<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: MS.hu
  Date: 2018/7/18
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    HashMap<String,Integer> now=  (HashMap<String,Integer>)  session.getAttribute("list");
    ArrayList<String> targets=new ArrayList<>();
    for(String key:now.keySet()){
        if(request.getParameter(key)!=null){
            targets.add(key);
        }
    }
    for(String tept:targets){
        now.remove(tept);
    }
    session.setAttribute("list",now);
%>
<script language="JavaScript">
    window.location = document.referrer;
</script>
</body>
</html>
