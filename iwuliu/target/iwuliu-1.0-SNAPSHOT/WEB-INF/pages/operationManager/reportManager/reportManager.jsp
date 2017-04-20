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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
	<%--Bootstrap-table CSS文件--%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
	<%--Bootstrap-Datetimepicker CSS文件--%>
	<link rel="stylesheet"
		  href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/reportManager/css/reportManager.css"/>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
	<%--Bootstrap时间控件  JavaScript 文件以及汉化文件--%>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<%--Bootstrap-table JavaScript 文件以及汉化文件--%>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/reportManager.js" type="text/javascript" charset="utf-8"></script>
	<%--echart--%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/common/echart/echarts.min.js"></script>
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
				</div>
				<div class="iframeMain">
					<h4>
						报表管理
					</h4>
					<div class="infoConditions">
						
						<div class="col-md-12">
							<%--报表管理列表--%>
							<table data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="reportManagerTable"
								   data-url="/iwuliu/reportManager/reportManagerList"
								   data-query-params="reportManagerQueryParams"
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
								<tr colspan="3">
									<th data-field="report_manage_number" data-visible="false"data-align="center" data-valign="middle">记录编号</th>
									<th data-field="report_type_number" class="hidden report_type_number" data-align="center" data-valign="middle">报表类型编号</th>
									<th data-field="report_name" data-align="center" data-valign="middle">报表名称</th>
									<th data-field="relevance_info_id" data-visible="false" data-align="center" data-valign="middle">关联信息ID</th>
									<th data-field="relevance_info" data-align="center" data-valign="middle">应用对象</th>
									<th data-field="report_detail" data-align="center" data-valign="middle" data-formatter="detailFormatter">报表详细</th>
								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="report_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
								<button id="report_goBtn" class="btn btn-success">GO</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--Modal 卡车运力-->
		<div class="modal fade bs-example-modal-lg" id="truckTransportCapacityManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
		        <a type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
		        <h4 class="modal-title">卡车运力</h4>
		      </div>
		      <div class="modal-body" id="transportCapacity" style="height:50rem;width:60rem;display: block">
				  <%--卡车运力--%>
				  <script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/truckTransportCapacityManager.js"
						  type="text/javascript" charset="utf-8"></script>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 司机出勤率-->
		<div class="modal fade bs-example-modal-lg" id="driverAttendanceManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
		        <a type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
		        <h4 class="modal-title">司机出勤率</h4>
		      </div>
		      <div class="modal-body" id="driverAttendance" style="height: 50rem;width: 60rem;">
				  <%--司机出勤率--%>
				  <script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/driverAttendanceManager.js"
						  type="text/javascript" charset="utf-8"></script>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 司机信用统计-->
		<div class="modal fade bs-example-modal-lg" id="driverCreditManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
		        <a type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
		        <h4 class="modal-title" >司机信用统计</h4>
		      </div>
		      <div class="modal-body" id="driverCredit" style="height: 50rem;width: 60rem;">
				  <%--司机信用--%>
				  <script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/driverCreditManager.js"
						  type="text/javascript" charset="utf-8"></script>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 驾驶行为分析报表-->
		<div class="modal fade bs-example-modal-lg" id="drivingBehaviorManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal" aria-label="Close">&nbsp;<span aria-hidden="true">&times;</span>&nbsp;</button>
				   <button isClick="true" onclick="fullScreen(this)" title="放大" type="button" class="close fullScreen">&nbsp;
					   <span class="glyphicon glyphicon-resize-full"></span>&nbsp;
				   </button>
				   <h4 class="modal-title" >驾驶行为分析报表</h4>
			   </div>
		      <div class="modal-body">
	      		<form class="form-inline">
						<div class="form-group">
							<label>所属单位：</label>
							<div class="display_inlineBlcok company_name">
								<button type="button" class="btn btn-default dropdown-toggle" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									<span class="placeHolder">请选择</span>
								    <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
								    <li value=""><a href="javascript:;">北京瞪羚科技有限公司</a></li>
								</ul>
								<p></p>
							</div>
						</div>
						<div class="form-group">
							<label>车主姓名：</label>
							<div class="display_inlineBlcok">
								<input type="text" class="form-control" id="person_name"/>
								<p></p>
							</div>
						</div>
						<div class="form-group">
							<label>车牌：</label>
							<div class="display_inlineBlcok equipmentStatics">
								<input type="text" class="form-control" id="plate_number" />
								<p></p>
							</div>
						</div>
						<div class="form-group">
							<label>日期：</label>
							<div class="display_inlineBlcok equipmentStatics">
								<input  class="form_datetime form-control"id="beginTime" type="text" size="16" />
								至
								<input class="form_datetime form-control"id="endTime" type="text" size="16" />
								<p></p>
							</div>
						</div>
						<div class="QuerytheReset">
							<a type="button" class="btn query" onclick="queryDrivingBehavior()">查询</a>
							<a type="button" class="btn reset" onclick="queryDrivingBehaviorReset()">重置</a>
						</div>
					</form>
					<div class="buttons">
						<a type="button" class="btn btn_export" onclick="exportExcel()">
							导出
						</a>
					</div>
					<div class="col-md-12">
						<table data-toggle="table" class="table table-striped table-bordered table-hover"
							   id="drivingBehaviorAnalysisTable"
							   data-query-params="queryParams"
							   data-query-params-type="limit"
							   data-pagination="true"
							   data-pagination-first-text="首页"
							   data-pagination-pre-text="上一页"
							   data-pagination-next-text="下一页"
							   data-pagination-last-text="末页"
							   data-row-style="rowStyle"
							   data-page-list="[10,20,30]"
							   data-smart-display="false"
						>
							<thead style="background-color:#dbfff2;">
							<tr>
								<th data-field="state" data-checkbox="true" data-width="100px"></th>
								<th data-field="member_name" data-align="center" data-valign="middle" data-sortable="true"
									data-width="100px">
									用户名
								</th>
								<th data-field="person_name" data-align="center" data-valign="middle" data-width="100px">车主</th>
								<th data-field="plate_number" data-align="center" data-valign="middle" data-width="100px">车牌号</th>
								<th data-field="driving_days" data-align="center" data-valign="middle" data-width="100px">驾驶天数</th>
								<th data-field="average_score" data-align="center" data-valign="middle"
									data-formatter="averageScoreFormatter" data-width="100px">评分级别</th>
								<th data-field="over_speed_count" data-align="center" data-valign="middle" data-width="100px">超速次数</th>
								<th data-field="rapid_acceleration_count" data-align="center" data-valign="middle" data-width="100px">急加速次数</th>
								<th data-field="high_engine_speed_count" data-align="center" data-valign="middle"data-width="100px" >转速过高次数</th>
								<th data-field="over_speed_duration" data-align="center" data-valign="middle" data-width="100px">超速时长(秒)</th>
								<th data-field="fatigue_driving_count" data-align="center" data-valign="middle" data-width="150px">疲劳驾驶提醒次数</th>
								<th data-field="idling_count" data-align="center" data-valign="middle" data-width="100px">怠速次数</th>
								<th data-field="idling_duration" data-align="center" data-valign="middle" data-width="100px">怠速时长(秒)</th>
								<th data-field="turns_count" data-align="center" data-valign="middle" data-width="100px">急转弯次数</th>
								<th data-field="trip_count" data-align="center" data-valign="middle" data-width="100px">空挡滑行次数</th>
								<th data-field="trip_duration" data-align="center" data-valign="middle" data-width="150px">空挡滑行时长(秒)</th>
								<%--<th data-field="advice" data-align="center" data-valign="middle" data-formatter="adviceFormatter">--%>
								<%--<span style="width: 400px"></span>建议--%>
								<%--</th>--%>
							</tr>
							</thead>
						</table>
						<div class="form-inline" align="center" style="margin: -45px auto 17px;text-align: center;">
							<input id="page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
							<button id="goBtn" class="btn btn-success">GO</button>
						</div>
					</div>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 车辆运营报表-->
		<div class="modal fade bs-example-modal-lg" id="vehicleOperationManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal" aria-label="Close">&nbsp;<span aria-hidden="true">&times;</span>&nbsp;</button>
				   <button isClick="true" onclick="fullScreen(this)" title="放大" type="button" class="close truckFullScreen">&nbsp;
					   <span class="glyphicon glyphicon-resize-full"></span>&nbsp;
				   </button>
				   <h4 class="modal-title" >车辆运营报表</h4>
		      </div>
		      <div class="modal-body">
	      		<form  class="form-inline">
						<div class="form-group">
							<label>日期：</label>
							<div class="display_inlineBlcok equipmentStatics">
								<input class="form_datetime form-control"id="truckOperation_beginTime" type="text" size="16" />
								至
								<input class="form_datetime form-control"id="truckOperation_endTime" type="text" size="16" />
								<p></p>
							</div>
						</div>
						<div class="QuerytheReset">
							<a type="button" class="btn query"  onclick="truckOperation_searchInfo();">查询</a>
							<a type="button" class="btn reset" onclick="truckOperation_resetTime();">重置</a>
						</div>
					</form>
					<div class="buttons">
						<a type="button" class="btn btn_export" onclick="truckOperation_export();">
							导出
						</a>
					</div>
					<div class="col-md-12">
						<%--车辆运营--%>
						<table data-toggle="table" class="table table-striped table-bordered table-hover"
							   id="truckOperationTable"
							   data-query-params="truckOperationQueryParams"
							   data-query-params-type="limit"
							   data-pagination="true"
							   data-pagination-first-text="首页"
							   data-pagination-pre-text="上一页"
							   data-pagination-next-text="下一页"
							   data-pagination-last-text="末页"
							   data-row-style="rowStyle"
							   data-page-list="[10, 20,30]"
							   data-smart-display="false"
							   data-height="100">
							<thead style="background-color:#dbfff2;">
							<tr>
								<th data-field="lhc" data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
								<th data-field="lhc" data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
								<th data-field="vi" data-colspan="2" data-align="center" data-valign="middle" data-width="200px">车辆信息</th>
								<th data-field="da" data-colspan="2" data-align="center" data-valign="middle" data-width="200px">日期</th>
								<th data-field="ti" data-colspan="1" data-align="center" data-valign="middle" data-width="150px">运输信息</th>
								<th data-field="cr" data-colspan="9" data-align="center" data-valign="middle" data-width="1500px">路上消费</th>
								<th data-field="lhc" data-colspan="1" data-align="center" data-valign="middle" data-width="150px"></th>
								<th data-field="lhc" data-colspan="1" data-align="center" data-valign="middle" data-width="150px"></th>
								<th data-field="lhc" data-colspan="1" data-align="center" data-valign="middle" data-width="150px"></th>
								<th data-field="fi" data-colspan="1" data-align="center" data-valign="middle" data-width="150px">运费收入</th>
								<th data-field="by" data-colspan="1" data-align="center" data-valign="middle" data-width="100px">收益</th>
								<th data-field="pr" data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
							</tr>
							<tr>
								<th data-field="state" data-checkbox="true" data-width="100px"></th>
								<th data-field="fzsj" data-align="center" data-valign="middle"
									data-footer-formatter="totalTextFormatter">
									负责司机
								</th>
								<th data-field="cph" data-align="center" data-valign="middle">车牌号</th>
								<th data-field="cx" data-align="center" data-valign="middle">车型</th>
								<th data-field="ccsj" data-align="center" data-valign="middle">出车时间</th>
								<th data-field="wcsj" data-align="center" data-valign="middle">完成时间</th>
								<th data-field="yszds" data-align="center" data-valign="middle" data-sortable="true"
									>运输总吨数（t）
								</th>
								<th data-field="yzzje" data-align="center" data-valign="middle" data-sortable="true">
									预支总金额（元）
								</th>
								<th data-field="jyzss" data-align="center" data-valign="middle" data-sortable="true"
									>加油总升数（L）
								</th>
								<th data-field="jqzs" data-align="center" data-valign="middle" data-sortable="true"
									>加气总数（公斤）
								</th>
								<th data-field="yqje" data-align="center" data-valign="middle" data-sortable="true"
									>油气金额（元）
								</th>
								<th data-field="glzcs" data-align="center" data-valign="middle" data-sortable="true"
									>过路总次数（次）
								</th>
								<th data-field="glzfy" data-align="center" data-valign="middle" data-sortable="true"
									>过路总费用（元）
								</th>
								<th data-field="wxzje" data-align="center" data-valign="middle" data-sortable="true"
									>维修总金额（元）
								</th>
								<th data-field="byzje" data-align="center" data-valign="middle" data-sortable="true"
									>保养总金额（元）
								</th>
								<th data-field="qtfy" data-align="center" data-valign="middle" data-sortable="true"
									>其他总费用
								</th>
								<th data-field="zsx" data-align="center" data-valign="middle" data-sortable="true"
									>总时效（小时）
								</th>
								<th data-field="zlc" data-align="center" data-valign="middle" data-sortable="true"
									 >总里程（公里）
								</th>
								<th data-field="xfzje" data-align="center" data-valign="middle" data-sortable="true"
									>消费总金额（元）
								</th>
								<th data-field="zyf" data-align="center" data-valign="middle" data-sortable="true"
									>总运费（元）
								</th>
								<th data-field="zsy" data-align="center" data-valign="middle" data-sortable="true"
									>总收益
								</th>
								<th data-field="bz" data-align="center" data-valign="middle">备注</th>
							</tr>
							</thead>
						</table>
						<div class="form-inline" align="center" style="margin: -45px auto 17px;">
							<input id="truckOperation_page" class="form-control" style="width: 60px;" type="number"
								   value="1" min="1">
							<button id="truckOperation_goBtn" class="btn btn-success">GO</button>
						</div>

					</div>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 财务运营报表-->
		<div class="modal fade bs-example-modal-lg" id="financialOperationManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal" aria-label="Close">&nbsp;<span aria-hidden="true">&times;</span>&nbsp;</button>
				   <button isClick="true" onclick="fullScreen(this)" title="放大" type="button" class="close financialFullScreen">&nbsp;
					   <span class="glyphicon glyphicon-resize-full"></span>&nbsp;
				   </button>
				   <h4 class="modal-title" >财务运营报表</h4>
		      </div>
		      <div class="modal-body">
	      		<form  class="form-inline">
						<div class="form-group">
							<label>日期：</label>
							<div class="display_inlineBlcok equipmentStatics">
								<input class="form_datetime form-control"id="financial_beginTime" type="text" size="16"/>
								至
								<input class="form_datetime form-control"id="financial_endTime" type="text" size="16"/>
								<p></p>
							</div>
						</div>
						<div class="QuerytheReset">
							<a type="button" class="btn query" onclick="financial_searchInfo();">查询</a>
							<a type="button" class="btn reset" onclick="financial_resetTime();">重置</a>
						</div>
					</form>
					<div class="buttons">
						<a type="button" class="btn btn_export" onclick="financialOperation_export()">
							导出
						</a>
					</div>
					<div class="col-md-12">
						<table data-toggle="table" class="table table-striped table-bordered table-hover"
							   id="financialOperationTable"
							   data-query-params="financialQueryParams"
							   data-query-params-type="limit"
							   data-pagination="true"
							   data-pagination-first-text="首页"
							   data-pagination-pre-text="上一页"
							   data-pagination-next-text="下一页"
							   data-pagination-last-text="末页"
							   data-row-style="rowStyle"
							   data-page-list="[10, 20,30]"
							   data-smart-display="false"
							   data-height="100">
							<thead style="background-color:#dbfff2;">
							<tr>
								<th data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
								<th data-field="xh" data-rowspan="2" data-colspan="1" data-align="center" data-valign="middle"
									data-width="100px">
									序号
								</th>
								<th data-colspan="1" data-align="center" data-valign="middle"data-width="100px"></th>
								<th data-colspan="1" data-align="center" data-valign="middle"data-width="100px"></th>
								<th data-colspan="6" data-align="center" data-valign="middle"data-width="600px">本期收入</th>
								<th data-colspan="11" data-align="center" data-valign="middle"data-width="1100px">本期支出</th>
								<th data-colspan="1" data-align="center" data-valign="middle"data-width="100px"></th>
								<th data-colspan="1" data-align="center" data-valign="middle"data-width="100px"></th>
							</tr>
							<tr>
								<th data-field="state" data-checkbox="true" data-width="100px"></th>
								<th data-field="rq" data-align="center" data-valign="middle"
									data-footer-formatter="totalTextFormatter">
									<p style="width: 120px"></p>日期
								</th>
								<th data-field="srye" data-align="center" data-valign="middle">期初余额</th>
								<th data-field="sryf" data-align="center" data-valign="middle" data-sortable="true"
									>运费（元）
								</th>
								<th data-field="srbx" data-align="center" data-valign="middle"
									>
									保险（年）
								</th>
								<th data-field="srcc" data-align="center" data-valign="middle"
									>
									仓储（年）
								</th>
								<th data-field="srzx" data-align="center" data-valign="middle"
									>
									装卸（年）
								</th>
								<th data-field="srtc" data-align="center" data-valign="middle"
									>
									停车场（年）
								</th>
								<th data-field="srhj" data-align="center" data-valign="middle"
									><font
										color="red">本期收入合计</font></th>
								<th data-field="zcrl" data-align="center" data-valign="middle"
									>
									燃料款
								</th>
								<th data-field="zcwx" data-align="center" data-valign="middle"
									>
									维修
								</th>
								<th data-field="zcby" data-align="center" data-valign="middle"
									>
									保养
								</th>
								<th data-field="zcbx" data-align="center" data-valign="middle"
									>
									保险
								</th>
								<th data-field="zcgl" data-align="center" data-valign="middle"
									>
									过路
								</th>
								<th data-field="zcxc1" data-align="center" data-valign="middle"
									>
									洗车
								</th>
								<th data-field="zczc" data-align="center" data-valign="middle"
									>
									装车费
								</th>
								<th data-field="zcxc" data-align="center" data-valign="middle"
									>
									卸车费
								</th>
								<th data-field="zcsh" data-align="center" data-valign="middle"
									>
									设备损耗
								</th>
								<th data-field="zcrg" data-align="center" data-valign="middle"
									>
									人工费用
								</th>
								<th data-field="zcdb" data-align="center" data-valign="middle"
									>
									地磅费
								</th>
								<th data-field="zchj" data-align="center" data-valign="middle"><font color="red"
																									 >
									本期支出合计</font></th>
								<th data-field="shloc" data-align="center" data-valign="middle">
									期末余额
								</th>
							</tr>
							</thead>
						</table>
						<div class="form-inline" align="center" style="margin: -45px auto 17px;">
							<input id="financial_page" class="form-control" style="width: 60px;" type="number" value="1"
								   min="1">
							<button id="financial_goBtn" class="btn btn-success">GO</button>
						</div>
					</div>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 平台结算表-->
		<div class="modal fade bs-example-modal-lg" id="platformAndSettlementManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal" aria-label="Close">&nbsp;<span aria-hidden="true">&times;</span>&nbsp;</button>
				   <button isClick="true" onclick="fullScreen(this)" title="放大" type="button" class="close platFullScreen">&nbsp;
					   <span class="glyphicon glyphicon-resize-full"></span>&nbsp;
				   </button>
				   <h4 class="modal-title" >平台结算表</h4>
		      </div>
		      <div class="modal-body">
	      		<form  class="form-inline">
	      			<h4>
	      				结算单汇总表
	      			</h4>
					<div class="form-group">
						<label>日期：</label>
						<div class="display_inlineBlcok equipmentStatics">
							<input  class="form_datetime form-control"id="plat_beginTime" type="text" size="16" />
							至
							<input class="form_datetime form-control"id="plat_endTime" type="text" size="16"/>
							<p></p>
						</div>
					</div>
					<div class="QuerytheReset">
						<a type="button" class="btn query" onclick="plat_searchInfo();">查询</a>
						<a type="button" class="btn reset" onclick="plat_resetTime();">重置</a>
					</div>
				</form>
				<div class="buttons">
					<a type="button" class="btn btn_export">
						导出
					</a>
				</div>
				<div>
					<%--结算单汇总表--%>
					<table data-toggle="table" class="table table-striped table-bordered table-hover"
						   id="platformSettlementSummaryTable"
						   data-url="/iwuliu/reportManager/platformSettlementList"
						   data-query-params="platformSettlementQueryParams"
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
							<th data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
							<th data-colspan="9" data-align="center" data-valign="middle" data-width="1500px">结算单汇总表</th>
						</tr>
						<tr>
							<th data-field="state" data-checkbox="true" data-width="100px"></th>
							<th data-field="rq" data-align="center" data-valign="middle"
								data-footer-formatter="totalTextFormatter">
								结算日期
							</th>
							<th data-field="dd" data-align="center" data-valign="middle">订单数（单）</th>
							<th data-field="zc" data-align="center" data-valign="middle" data-sortable="true"
								>
								使用总车数
							</th>
							<th data-field="ys" data-align="center" data-valign="middle"
								>
								运输总吨数
							</th>
							<th data-field="je" data-align="center" data-valign="middle"
								>
								金额（元）
							</th>
							<th data-field="yz" data-align="center" data-valign="middle"
								>
								预支总金额（元）
							</th>
							<th data-field="zr" data-align="center" data-valign="middle"
								>
								责任险总金额
							</th>
							<th data-field="js" data-align="center" data-valign="middle"
								>
								结算总金额（元）
							</th>
							<th data-align="center" data-valign="middle" data-formatter="operationFormatter">操作</th>
						</tr>
						</thead>
					</table>
					<div class="form-inline" align="center" style="margin: -45px auto 17px;">
						<input id="plat_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
						<button id="plat_goBtn" class="btn btn-success">GO</button>
					</div>
				</div>
				<form  class="form-inline">
	      			<h4>
	      				结算单明细表
	      			</h4>
					<div class="form-group">
						<label>
							结算单号:
						</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" id="settlementNum"/>
						</div>
					</div>
					<div class="form-group">
						<label>
							物品名称:
						</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" id="itemName"/>
						</div>
					</div>
					<div class="form-group">
						<label>日期：</label>
						<div class="display_inlineBlcok equipmentStatics">
							<input  class="form_datetime form-control"id="begin_time_detail" type="text" size="16" />
							至
							<input class="form_datetime form-control"id="end_time_detail" type="text" size="16"/>
							<p></p>
						</div>
					</div>
					<div class="QuerytheReset">
						<a type="button" class="btn query" onclick="settlementDetail_searchInfo();">查询</a>
						<a type="button" class="btn reset" onclick="settlementDetail_resetTime();">重置</a>
					</div>
				</form>
				<div class="buttons">
					<a type="button" class="btn btn_export">
						导出
					</a>
				</div>
				<div>
					<%--结算单明细表--%>
					<table data-toggle="table" class="table table-striped table-bordered table-hover"
						   id="settlementDetailTable"
						   data-query-params="querySettlementDetailParams"
						   data-query-params-type="limit"
						   data-pagination="true"
						   data-pagination-first-text="首页"
						   data-pagination-pre-text="上一页"
						   data-pagination-next-text="下一页"
						   data-pagination-last-text="末页"
						   data-row-style="rowStyle"
						   data-page-list="[10, 20,30]"
						   data-smart-display="false"
						   data-height="100">
						<thead style="background-color:#dbfff2;">
						<tr>
							<th data-field="by" data-colspan="1" data-align="center" data-valign="middle"data-width="100px" ></th>
							<th data-field="by" data-colspan="13" data-align="center"
								data-valign="middle" data-width="1500px">结算单明细表
							</th>
						</tr>
						<tr>
							<th data-field="state" data-checkbox="true" data-width="100px"></th>
							<th data-field="js" data-align="center" data-valign="middle">结算单号</th>
							<th data-field="dd" data-align="center" data-valign="middle"
								data-formatter="orderFormatter">订单号
							</th>
							<th data-field="ds" data-align="center" data-valign="middle" data-sortable="true">
								订单时间
							</th>
							<th data-field="mc" data-align="center" data-valign="middle">物品名称</th>
							<th data-field="zl" data-align="center" data-valign="middle">物品种类</th>
							<th data-field="ms" data-align="center" data-valign="middle">物品描述</th>
							<th data-field="cs" data-align="center" data-valign="middle">使用总车数（台）</th>
							<th data-field="ys" data-align="center" data-valign="middle">运输吨位（吨）</th>
							<th data-field="je1" data-align="center" data-valign="middle">金额（元）</th>
							<th data-field="yz" data-align="center" data-valign="middle">预支金额（元）</th>
							<th data-field="je" data-align="center" data-valign="middle">结算金额（元）</th>
							<th data-field="fp" data-align="center" data-valign="middle"><font
									color="red">发票状态</font></th>
							<th data-field="zt" data-align="center" data-valign="middle">状态</th>
						</tr>
						</thead>
					</table>
					<div class="form-inline" align="center" style="margin: -45px auto 17px;">
						<input id="settlementDetail_page" class="form-control" style="width: 60px;" type="number"
							   value="1" min="1">
						<button id="settlementDetail_goBtn" class="btn btn-success">GO</button>
					</div>
				</div>
				  <h4>
					  派单单明细表
				  </h4>
				   <div class="buttons">
					  <a type="button" class="btn btn_export">
						  导出
					  </a>
				  </div>
				  <div class="col-md-12">
					  <%--派单明细表--%>
					  <table data-toggle="table" class="table table-striped table-bordered table-hover"
							 id="dispatchDetailTable"
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
							  <th data-field="by" data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
							  <th data-field="by" data-colspan="13" data-align="center" data-valign="middle" data-width="1500px">派单明细表</th>
						  </tr>
						  <tr>
							  <th data-field="state" data-checkbox="true"></th>
							  <th data-field="dd" data-align="center" data-valign="middle">订单号</th>
							  <th data-field="pd" data-align="center" data-valign="middle">派单号</th>
							  <th data-field="pc" data-align="center" data-valign="middle" data-sortable="true">派车时间</th>
							  <th data-field="xc" data-align="center" data-valign="middle">卸车时间</th>
							  <th data-field="cp" data-align="center" data-valign="middle">车牌号</th>
							  <th data-field="gb" data-align="center" data-valign="middle">车辆过磅数（次）</th>
							  <th data-field="sj" data-align="center" data-valign="middle">行驶司机</th>
							  <th data-field="ys" data-align="center" data-valign="middle">运输吨位（吨）</th>
							  <th data-field="je" data-align="center" data-valign="middle">金额（元）</th>
							  <th data-field="yz" data-align="center" data-valign="middle">预支金额（元）</th>
							  <th data-field="js" data-align="center" data-valign="middle">结算金额（元）</th>
							  <th data-field="fp" data-align="center" data-valign="middle"><font
									  color="red">发票状态</font></th>
							  <th data-field="zt" data-align="center" data-valign="middle">状态</th>
						  </tr>
						  </thead>
					  </table>
					  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
						  <input id="dispatchDetail_page" class="form-control" style="width: 60px;" type="number"
								 value="1" min="1">
						  <button id="dispatchDetail_goBtn" class="btn btn-success">GO</button>
					  </div>
				  </div>
			  </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 用户运营报表-->
		<div class="modal fade bs-example-modal-lg" id="userOperationManager" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal" aria-label="Close">&nbsp;<span aria-hidden="true">&times;</span>&nbsp;</button>
				   <button isClick="true" onclick="fullScreen(this)" title="放大" type="button" class="close userFullScreen">&nbsp;
					   <span class="glyphicon glyphicon-resize-full"></span>&nbsp;
				   </button>
				   <h4 class="modal-title" >用户运营报表</h4>
		      </div>
		      <div class="modal-body">
	      		<form  class="form-inline">
						<div class="form-group">
							<label>日期：</label>
							<div class="display_inlineBlcok equipmentStatics">
								<input class="form_datetime form-control"id="use_beginTime" type="text" size="16"/>
								至
								<input class="form_datetime form-control"id="use_endTime" type="text" size="16" />
								<p></p>
							</div>
						</div>
						<div class="QuerytheReset">
							<a type="button" class="btn query" onclick="user_searchInfo();">查询</a>
							<a type="button" class="btn reset" onclick="user_resetTime();">重置</a>
						</div>
					</form>
					<div class="buttons">
						<a type="button" class="btn btn_export" onclick="userOperation_export();">
							导出
						</a>
					</div>
					<div class="col-md-12">
						<table data-toggle="table" class="table table-striped table-bordered table-hover"
							   id="userOperationTable"
							   data-query-params="queryUserOperationParams"
							   data-query-params-type="limit"
							   data-pagination="true"
							   data-pagination-first-text="首页"
							   data-pagination-pre-text="上一页"
							   data-pagination-next-text="下一页"
							   data-pagination-last-text="末页"
							   data-row-style="rowStyle"
							   data-page-list="[10,20,30]"
							   data-smart-display="false"
						>
							<thead style="background-color:#dbfff2;">
							<tr>
								<th data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
								<th data-colspan="1" data-align="center" data-valign="middle" data-width="100px"></th>
								<th data-colspan="5" data-align="center" data-valign="middle" data-width="600px">司机用户</th>
								<th data-colspan="7" data-align="center" data-valign="middle" data-width="800px">车主</th>
								<th data-colspan="4" data-align="center" data-valign="middle" data-width="500px">货主</th>
							</tr>
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="rq" data-align="center" data-valign="middle" data-sortable="true">
									日期
								</th>
								<th data-field="sjzc" data-align="center" data-valign="middle">注册用户（人）</th>
								<th data-field="sjls" data-align="center" data-valign="middle">流失用户（人）</th>
								<th data-field="sjhy" data-align="center" data-valign="middle">活跃用户（人）</th>
								<th data-field="sjsd" data-align="center" data-valign="middle">锁定用户（人）</th>
								<th data-field="sjtg" data-align="center" data-valign="middle">推广转化率</th>
								<th data-field="czzc" data-align="center" data-valign="middle">注册用户（人）</th>
								<th data-field="czls" data-align="center" data-valign="middle">流失用户（人）</th>
								<th data-field="czhy" data-align="center" data-valign="middle">活跃用户（人）</th>
								<th data-field="czsd" data-align="center" data-valign="middle">锁定用户（人）</th>
								<th data-field="cztg" data-align="center" data-valign="middle">推广转化率</th>
								<th data-field="czyl" data-align="center" data-valign="middle">车主盈利（元）</th>
								<th data-field="czxf" data-align="center" data-valign="middle">车主消费（元）</th>
								<th data-field="hzzc" data-align="center" data-valign="middle">注册用户（人）</th>
								<th data-field="hzls" data-align="center" data-valign="middle">流失用户（人）</th>
								<th data-field="hzjy" data-align="center" data-valign="middle">货主交易（元）</th>
								<th data-field="hzsd" data-align="center" data-valign="middle">锁定用户（人）</th>
							</tr>
							</thead>
						</table>
						<div class="form-inline" align="center" style="margin: -45px auto 17px;">
							<input id="user_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
							<button id="user_goBtn" class="btn btn-success">GO</button>
						</div>
					</div>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
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
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.form.js" type="text/javascript" charset="utf-8">
	</script>

	<script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/userOperationManager.js"
			type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/vehicleOperationManager.js"
			type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/financialOperationManager.js"
			type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/drivingBehaviorManager.js"
			type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/reportManager/js/platformAndSettlementManager.js"
			type="text/javascript" charset="utf-8"></script>
	<%--common.js的引用放在自己单独引用的js下面--%>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
</html>