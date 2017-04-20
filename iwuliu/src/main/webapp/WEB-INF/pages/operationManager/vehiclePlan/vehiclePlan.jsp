<%--
Created by IntelliJ IDEA.
User: WXW
Date: 2017/03/5
Time: 14:35
To change this template use File | Settings | File Templates.
--%>
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
	<link rel="stylesheet"
		  href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/vehiclePlan/css/vehiclePlan.css"/>
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
	<script src="<%=request.getContextPath()%>/static/operationManager/vehiclePlan/js/vehiclePlan.js" type="text/javascript" charset="utf-8"></script>
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
					<div class="infoConditions">
						<h4>
							可用车辆列表
						</h4>
						<div class="buttons">
							<a type="button" class="btn btn_manuallyAssign" onclick="btn_manuallyAssign()">
								手工分配
							</a>
							<a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
								详情/编辑
							</a>
						</div>
							<%--手动运输计划可用车辆列表--%>
							<table data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="manualTruckSheet"
								   data-url="/iwuliu/companyManager/manualTruckSheet"
								   data-query-params="manualTruckSheetparams"
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
									<th data-field="truckId" class="hidden" data-align="center" data-valign="middle">
										车辆id
									</th>
									<th data-field="flag"  class="hidden" data-align="center" data-valign="middle">
										标识
									</th>
									<th data-field="plateNumber" data-align="center" data-valign="middle">车辆牌照</th>
									<th data-field="position" data-align="center" data-valign="middle">车辆当前位置</th>
									<th data-field="statusName" data-align="center" data-valign="middle" data-sortable="true"
									>运输状态
									</th>
									<th data-field="loadWeight" data-align="center" data-valign="middle" data-sortable="true"
									>载重量（吨）
									</th>
								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="manualTruckSheetpage" class="form-control" style="width: 60px;" type="number"
									   value="1" min="1">
								<button id="manualTruckSheetgoBtn" class="btn btn-success">GO</button>
							</div>
					
						<h4>
							不可用车辆列表
						</h4>
						<div class="buttons">
							<a type="button" class="btn btn_detail" onclick="btn_UnModalDetail()">
								详情/编辑
							</a>
						</div>
						<%--手动运输计划不可用车辆列表--%>
						<table data-toggle="table" class="table table-striped table-bordered table-hover"
							   id="manualUnFinishedTruckSheet"
							   data-url="/iwuliu/companyManager/manualUnFinishedTruckSheet"
							   data-query-params="manualTruckSheetparams"
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
								<th data-field="truckId" class="hidden" data-align="center" data-valign="middle">
									车辆id
								</th>
								<th data-field="flag"  class="hidden" data-align="center" data-valign="middle">
									标识
								</th>
								<th data-field="plateNumber" data-align="center" data-valign="middle">车辆牌照</th>
								<th data-field="position" data-align="center" data-valign="middle">车辆当前位置</th>
								<th data-field="statusName" data-align="center" data-valign="middle" data-sortable="true"
								>运输状态
								</th>
								<th data-field="loadWeight" data-align="center" data-valign="middle" data-sortable="true"
								>载重量（吨）
								</th>
							</tr>
							</thead>
						</table>
						<div class="form-inline" align="center" style="margin: -45px auto 17px;">
							<input id="manualUnFinishedTruckSheetpage" class="form-control" style="width: 60px;" type="number"
								   value="1" min="1">
							<button id="manualUnFinishedTruckSheetgoBtn" class="btn btn-success">GO</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal 可用车辆详情-->
		<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel3">详情</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="form-group3" class="form-inline">
					<div class="form-group docubleUneditable">
						<label>车辆牌照：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="truckNumber" class="form-control" />
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆当前位置：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="truckPosition" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆目前运输状态：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="truckStatus" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>载重量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="truckLoadWeight" class="form-control" />
							<p></p>
						</div>
					</div>
					
				</form>
		      	<h4>
		      		不可撤销运输计划
		      	</h4>
				  <%--可用列表不可撤销运输计划--%>
				  <table data-toggle="table" class="table table-striped table-bordered table-hover"
						 id="manualTruckSheetTable"
						 data-query-params="manualTruckSheetTableparams"
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
						  <th data-field="schedulePlanNumber" data-align="center" data-width="120" data-valign="middle">计划编号
						  </th>
						  <th data-field="cargoTypeName" data-align="center"data-width="120" data-valign="middle">货物类型
						  </th>
						  <th data-field="lineName" data-align="center"data-width="120" data-valign="middle" data-sortable="true"
						  >对应干线
						  </th>
						  <th data-field="loadingName" data-align="center" data-width="120"data-valign="middle" data-sortable="true"
						  >装货货场
						  </th>
						  <th data-field="unloadingName" data-align="center"data-width="120" data-valign="middle" data-sortable="true"
						  >卸货货场
						  </th>
						  <th data-field="unRedistributeTotal" data-align="center"data-width="120" data-valign="middle" data-sortable="true"
						  >未分配数量
						  </th>
						  <th data-field="price" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >运输单价
						  </th>
						  <th data-field="beginDate" data-align="center" data-valign="middle"data-width="120" data-sortable="true"
						  >计划开始时间
						  </th>
						  <th data-field="lastUpdate" data-align="center" data-valign="middle"data-width="120" data-sortable="true"
						  >手动派单时间
						  </th>
						  <th data-field="status" data-align="center" data-width="120"data-valign="middle" data-sortable="true"
						  >手动派单状态
						  </th>

					  </tr>
					  </thead>
				  </table>
				  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
					  <input id="manualTruckSheetTablepage" class="form-control" style="width: 60px;" type="number"
							 value="1" min="1">
					  <button id="manualTruckSheetTablegoBtn" class="btn btn-success">GO</button>
				  </div>
		      	<h4>
		      		可撤销运输计划
		      	</h4>
		      	<div class="buttons">
		      		<a class="btn" onclick="cancelSheetDummy()">撤销派单</a>
		      	</div>
				  <%--可用列表  可撤销运输计划手动未查看运输计划--%>
				  <table data-toggle="table" class="table table-striped table-bordered table-hover"
						 id="manualTruckSheetTable2"
						 data-query-params="manualTruckSheetTableparams2"
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
						  <th data-field="planNumber" data-align="center" class="hidden" data-valign="middle">编号
						  </th>
						  <th data-field="schedulePlanNumber" data-align="center"  data-width="120"data-valign="middle">计划编号
						  </th>
						  <th data-field="cargoTypeName" data-align="center"data-width="120" data-valign="middle">货物类型
						  </th>
						  <th data-field="lineName" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >对应干线
						  </th>
						  <th data-field="loadingName" data-align="center" data-valign="middle"data-width="120" data-sortable="true"
						  >装货货场
						  </th>
						  <th data-field="unloadingName" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >卸货货场
						  </th>
						  <th data-field="unRedistributeTotal" data-align="center" data-width="120" data-valign="middle" data-sortable="true"
						  >未分配数量
						  </th>
						  <th data-field="price" data-align="center" data-valign="middle"data-width="120" data-sortable="true"
						  >运输单价
						  </th>
						  <th data-field="beginDate" data-align="center" data-valign="middle"data-width="120" data-sortable="true"
						  >计划开始时间
						  </th>
						  <th data-field="lastUpdate" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >手动派单时间
						  </th>
						  <th data-field="status" data-align="center" data-valign="middle"data-width="120" data-sortable="true"
						  >手动派单状态
						  </th>
					  </tr>
					  </thead>
				  </table>
				  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
					  <input id="manualTruckSheetTablepage2" class="form-control" style="width: 60px;" type="number"
							 value="1" min="1">
					  <button id="manualTruckSheetTablegoBtn2" class="btn btn-success">GO</button>
				  </div>
		      </div>
		      <div class="modal-footer modalFooter hidden">
		        <a type="button" class="btn hold">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Modal 可用车辆手动分配-->
		<div class="modal fade bs-example-modal-lg" id="mymanuallyAssign" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel2">手动分配</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="form-grou2 vehiclePlanManuallyAssign" class="form-inline">
					<div class="form-group docubleUneditable">
						<label>车辆牌照：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="plateNumber"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆当前位置：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="position" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆目前运输状态：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="status" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>载重量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="loadWeight" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>运输计划编号：</label>
						<div class="display_inlineBlcok tranPlan">
							<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							   	<span class="placeHolder">请选择</span>
							   	<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>货物种类：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="cargoType"  class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>对应干线：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="lineName" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货货场名称：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="loadCargo" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>卸货货场名称：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="unloadCargo"  class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>未分配数量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="undistributedAmount" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>运输单价（元/吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"  id="price"class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>开始时间：</label>
						<div class="display_inlineBlcok">
							<input disabled id="startTime" type="text" class="form-control"/>
							<p></p>
						</div>
					</div>
				</form>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn hold addTruckSheet">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Modal 不可用车辆详情-->
		<div class="modal fade bs-example-modal-lg" id="UnmyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">详情</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="form-group" class="form-inline">
					<div class="form-group docubleUneditable">
						<label>车辆牌照：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="carNumber"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆当前位置：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="carPosition" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆目前运输状态：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="carStatus" class="form-control"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group docubleUneditable">
						<label>载重量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="carLoadWeight" class="form-control" />
							<p></p>
						</div>
					</div>
					
				</form>
		      	<h4>
		      		不可撤销运输计划
		      	</h4>
				  <%--手动运输计划--%>
				  <table data-toggle="table" class="table table-striped table-bordered table-hover"
						 id="manualTruckSheetTable5"
						 data-query-params="manualTruckSheetTableparams"
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
						  <th data-field="schedulePlanNumber" data-align="center" data-valign="middle">计划编号
						  </th>
						  <th data-field="cargoTypeName" data-align="center" data-width="120"data-valign="middle">货物类型
						  </th>
						  <th data-field="lineName" data-align="center" data-width="120"data-valign="middle" data-sortable="true"
						  >对应干线
						  </th>
						  <th data-field="loadingName" data-align="center" data-width="120"data-valign="middle" data-sortable="true"
						  >装货货场
						  </th>
						  <th data-field="unloadingName" data-align="center" data-width="120"data-valign="middle" data-sortable="true"
						  >卸货货场
						  </th>
						  <th data-field="unRedistributeTotal" data-align="center" data-width="120"data-valign="middle" data-sortable="true"
						  >未分配数量
						  </th>
						  <th data-field="price" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >运输单价
						  </th>
						  <th data-field="beginDate" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >计划开始时间
						  </th>
						  <th data-field="lastUpdate" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >手动派单时间
						  </th>
						  <th data-field="status" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >手动派单状态
						  </th>

					  </tr>
					  </thead>
				  </table>
				  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
					  <input id="manualTruckSheetTablepage5" class="form-control" style="width: 60px;" type="number"
							 value="1" min="1">
					  <button id="manualTruckSheetTablegoBtn5" class="btn btn-success">GO</button>
				  </div>
		      	<h4>
		      		可撤销运输计划
		      	</h4>
		      	<div class="buttons">
		      		<a class="btn"  onclick="cancelUnSheetDummy()">撤销派单</a>
		      	</div>
				  <%--手动未查看运输计划--%>
				  <table data-toggle="table" class="table table-striped table-bordered table-hover"
						 id="manualTruckSheetTable6"
						 data-query-params="manualTruckSheetTableparams"
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
						  <th data-field="planNumber" data-align="center" class="hidden" data-valign="middle">编号
						  </th>
						  <th data-field="schedulePlanNumber" data-align="center" data-width="120" data-valign="middle">计划编号
						  </th>
						  <th data-field="cargoTypeName" data-align="center" data-width="120"data-valign="middle">货物类型
						  </th>
						  <th data-field="lineName" data-align="center"data-width="120" data-valign="middle" data-sortable="true"
						  >对应干线
						  </th>
						  <th data-field="loadingName" data-align="center"data-width="120" data-valign="middle" data-sortable="true"
						  >装货货场
						  </th>
						  <th data-field="unloadingName" data-align="center"data-width="120" data-valign="middle" data-sortable="true"
						  >卸货货场
						  </th>
						  <th data-field="unRedistributeTotal" data-align="center" data-width="120"data-valign="middle" data-sortable="true"
						  >未分配数量
						  </th>
						  <th data-field="price" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >运输单价
						  </th>
						  <th data-field="beginDate" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >计划开始时间
						  </th>
						  <th data-field="lastUpdate" data-align="center" data-valign="middle" data-width="120"data-sortable="true"
						  >手动派单时间
						  </th>
						  <th data-field="status" data-align="center" data-valign="middle"data-width="120" data-sortable="true"
						  >手动派单状态
						  </th>
					  </tr>
					  </thead>
				  </table>
				  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
					  <input id="manualTruckSheetTablepage6" class="form-control" style="width: 60px;" type="number"
							 value="1" min="1">
					  <button id="manualTruckSheetTablegoBtn6" class="btn btn-success">GO</button>
				  </div>
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
						<h3 class="titleInfo">

						</h3>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--删除Modal 小提示框-->
		<div class="modal fade bs-example-modal-sm" id="smallModalInfoDelete" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">提示信息</h4>
					</div>
					<div class="modal-body text-center">
						<h2 class="titleInfoDelete">

						</h2>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-default deleteTruckType" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>