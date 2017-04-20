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
	<%--Bootstrap-Datetimepicker CSS文件--%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
	<%--Bootstrap-table CSS文件--%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/withdrawalApproval/css/withdrawalApproval.css"/>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/withdrawalApproval/js/withdrawalApproval.js" type="text/javascript" charset="utf-8"></script>
	<%--Bootstrap时间控件  JavaScript 文件以及汉化文件--%>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
					    <%--<div class="panel-heading"  class="collapsed" >--%>
					      <%--<a href="../reportManager/reportManager.html">--%>
					      	<%--<h4 class="panel-title">--%>
						      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_5.png"/>--%>
						       		<%--报表管理--%>
					      	<%--</h4>--%>
					      <%--</a>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed">--%>
					      	<%--<a href="../estimatesContractManager/estimatesContractManager.html">--%>
						      	<%--<h4 class="panel-title">--%>
						      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_3.png"/>--%>
						       		<%--合同管理--%>
						      	<%--</h4>--%>
					      	<%--</a>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					  <%--<div class="panel panel-default">--%>
					    <%--<div class="panel-heading"  class="collapsed">--%>
					      <%--<a href="../estimatesPlanManager/estimatesPlanManager.html">--%>
					      	<%--<h4 class="panel-title">--%>
					      	<%--<img src="<%=request.getContextPath()%>/static/common/images/icon_4.png"/>--%>
					       		<%--计划管理--%>

					      	<%--</h4>--%>
					      <%--</a>--%>
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
					      			<%--<a href="../transportPlan/transportPlan.jsp">--%>
					      				<%--运输计划--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../vehiclePlan/vehiclePlan.jsp">--%>
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
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="/iwuliu/virtualAccountManager/virtualAccount">--%>
					      				<%--虚拟账目查看--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="/iwuliu/transactionManager/transactionRecords">--%>
					      				<%--交易记录查询--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item menuActive">--%>
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
					      			<%--<a href="../driverManager/driverManager.html">--%>
					      				<%--司机管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../ownerManager/ownerManager.html">--%>
					      				<%--货主管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../OBDEquipManager/OBDEquipManager.html">--%>
					      				<%--设备管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../routerManager/routerManager.html">--%>
					      				<%--线路管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../goodTypeManager/goodTypeManager.html">--%>
					      				<%--货物类型管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../carTypeManager/carTypeManager.html">--%>
					      				<%--车厢类型管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../freightYardManager/freightYardManager.html">--%>
					      				<%--货场管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../schedulingOrderManager/schedulingOrderManager.jsp">--%>
					      				<%--调度单管理--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="../allianceBusinessManagement/allianceBusinessManagement.html">--%>
					      				<%--线下加盟商管理--%>
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
						提现审批
					</h4>
					<div class="infoConditions container-fluid">
						<div class="row">
							<div class="col-md-12 withdrawalApproval">
								<div class="withdrawalApprovalAll">
									平台账户总额度(元) <span id="platAmountSum">333</span>
								</div>
								<div class="approval">
									<div class="withdrawalBefore">
										提现申请总额(元) <span id="withdrawalSum">4444</span>&nbsp;
										<a href="javascript:;" class="btn withdrawalAmountDetailClick">明细</a>
									</div>
									<div class="withdrawalAfter">
										全部提现后平台账户结余额度(元) <span id="platAmountSumLeft">5555</span>
									</div>
								</div>
							</div>
							<div class="form-inline col-md-12">
								<div class="form-group">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label>时间：</label>
									<div class="display_inlineBlcok">
										<input type="text" id="start_time" class="form_datetime form-control" />至
										<input type="text" id="end_time" class="form_datetime form-control" />
									</div>
								</div>
								<div class="form-group">
									&nbsp;&nbsp;
									<label>审核状态：</label>
	    							<div class="display_inlineBlcok checkType">
		    							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											<span class="placeHolder">请选择</span>
										    <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										    <li><a href="javascript:;">全部</a></li>
										    <li><a href="javascript:;">审核通过</a></li>
										    <li><a href="javascript:;">待审核</a></li>
										    <li><a href="javascript:;">审核未通过</a></li>
										</ul>
										<p></p>
	    							</div>
								</div>
								<div class="form-group">
									&nbsp;&nbsp;
									<label class="sr-only">查询按钮</label>
									<div class="display_inlineBlcok buttons">
										<a type="button" onclick="queryWithdrawalInfo()" class="btn">
											查询
										</a>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="buttons">
									&nbsp;&nbsp;
									<span class="drawa">提现审批明细</span>
									<a class="btn">
										导出
									</a>

								</div>
								<%--<div style="height: 300px;background: #fff;"></div>--%>
								<div>
									<%--提现审批明细列表--%>
									<table data-toggle="table" class="table table-striped table-bordered table-hover"
										   id="withdrawalApprovalTable"
										   data-query-params="withdrawalApprovalQueryParams"
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
											<th data-field="account_type" data-align="center" data-valign="middle" data-sortable="true">
												账户类型</th>
											<th data-field="account_name" data-align="center" data-valign="middle" data-sortable="true">
												账户名</th>
											<th data-field="target" data-align="center" data-valign="middle" data-sortable="true">
												提现目标</th>
											<th data-field="withdraw_amount" data-align="center" data-valign="middle" data-sortable="true">
												提现金额（元）</th>
											<th data-field="withdraw_status" data-align="center" data-valign="middle" data-sortable="true">
												审核状态</th>
											<th data-field="deny_reason" data-align="center" data-valign="middle">
												拒绝理由</th>
										</tr>
										</thead>
									</table>
									<div class="form-inline" align="center" style="margin: -45px auto 17px;">
										<input id="withdrawal_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
										<button id="withdrawal_goBtn" class="btn btn-success">GO</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript">
	$(function(){
		// 表格go跳转
		var table = $('#withdrawalApprovalTable'),
				page = $('#withdrawal_page'),
				goBtn = $('#withdrawal_goBtn');
		// 跳转到某页
		goBtn.click(function () {
			table.bootstrapTable('selectPage', +page.val());
		});
		$("#withdrawalApprovalTable .th-inner").eq(0).append('<span>全选</span>');
	})
</script>
</html>