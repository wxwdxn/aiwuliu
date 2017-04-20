<%--
  Created by IntelliJ IDEA.
  User: CYH
  Date: 2015/12/1
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>未找到资源</title>
    <style type="text/css">
        a{
            font-family: 微软雅黑;
            font-size: 16px;
            color: #1e1e1e;
            text-decoration: none;
        }
        a:hover{
            font-family: 微软雅黑;
            font-size: 16px;
            color: #1e1e1e;
            text-decoration: none;
        }
        a:visited{
            font-family: 微软雅黑;
            font-size: 16px;
            color: #1e1e1e;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div style=' margin-top: 10%;overflow:hidden;text-align:center'><img src="<%=request.getContextPath()%>/static/common/images/error/404_06.png"></div>
    <div style="text-align: center;margin-top: 76px;">
        <span style="margin-right: 96px;"><img src="<%=request.getContextPath()%>/static/common/images/error/back.png"><a href="#">返回上一级</a></span>
        <span ><img src="<%=request.getContextPath()%>/static/common/images/error/home.png"><a href="<%=request.getContextPath()%>">返回首页</a></span>
    </div>
</body>
</html>
