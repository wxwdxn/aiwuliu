<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/14
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆超时</title>
</head>
<body>
<script language="JavaScript">
    alert("用户登陆信息已失效，请重新登录。");
    setTimeout(function () {
        window.top.location.href = "/iwuliu/login/memberHome";
    }, 2000);
</script>
</body>
</html>
