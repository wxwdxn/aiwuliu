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
	<%--Bootstrap-table CSS文件--%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
	<%--Bootstrap-Datetimepicker CSS文件--%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/transactionRecords/css/transactionRecords.css"/>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/transactionRecords/js/transactionRecords.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.form.js" type="text/javascript" charset="utf-8"></script>
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
					    <%--<div class="panel-heading">--%>
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
					      		<%--<li class="list-group-item">--%>
					      			<%--<a href="/iwuliu/virtualAccountManager/virtualAccount">--%>
					      				<%--虚拟账目查看--%>
					      			<%--</a>--%>
					      		<%--</li>--%>
					      		<%--<li class="list-group-item menuActive">--%>
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
					      			<%--<a href="<%=request.getContextPath()%>/vehicleManager/home">--%>
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
						交易记录查询
					</h4>
					<div class="infoConditions">
						<form class="form-inline">
							<div class="form-group">
								<label>账户类型：</label>
								<div class="display_inlineBlcok accountType">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder">请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu"  aria-labelledby="dropdownMenu1">
									    <li><a href="javascript:;" value="0" >全部</a></li>
									    <li><a href="javascript:;" value="1">车辆账户</a></li>
									    <li><a href="javascript:;" value="2">个人账户</a></li>
									</ul>
									<p></p>
								</div>
							</div>
							<div class="form-group licensePlateNumber">
								<label>
									车牌号：
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="carNum"/>
									<p></p>
								</div>
								<%--<span class="red">*</span>--%>
							</div>
							<div class="form-group individualName">
								<label>
									个人姓名：
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="personName" />
									<p></p>
								</div>
								<%--<span class="red">*</span>--%>
							</div>
							<div class="form-group phoneNumber">
								<label>
									手机号：
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="phoneNum" />
									<p></p>
								</div>
								<%--<span class="red">*</span>--%>
							</div>
							<div class="QuerytheReset">
								<a type="button" class="btn query" onclick="transactionQuery();">查询</a>
								<a type="button" class="btn reset" onclick="transactionManagerQueryReset();">重置</a>
							</div>
						</form>
						<div class="buttons form-inline">
							<a type="button" class="btn btn_export">
								导出
							</a>
							<a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
								详情/编辑
							</a>
						</div>
							<div class="col-md-12">
								<%--交易记录管理列表--%>
								<table data-toggle="table" class="table table-striped table-bordered table-hover"
									   id="transactionManagerTable"
									   data-query-params="transactionManagerQueryParams"
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
										<th data-field="account_type" data-align="center" data-valign="middle">账户类型</th>
										<th data-field="account_name" data-align="center" data-valign="middle">账户名</th>
										<th data-field="account_money" data-align="center" data-valign="middle">余额(元)</th>
									</tr>
									</thead>
								</table>
								<div class="form-inline" align="center" style="margin: -45px auto 17px;">
									<input id="transaction_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
									<button id="transaction_goBtn" class="btn btn-success">GO</button>
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>



		<!-- Modal 详情-->
		<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span  aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">详情</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="form-group" class="form-inline">
					<div class="form-group uneditable">
						<label>账户名：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="accountName" />
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>余额：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="accountMoney" class="form-control newAdd_address"/>
							<p></p>
						</div>
					</div><br/>
					<div class="form-group">
						<label>时间：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form_datetime form-control" id="start_time"/>至
							<input type="text" class="form_datetime form-control" id="end_time"/>
							<p></p>
						</div>
					</div>
					<div class="buttons form-group">
						<label class="sr-only">查询按钮</label>
						<div class="display_inlineBlcok">
							<a type="button" class="btn btn_export" onclick="transactionRecordsDetailClick()">
								查询
							</a>
						</div>
					</div>
				</form>
				  <div>
					  <%--交易详情管理列表--%>
					  <table data-toggle="table" class="table table-striped table-bordered table-hover"
							 id="transactionDetailManagerTable"
							 data-query-params="transactionDetailManagerQueryParams"
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
							  <%--<th data-field="state" data-checkbox="true" data-width="100px"></th>--%>
							  <th data-field="time_text" data-align="center" data-valign="middle">时间</th>
							  <th data-field="account_money" data-align="center" data-valign="middle">额度</th>
							  <th data-field="payment_text" data-align="center" data-valign="middle">类别</th>
							  <th data-field="amount_text" data-align="center" data-valign="middle">交易金额</th>
							  <th data-field="target_text" data-align="center" data-valign="middle">来源/目标</th>
						  </tr>
						  </thead>
					  </table>
					  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
						  <input id="transactionDetail_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
						  <button id="transactionDetail_goBtn" class="btn btn-success">GO</button>
					  </div>
				  </div>
			  </div>
			</div>
				<%--<div style="height: 300px;background: #000;"></div>--%>
		      <%--</div>--%>
		      <%--<div class="modal-footer modalFooter">--%>
		        <%--<a type="button" class="btn hold">保存</a>--%>
		        <%--<a type="button" class="btn cancel" data-dismiss="modal">取消</a>--%>
		      <%--</div>--%>
		    </div>
	</div>

		<!--Modal 小提示框-->
		<div class="modal fade bs-example-modal-sm" id="smallModalInfo" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">提示信息</h4>
					</div>
					<div class="modal-body text-center">
						<h2 class="titleInfo">

						</h2>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript">
	//交易记录table页脚
	$(function () {
		var table = $('#transactionManagerTable'),
				page = $('#transaction_page'),
				goBtn = $('#transaction_goBtn');
		// 跳转到某页
		goBtn.click(function () {
			table.bootstrapTable('selectPage', +page.val());
		});
		$("#transactionManagerTable .th-inner").eq(0).append('<span>全选</span>');


	})
</script>
</html>