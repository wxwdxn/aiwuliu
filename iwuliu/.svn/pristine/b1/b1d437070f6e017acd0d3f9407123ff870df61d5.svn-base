<%--
Created by IntelliJ IDEA.
User: WXW
Date: 2017/03/5
Time: 14:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<link rel="stylesheet"
		  href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/schedulingOrderManager/css/schedulingOrderManager.css"/>
	<%--Bootstrap时间控件  JavaScript 文件以及汉化文件--%>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.min.js" type="text/javascript"
			charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/schedulingOrderManager/js/schedulingOrderManager.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>

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
						历史运单查询
					</h4>
					<div class="infoConditions">
						<form class="form-inline">
							<div class="form-group">
								<label>运单号：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="schedulePaln" />
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>车牌号：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="plateNumber" />
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>车辆型号：</label>
    							<div class="display_inlineBlcok truckType">
	    							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder" value="">请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

									</ul>
    							</div>
							</div>
							<div class="form-group">
								<label>选择干线：</label>
    							<div class="display_inlineBlcok mainLine">
	    							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder" value="">请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									</ul>
    							</div>
							</div>
							<div class="form-group">
								<label>接单起止时间：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="startTime"/>至
									<input type="text" class="form-control" id="endTime" />
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>司机：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="person"/>
									<p></p>
								</div>
							</div>
							<div class="form-group">
								<label>司机手机：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="personPhone" />
									<p></p>
								</div>
							</div>
							<div class="QuerytheReset">
								<label class="sr-only">查询按钮</label>
								<div class="display_inlineBlcok">
									<a type="button" class="btn query" onclick="searchScheduleOrder()">查询</a>
									<a type="button" class="btn reset" onclick="resetSchedule()">重置</a>
								</div>
							</div>
						</form>
						
						<div class="buttons">
							<a type="button" class="btn btn_export">
								导出
							</a>
							<a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
								详情
							</a>
						</div>
						<%--运单查询--%>
						<table data-toggle="table" class="table table-striped table-bordered table-hover"
							   id="scheduleOrderTable"
							   data-query-params="scheduleOrderTableParams"
							   data-query-params-type="limit"
							   data-pagination="true"
							   data-pagination-first-text="首页"
							   data-pagination-pre-text="上一页"
							   data-pagination-next-text="下一页"
							   data-pagination-last-text="末页"
							   data-show-footer="false"
							   data-row-style="rowStyle"
							   data-page-list="[10, 20,30]"
							   data-smart-display="false"
							   data-height="100">
							<thead style="background-color:#dbfff2;">
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="schedulingSheetId"  class="hidden" data-align="center" data-valign="middle">调度单Id</th>
								<th data-field="planName"  data-align="center" data-valign="middle">运单号
								</th>
								<th data-field="plateNumber" data-align="center" data-valign="middle">车牌号
								</th>
								<th data-field="lineName" data-align="center" data-valign="middle">选择线路
								</th>
								<th data-field="juli" data-align="center" data-valign="middle">总里程（公里）
								</th>
								<th data-field="modelName" data-align="center" data-valign="middle">车辆型号
								</th>
								<th data-field="cargoType" data-align="center" data-valign="middle">货物类型
								</th>
								<th data-field="total" data-align="center" data-valign="middle">卸货量
								</th>

								<th data-field="acceptTime" data-align="center" data-valign="middle">接单时间
								</th>
								<th data-field="personName" data-align="center" data-valign="middle">司机
								</th>
								<th data-field="personPhone" data-align="center" data-valign="middle">手机号
								</th>
							</tr>
							</thead>
						</table>
						<div class="form-inline" align="center" style="margin: -45px auto 17px;">
							<input id="scheduleOrderTablePage" class="form-control" style="width: 60px;" type="number"
								   value="1" min="1" addAttr="page">
							<button id="scheduleOrderTablePagegoBtn" class="btn btn-success">GO</button>
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
		        <h4 class="modal-title" id="myModalLabel3">详情</h4>
		      </div>
		      <div class="modal-body">
		      	<h4>调度单信息</h4>
		      	<form id="form-group" class="form-inline">
					<div class="form-group docubleUneditable">
						<label>运单号：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="planNumber" class="form-control" />
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>车辆型号：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="modelName" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>选择干线线路：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="lineName" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>调度数量：</label>
						<div class="display_inlineBlcok">
							<input disabled id="total" type="text" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>调度车辆车牌：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="truckNumber" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>接单时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="receveTime" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>出发时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="offTime" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货时货物数量：</label>
						<div class="display_inlineBlcok">
							<input disabled id="loadWeight" type="text" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货开始时间：</label>
						<div class="display_inlineBlcok">
							<input disabled id="loadStart" type="text" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货结束时间：</label>
						<div class="display_inlineBlcok">
							<input id="loadEnd" disabled type="text" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group formgroupImg">
						<label>装货过磅单：</label>
						<div class="display_inlineBlcok" id="loadPath">
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货位置：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="loadposition" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>卸货时货物数量：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="unloadWeight" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>卸货开始时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unloadStart" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>卸货结束时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unloadEnd" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group formgroupImg">
						<label>卸货过磅单：</label>
						<div class="display_inlineBlcok" id="unloadPath">
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>卸货位置：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unloadposition" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>运输单价（元/吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unitPrice" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>调度单运输状态：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="status" class="form-control"/>
							<p></p>
						</div>
					</div>
				</form>
				  <%--支出详情--%>
				  <table data-toggle="table" class="table table-striped table-bordered table-hover"
						 id=scheduleFlowTable
						 data-query-params="scheduleFlowTableParams"
						 data-query-params-type="limit"
						 data-pagination="true"
						 data-pagination-first-text="首页"
						 data-pagination-pre-text="上一页"
						 data-pagination-next-text="下一页"
						 data-pagination-last-text="末页"
						 data-show-footer="false"
						 data-row-style="rowStyle"
						 data-page-list="[10, 20,30]"
						 data-smart-display="false"
						 data-height="100">
					  <thead style="background-color:#dbfff2;">

					  <tr>
						  <th data-field="state" data-checkbox="true"></th>
						  <th  data-field="createTime"  data-align="center" data-valign="middle" width="0">
							  支付时间
						  </th>
						  <th data-field="flowType" data-align="center" data-valign="middle" >
							  支出类型
						  </th>
						  <th data-field="stationId" data-align="center" data-valign="middle">支出商户</th>
						  <th data-field="amount" data-align="center" data-valign="middle">金额</th>
						  <th data-field="createTime2" data-align="center" data-valign="middle">流水产生时间</th>
						  <th data-field="path" data-align="center" data-valign="middle" class="img"  data-formatter="imgFormatter">支出凭证</th>
						  <%--<th data-field="longitude" data-align="center" data-valign="middle">经度</th>--%>
						  <th data-field="position" data-align="center" data-valign="middle">位置</th>
						  <th data-field="personId" data-align="center" data-valign="middle">支付人员</th>
					  </tr>
					  </thead>
				  </table>
				  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
					  <input id="scheduleFlowTablepage" class="form-control" style="width: 60px;" type="number"
							 value="1" min="1">
					  <button id="scheduleFlowTablegoBtn" class="btn btn-success">GO</button>
				  </div>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn hold">保存</a>
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
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--Modal imgBig-->
		<div class="modal fade" id="ModalImg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
		        <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
		        <h4 class="modal-title" id="myModalLabel">图片信息</h4>
		      </div>
		      <div class="modal-body modalImg">
		      </div>
		    </div>
		  </div>
		</div>
	</body>
</html>