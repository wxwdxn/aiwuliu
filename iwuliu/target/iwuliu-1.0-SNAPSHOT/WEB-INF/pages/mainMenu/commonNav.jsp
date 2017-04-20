<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String username = request.getSession().getAttribute("username").toString();%>
<div class="container-fluid">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="javascript:;">
			<img src="<%=request.getContextPath()%>/static/common/images/logo.png"/>
		</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

		<ul class="nav navbar-nav navbar-right">
			<%--<li class="dropdown">--%>
				<%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">系统管理 <span class="caret"></span></a>--%>
				<%--<ul class="dropdown-menu" role="menu">--%>
					<%--<li><a href="#">权限管理</a></li>--%>
					<%--<li><a href="#">角色管理</a></li>--%>
				<%--</ul>--%>
			<%--</li>--%>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=username%><span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/iwuliu/login/loginOut">退出</a></li>
				</ul>
			</li>
		</ul>
	</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->