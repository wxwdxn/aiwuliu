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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/logisticsAccountManager/css/logisticsAccountManager.css"/>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/logisticsAccountManager/js/logisticsAccountManager.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.form.js" type="text/javascript" charset="utf-8">
	</script>
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
			            <li><a href="#">Action</a></li>
			            <li><a href="#">Another action</a></li>
			            <li><a href="#">Something else here</a></li>
			            <li class="divider"></li>
			            <li><a href="#">Separated link</a></li>
			          </ul>
		        </li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">15111111111<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="javascript:;">Action</a></li>
		            <li><a href="javascript:;">Another action</a></li>
		            <li><a href="javascript:;">Something else here</a></li>
		            <li class="divider"></li>
		            <li><a href="javascript:;">Separated link</a></li>
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
					<!--<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default">
					    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" role="tab" id="headingOne">
					      <h4 class="panel-title">
					      	<img src="<%=request.getContextPath()%>/static/common/images/icon_1.png"/>
					        <a>
					          	车联服务
					        </a>
					      </h4>
					    </div>
					    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
					      <ul class="list-group">
					      		<li class="list-group-item">
					      			<a href="../vehicleMonitoring/vehicleMonitoring.html">
					      				车辆监控
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../trackThePlayback/trackThePlayback.html">
					      				轨迹回放
					      			</a>
					      		</li>
					      	</ul>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed" >
					      <a href="../reportManager/reportManager.html">
					      	<h4 class="panel-title">
						      	<img src="<%=request.getContextPath()%>/static/common/images/icon_5.png"/>
						       		报表管理	
					      	</h4>
					      </a>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed">
					      	<a href="../estimatesContractManager/estimatesContractManager.html">
						      	<h4 class="panel-title">
						      	<img src="<%=request.getContextPath()%>/static/common/images/icon_3.png"/>
						       		合同管理
						      	</h4>
					      	</a>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed">
					      <a href="../estimatesPlanManager/estimatesPlanManager.html">
					      	<h4 class="panel-title">
					      	<img src="<%=request.getContextPath()%>/static/common/images/icon_4.png"/>
					       		计划管理
					        
					      	</h4>
					      </a>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix" role="tab" id="headingSix">
					      <h4 class="panel-title">
					      	<img src="<%=request.getContextPath()%>/static/common/images/icon_6.png"/>
					        <a>
					       		手动派单
					        </a>
					      </h4>
					    </div>
					    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
					      <ul class="list-group">
					      		<li class="list-group-item">
					      			<a href="../transportPlan/transportPlan.html">
					      				运输计划
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../vehiclePlan/vehiclePlan.html">
					      				车辆计划
					      			</a>
					      		</li>
					      	</ul>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven" role="tab" id="headingSeven">
					      <h4 class="panel-title">
					      	<img src="<%=request.getContextPath()%>/static/common/images/icon_7.png"/>
					        <a>
					       		财务金融
					        </a>
					      </h4>
					    </div>
					    <div id="collapseSeven" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingSeven">
					      <ul class="list-group">
					      		<li class="list-group-item">
					      			<a href="../virtualAccount/virtualAccount.html">
					      				虚拟账目查看
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../transactionRecords/transactionRecords.html">
					      				交易记录查询
					      			</a>
					      		</li>
					      		<li class="list-group-item menuActive">
					      			<a href="../withdrawalApproval/withdrawalApproval.html">
					      				提现审批
					      			</a>
					      		</li>
					      	</ul>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEight" aria-expanded="false" aria-controls="collapseEight" role="tab" id="headingEight">
					      <h4 class="panel-title">
					      	<img src="<%=request.getContextPath()%>/static/common/images/icon_8.png"/>
					        <a>
					       		物流管理
					        </a>
					      </h4>
					    </div>
					    <div id="collapseEight" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEight">
					      <ul class="list-group">
					      		<li class="list-group-item">
					      			<a href="../logisticsManager/logisticsManager.html">
					      				物流公司管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../vehicleManagement/vehicleManagement.html">
					      				车辆管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../driverManager/driverManager.html">
					      				司机管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../ownerManager/ownerManager.html">
					      				货主管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../OBDEquipManager/OBDEquipManager.html">
					      				设备管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../routerManager/routerManager.html">
					      				线路管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../goodTypeManager/goodTypeManager.html">
					      				货物类型管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../carTypeManager/carTypeManager.html">
					      				车厢类型管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../vehicleModelManager/vehicleModelManager.html">
					      				车辆型号管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../freightYardManager/freightYardManager.html">
					      				货场管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../schedulingOrderManager/schedulingOrderManager.html">
					      				调度单管理
					      			</a>
					      		</li>
					      		<li class="list-group-item">
					      			<a href="../allianceBusinessManagement/allianceBusinessManagement.html">
					      				线下加盟商管理	
					      			</a>
					      		</li>
					      	</ul>
					    </div>
					  </div>
					</div>
					<div isClick=true class="menuNarrow" onclick="menuNarrow(this)">
						<img src="<%=request.getContextPath()%>/static/common/images/menuNarrow.png"/>
					</div>-->
				</div>
				
				<div class="iframeMain">
					<h4>
						物流公司账户管理
					</h4>
					<div class="infoConditions container-fluid">
						<div class="row">
							<div class="col-md-12 withdrawalApproval">
								<div class="withdrawalApprovalAll">
									平台账户总额度(元) <span id="platAmountSum">333</span>
								</div>
								<div class="approval">
									<div class="withdrawalBefore">
										车主账户总额(元) <span id="carOwnerAccountSum">4444</span>&nbsp;
									</div>
									<div class="withdrawalAfter">
										车辆账户总额(元) <span id="carAccountSumLeft">5555</span>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="buttons">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="btn">
										导出
									</a>
									<a class="btn" onclick="a()">
										充值/提现
									</a>
									<a class="btn">
										转入/转出
									</a>
								</div>
								<div class="col-md-12">
									<%--交易记录管理列表--%>
									<table data-toggle="table" class="table table-striped table-bordered table-hover"
										   id="logisticsAccountManagerTable"
										   data-query-params="logisticsAccountQueryParams"
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
											<th data-field="carOwner_account" data-align="center" data-valign="middle">车主账户</th>
											<th data-field="car_account" data-align="center" data-valign="middle">车辆账户(车牌号)</th>
											<th data-field="account_money" data-align="center" data-valign="middle">余额(元)</th>
											<th data-field="operation" data-align="center" data-valign="middle">操作</th>
										</tr>
										</thead>
									</table>
									<div class="form-inline" align="center" style="margin: -45px auto 17px;">
										<input id="logisticsAccount_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
										<button id="logisticsAccount_goBtn" class="btn btn-success">GO</button>
									</div>
								</div>
								<div style="height: 300px;background: #fff;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal 企业账户明细Corporate account details-->
		<div class="modal fade bs-example-modal-lg" id="myModalCorporate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">企业账户明细</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-inline">
		      		<div class="form-group ">
						<label>交易流水：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" value="京A888888"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>用户姓名：</label>
						<div class="display_inlineBlcok">
							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder">全部</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							    <li><a href="javascript:;">集装箱</a></li>
							    <li><a href="javascript:;">仓栅</a></li>
							    <li><a href="javascript:;">黄金车</a></li>
							    <li><a href="javascript:;">自由厢栏</a></li>
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>用户手机：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control newAdd_address"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>交易金额：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control transactionAmount"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>交易类别：</label>
						<div class="display_inlineBlcok">
							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder">全部</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							    <li><a href="javascript:;">集装箱</a></li>
							    <li><a href="javascript:;">仓栅</a></li>
							    <li><a href="javascript:;">黄金车</a></li>
							    <li><a href="javascript:;">自由厢栏</a></li>
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>交易日期：</label>
						<div class="display_inlineBlcok">
							<input readonly type="text" class="form-control"/>至
							<input readonly type="text" class="form-control"/>
							<p></p>
						</div>
					</div>
		      		<div class="QuerytheReset">
						<a type="button" class="btn query">查询</a>
						<a type="button" class="btn reset">重置</a>
					</div>
					<div class="buttons">
						<a href="javascript:;" class="btn">
							导出
						</a>
					</div>
					<div style="height: 300px;border: 1px solid #000;"></div>
		      	</form>
		      </div>
		      <div class="modal-footer modalFooter hidden">
		        <a type="button" class="btn hold">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Modal 车辆账户明细Vehicle account details-->
		<div class="modal fade bs-example-modal-lg" id="myModalVehicle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">交易账户明细</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-inline">
		      		<div class="form-group ">
						<label>交易流水：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" value="京A888888"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>用户姓名：</label>
						<div class="display_inlineBlcok">
							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder">全部</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							    <li><a href="javascript:;">集装箱</a></li>
							    <li><a href="javascript:;">仓栅</a></li>
							    <li><a href="javascript:;">黄金车</a></li>
							    <li><a href="javascript:;">自由厢栏</a></li>
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>用户手机：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control newAdd_address"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>交易金额：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control transactionAmount"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>交易类别：</label>
						<div class="display_inlineBlcok">
							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder">全部</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							    <li><a href="javascript:;">集装箱</a></li>
							    <li><a href="javascript:;">仓栅</a></li>
							    <li><a href="javascript:;">黄金车</a></li>
							    <li><a href="javascript:;">自由厢栏</a></li>
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>交易项目：</label>
						<div class="display_inlineBlcok">
							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder">全部</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							    <li><a href="javascript:;">集装箱</a></li>
							    <li><a href="javascript:;">仓栅</a></li>
							    <li><a href="javascript:;">黄金车</a></li>
							    <li><a href="javascript:;">自由厢栏</a></li>
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>交易日期：</label>
						<div class="display_inlineBlcok">
							<input readonly type="text" class="form-control startWidth"/>至
							<input readonly type="text" class="form-control endWidth"/>
							<p></p>
						</div>
					</div>
		      		<div class="QuerytheReset">
						<a type="button" class="btn query">查询</a>
						<a type="button" class="btn reset">重置</a>
					</div>
					<div class="buttons">
						<a href="javascript:;" class="btn">
							导出
						</a>
					</div>
					<div style="height: 300px;border: 1px solid #000;"></div>
		      	</form>
		      </div>
		      <div class="modal-footer modalFooter hidden">
		        <a type="button" class="btn hold">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 转入转出 Recharge and withdrawal-->
		<div class="modal fade bs-example-modal-lg" id="myModalRechargeAndWithdrawal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalRechargeAndWithdrawal" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">转入转出</h4>
		      </div>
		      <div class="modal-body">
		      		<div class="myModalRechargeAndWithdrawalBtn text-center">
		      			<a href="javascript:;" class="btn clickActive">
		      				转入
		      			</a>
		      			<a href="javascript:;" class="btn">
		      				转出
		      			</a>
		      		</div>
		      		<div class="form-horizontal text-center recharge">
		      			<div class="form-group">
		      				<label>公司总账户余额(元)：</label>
		      				<div class="display_inlineBlcok">
		      					<input disabled type="text" class="form-control" />
		      				</div>
		      			</div>
		      			<div class="form-group">
		      				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;充值车辆账户：</label>
		      				<div class="display_inlineBlcok">
		      					<input disabled type="text" class="form-control" />
		      				</div>
		      			</div>
		      			<div class="form-group">
		      				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;输入金额(元)：</label>
		      				<div class="display_inlineBlcok">
		      					<input type="text" class="form-control" />
		      				</div>
		      			</div>
		      			<div class="form-group">
		      				<label>公司账户支付密码：</label>
		      				<div class="display_inlineBlcok">
		      					<input type="text" class="form-control" />
		      				</div>
		      			</div>
		      		</div>
		     		<div class="form-horizontal text-center hidden withdrawal">
		      			<div class="form-group">
		      				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;物流公司账户：</label>
		      				<div class="display_inlineBlcok">
		      					<input disabled type="text" class="form-control" />
		      				</div>
		      			</div>
		      			<div class="form-group">
		      				<label>输入提现金额(元)：</label>
		      				<div class="display_inlineBlcok">
		      					<input disabled type="text" class="form-control" />
		      				</div>
		      			</div>
		      			<div class="form-group">
		      				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;绑卡支付密码：</label>
		      				<div class="display_inlineBlcok">
		      					<input type="text" class="form-control" />
		      				</div>
		      			</div>
		      			<div class="form-group">
		      				<label>&nbsp;&nbsp;输入短信验证码：</label>
		      				<div class="display_inlineBlcok">
		      					<input type="text" class="form-control code" />
		      					<a class="codeClick" href="javascript:;" isClick="true" onclick="getCode(this)">点击获取验证码</a>
		      				</div>
		      			</div>
		      		</div>
		      </div>
		      <div class="modal-footer modalFooter recharge">
		        <a type="button" class="btn hold">确定</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		      <div class="modal-footer modalFooter withdrawal hidden">
		        <a type="button" class="btn hold">确定</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
	</body>
	<script type="text/javascript">
		//交易记录table页脚
		$(function () {
			var table = $('#logisticsAccountManagerTable'),
					page = $('#logisticsAccount_page'),
					goBtn = $('#logisticsAccount_goBtn');
			// 跳转到某页
			goBtn.click(function () {
				table.bootstrapTable('selectPage', +page.val());
			});
			$("#transactionManagerTable .th-inner").eq(0).append('<span>全选</span>');


		})
	</script>
</html>