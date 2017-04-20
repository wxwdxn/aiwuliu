<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/transactionRecords/js/transactionRecords.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
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
		</nav>
		<!--*********导航条结束***********-->
		<div class="container-fluid eleHeight">
			<div class="row eleHeight">
				<div class="col-md-2 leftMenu eleHeight">
					<!--<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default">
					    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" role="tab" id="headingOne">
					      <h4 class="panel-title">
					      	<img src="../../../static/common/images/icon_1.png"/>
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
						      	<img src="../../../static/common/images/icon_5.png"/>
						       		报表管理	
					      	</h4>
					      </a>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed">
					      	<a href="../estimatesContractManager/estimatesContractManager.html">
						      	<h4 class="panel-title">
						      	<img src="../../../static/common/images/icon_3.png"/>
						       		合同管理
						      	</h4>
					      	</a>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed">
					      <a href="../estimatesPlanManager/estimatesPlanManager.html">
					      	<h4 class="panel-title">
					      	<img src="../../../static/common/images/icon_4.png"/>
					       		计划管理
					        
					      	</h4>
					      </a>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading"  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix" role="tab" id="headingSix">
					      <h4 class="panel-title">
					      	<img src="../../../static/common/images/icon_6.png"/>
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
					      	<img src="../../../static/common/images/icon_7.png"/>
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
					      		<li class="list-group-item menuActive">
					      			<a href="../transactionRecords/transactionRecords.html">
					      				交易记录查询
					      			</a>
					      		</li>
					      		<li class="list-group-item">
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
					      	<img src="../../../static/common/images/icon_8.png"/>
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
						<img src="../../../static/common/images/menuNarrow.png"/>
					</div>-->
				</div>
				
				<div class="iframeMain">
					<h4>
						交易记录查询
					</h4>
					<div class="infoConditions">
						<form class="form-inline">
							<div class="form-group">
								<label>
									所属部门：
								</label>
								<div class="display_inlineBlcok accountDept">
									<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder"></span>
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

									</ul>
								</div>
							</div>
							<div class="form-group">
								<label>账户类型：</label>
								<div class="display_inlineBlcok accountType">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder">请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									    <li value="0"><a href="javascript:;">全部</a></li>
									    <li value="1"><a href="javascript:;">车主账户</a></li>
									    <li value="2"><a href="javascript:;">车辆账户</a></li>
									</ul>
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>
									账户名：
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="accountName" />
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>
									交易人账号：
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="accountNo" />
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>
									交易金额：
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id ="transactionMoney"/>
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>
									交易类别：
								</label>
								<div class="display_inlineBlcok transactionType">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder">请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									    <li class="carsAndindividual" value=""><a href="javascript:;">全部</a></li>
									    <li class="cars" value="0"><a href="javascript:;">充值</a></li>
									    <li class="cars" value="1"><a href="javascript:;">提现</a></li>
									    <li class="cars" value="2"><a href="javascript:;">转出到车辆账户</a></li>
									    <li class="cars" value="3"><a href="javascript:;">车辆账户转入</a></li>
									    <li class="individual" value="2"><a href="javascript:;">扫码支付</a></li>
									    <li class="individual" value="1"><a href="javascript:;">转出到个人账户</a></li>
									    <li class="individual" value="0"><a href="javascript:;">个人账户转入</a></li>
									</ul>
									<p></p>
								</div>
							</div>
							<div class="form-group ">
								<label>
									交易项目：
								</label>
								<div class="display_inlineBlcok transactionName">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder">请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									    <li value=""><a href="javascript:;">全部</a></li>
									    <li value="0"><a href="javascript:;">加气费</a></li>
									    <li value="1"><a href="javascript:;">加油费</a></li>
									    <li value="2"><a href="javascript:;">维修费</a></li>
									    <li value="3"><a href="javascript:;">过桥费</a></li>
									    <li value="4"><a href="javascript:;">过路费</a></li>
									    <li value="5"><a href="javascript:;">过泵费</a></li>
									    <li value="6"><a href="javascript:;">罚款</a></li>
									    <li value="7"><a href="javascript:;">餐饮费</a></li>
									    <li value="8"><a href="javascript:;">其他</a></li>
									</ul>
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>
									订单流水：
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="orderNo" />
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>
									交易时间：
								</label>
								<div class="display_inlineBlcok" >
									<input type="text" class="form_datetime form-control"  id="start_time"/>至
									<input type="text" class="form_datetime form-control" id="end_time" />
									<p></p>
								</div>
							</div>
							<div class="QuerytheReset">
								<a tabindex="0" role="button" type="button" onclick="queryTransaction()" class="btn query">查询</a>
								<a type="button" class="btn reset" onclick="queryReset()">重置</a>
							</div>
						</form>
						<div class="buttons">
							<a type="button" class="btn btn_export">
								导出
							</a>
						</div>
						<div class="drivers" >
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
									<th data-field="account_dept" data-align="center" data-valign="middle">所属部门</th>
									<th data-field="account_type" data-align="center" data-valign="middle">账户类型</th>
									<th data-field="account_name" data-align="center" data-valign="middle">账户名</th>
									<th data-field="order_no" data-align="center" data-width="250px" data-valign="middle">交易流水号</th>
									<th data-field="transaction_time" data-align="center" data-valign="middle">交易时间</th>
									<th data-field="transaction_type" data-align="center" data-width="120px" data-valign="middle">交易类别</th>
									<th data-field="transaction_name" data-align="center" data-valign="middle">交易项目</th>
									<th data-field="transaction_money" data-align="center" data-valign="middle">交易金额（元）</th>
									<th data-field="transaction_addr" data-align="center" data-valign="middle">交易地点</th>
									<th data-field="account_money" data-align="center" data-valign="middle">账户余额（元）</th>
									<th data-field="transaction_person" data-align="center" data-width="180px" data-valign="middle">交易人</th>
									<th data-field="account_operation"   data-align="center" data-valign="middle">操作</th>
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
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">详情</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="form-group" class="form-inline">
					<div class="form-group uneditable">
						<label>账户名：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" value="京A888888"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>余额：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control newAdd_address"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>时间：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control"/>至
							<input type="text" class="form-control" />
							<p></p>
						</div>
					</div>
					<div class="buttons form-group">
						<label class="sr-only">查询按钮</label>
						<div class="display_inlineBlcok">
							<a type="button" class="btn btn_export">
								查询
							</a>
						</div>
					</div>
				</form>
				<div style="height: 300px;background: #000;"></div>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn hold">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
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