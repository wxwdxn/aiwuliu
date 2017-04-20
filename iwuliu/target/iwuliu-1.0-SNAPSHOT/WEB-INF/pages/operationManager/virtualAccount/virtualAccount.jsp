<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String username = request.getSession().getAttribute("username").toString();%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="renderer" content="webkit|ie-comp|ie-stand"> 
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/virtualAccount/css/virtualAccount.css"/>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/virtualAccount/js/virtualAccount.js" type="text/javascript" charset="utf-8"></script>
	<%--Bootstrap-table JavaScript 文件以及汉化文件--%>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<body>
		<!--*********导航条开始***********-->
		<nav class="navbar navbar-default" role="navigation">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">
		      	<img src="<%=request.getContextPath()%>/static/common/images/logo.png"/>
		      </a>
		    </div>

			  <!-- Collect the nav links, forms, and other content for toggling -->
			  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

				  <ul class="nav navbar-nav navbar-right">
					  <li class="dropdown">
						  <a href="#" class="dropdown-toggle" data-toggle="dropdown">系统管理 <span class="caret"></span></a>
						  <ul class="dropdown-menu" role="menu">
							  <li><a href="#">权限管理</a></li>
							  <li><a href="#">角色管理</a></li>
						  </ul>
					  </li>
					  <li class="dropdown">
						  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=username%><span class="caret"></span></a>
						  <ul class="dropdown-menu" role="menu">
							  <li><a href="/iwuliu/login/loginOut">退出</a></li>
						  </ul>
					  </li>
				  </ul>
			  </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<!--*********导航条结束***********-->
		<div class="container-fluid eleHeight">
			<div class="row eleHeight">
				<div class="col-md-2 leftMenu eleHeight">
					<%--<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" role="tab" id="headingOne">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_1.png"/>--%>
					        <%--<a>--%>
					          	<%--车联服务--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					    <%--<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">--%>
					      <%--<ul class="list-group">--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="/iwuliu/operationWelcome/home">--%>
					      				<%--车辆监控--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="/iwuliu/operationWelcome/truckReplay_home">--%>
					      				<%--轨迹回放--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      	<%--</ul>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive" role="tab" id="headingFive">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_5.png"/>--%>
					        <%--<a>--%>
					       		<%--报表管理--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					    <%--<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">--%>
					      <%----%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseNine" aria-expanded="false" aria-controls="collapseNine" role="tab" id="headingNine">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_9.png"/>--%>
					        <%--<a>--%>
					        	<%--运力评估--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					    <%--<div id="collapseNine" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingNine">--%>
					     <%----%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" role="tab" id="headingThree">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_3.png"/>--%>
					        <%--<a>--%>
					       		<%--合同管理--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					    <%--<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">--%>
					      <%----%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading menuActive">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_4.png"/>--%>
					        <%--<a>--%>
					       		<%--计划管理--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix" role="tab" id="headingSix">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_6.png"/>--%>
					        <%--<a>--%>
					       		<%--手动派单--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					    <%--<div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">--%>
					      <%--<ul class="list-group">--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--运输计划--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--车辆计划--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      	<%--</ul>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven" role="tab" id="headingSeven">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_7.png"/>--%>
					        <%--<a>--%>
					       		<%--财务金融--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					    <%--<div id="collapseSeven" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingSeven">--%>
					      <%--<ul class="list-group">--%>
					      		<%--<li class="list-group-item menuActive">--%>
					      			<%--<a href="/iwuliu/virtualAccountManager/virtualAccount">--%>
					      				<%--虚拟账目查看--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="/iwuliu/transactionManager/transactionRecords">--%>
					      				<%--交易记录查询--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="/iwuliu/withdrawalApprovalManager/withdrawalApproval">--%>
					      				<%--提现审批--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      	<%--</ul>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEight" aria-expanded="false" aria-controls="collapseEight" role="tab" id="headingEight">--%>
					      <%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_8.png"/>--%>
					        <%--<a>--%>
					       		<%--物流管理--%>
					        <%--</a>--%>
					      <%--</h4>--%>
					    <%--</div>--%>
					    <%--<div id="collapseEight" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEight">--%>
					      <%--<ul class="list-group">--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="<%=request.getContextPath()%>/logisticsCompanyManager/home">--%>
					      				<%--物流公司管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../vehicleManagement/vehicleManagement.html">--%>
					      				<%--车辆管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="#">--%>
					      				<%--司机管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="#">--%>
					      				<%--货主管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--OBD设备管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--线路管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--货物类型管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--车厢类型管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--货场管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--调度单管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="javascript:;">--%>
					      				<%--线下加盟商管理	--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      	<%--</ul>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					<%--</div>--%>
					<div isClick=true class="menuNarrow" onclick="menuNarrow(this)">
						<img src="<%=request.getContextPath()%>/static/common/images/menuNarrow.png"/>
					</div>
				</div>
				<div class="iframeMain">
					<h4>
						虚拟账目查看
					</h4>
						<div class="infoConditions container-fluid"	>
						<div class="row">
							<div class="col-md-12 virtualAccountAll">
								平台账户总额(元) &nbsp; &nbsp;<span id="platAmountSum"></span>
							</div>
							<div class="col-md-12 virtualAccountAlgorithm">
								<div class="algorithm">
									<div class="personalAccount">
										个人账户总额度(元) <span id="personalAccountSum"></span>
										<a class="btn personalAccountDetailClick" href="javascript:;">
											明细
										</a>
									</div> 
									<div class="vehicleAccount">
										车辆账户总额度(元) <span id="truckAccountSum"></span>
										<a class="btn vehicleAccountDetailClick" href="javascript:;">
											明细
										</a>
									</div>
								</div>
								<div class="platform">
									平台账户可用额度(元) <span id="platAmountUsable"></span>
								</div>
								<div class="col-md-12 tip red">
									提示：点击<span>【</span>明细<span>】</span>按钮，查询出相应的数据
								</div>
							</div>
							<div class="col-md-6 virtualAccountAlgorithmDetail">
								<div class="col-md-12">
									<h3>
										个人账户明细
									</h3>
									<div class="gOut">
										<a class="btn" href="javascript:;">
											导出
										</a>
									</div>
									<div>
										<%--个人账户明细--%>
										<table data-toggle="table" class="table table-striped table-bordered table-hover"
											   id="memberAccountTable"
											   data-query-params="memberAccountQueryParams"
											   data-query-params-type="limit"
											   data-pagination="true"
											   data-pagination-first-text="首页"
											   data-pagination-pre-text="上一页"
											   data-pagination-next-text="下一页"
											   data-pagination-last-text="末页"
											   data-row-style="rowStyle"
											   data-page-list="[10, 20,30]"
											   data-smart-display="false"
										>
											<thead style="background-color:#dbfff2;">
											<tr>
												<th data-field="state" data-checkbox="true" data-width="100px"></th>
												<th data-field="member_name" data-align="center" data-valign="middle">账户名</th>
												<th data-field="member_account_amount" data-align="center" data-valign="middle"
													data-formatter="moneyFormatter">余额</th>
											</tr>
											</thead>
										</table>
										<div class="form-inline" align="center" style="margin: -45px auto 17px;">
											<input id="member_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
											<button id="member_goBtn" class="btn btn-success">GO</button>
										</div>
									</div>
								</div>

						</div>
							<div class="col-md-6 personalAccountDetail">
								<div class="col-md-12">
									<h3>
										车辆账户明细
									</h3>
									<div class="col-md-2 gOut">
										<a class="btn" href="javascript:;">
											导出
										</a>
									</div>
								</div>
								<div class="col-md-12">
									<%--车辆账户明细--%>
									<table data-toggle="table" class="table table-striped table-bordered table-hover"
										   id="truckAccountTable"
										   data-query-params="truckAccountQueryParams"
										   data-query-params-type="limit"
										   data-pagination="true"
										   data-pagination-first-text="首页"
										   data-pagination-pre-text="上一页"
										   data-pagination-next-text="下一页"
										   data-pagination-last-text="末页"
										   data-row-style="rowStyle"
										   data-page-list="[10, 20,30]"
										   data-smart-display="false"
									>
										<thead style="background-color:#dbfff2;">
										<tr>
											<th data-field="state" data-checkbox="true" data-width="100px"></th>
											<th data-field="plate_number" data-align="center" data-valign="middle">账户名</th>
											<th data-field="cash_amount" data-align="center" data-valign="middle"
												data-formatter="moneyFormatter">余额</th>
										</tr>
										</thead>
									</table>
									<div class="form-inline" align="center" style="margin: -45px auto 17px;">
										<input id="truckAccount_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
										<button id="truckAccount_goBtn" class="btn btn-success">GO</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>