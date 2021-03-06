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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/transportPlan/css/transportPlan.css"/>
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
	<script src="<%=request.getContextPath()%>/static/operationManager/transportPlan/js/transportPlan.js" type="text/javascript" charset="utf-8"></script>
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
							可用计划运输列表
						</h4>
						<div class="buttons">
							<a type="button" class="btn btn_manuallyAssign" onclick="btn_manuallyAssign()">
								手工分配
							</a>
							<a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
								详情
							</a>
						</div>
						<div class="col-md-12" >
							<%--手动运输计划--%>
							<table data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="manualOrderTable"
								   data-url="/iwuliu/transportationOrderManager/manualOrderList"
								   data-query-params="manualOrderTableparams"
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
									<th data-field="planNumber" class="hidden" data-align="center" data-valign="middle">
										计划编号
									</th>
									<th data-field="cargo_type_id" data-align="center" data-valign="middle">货物类型</th>
									<th data-field="lineName" data-align="center" data-valign="middle">对应运营干线</th>
									<th data-field="loading_cargo_yard_id" data-align="center" data-valign="middle" data-sortable="true"
									>装货货场
									</th>
									<th data-field="unloading_cargo_yard_id" data-align="center" data-valign="middle" data-sortable="true"
									>卸货货场
									</th>
									<th data-field="price" data-align="center" data-valign="middle" data-sortable="true"
									>运输单价(元/吨)
									</th>
									<th data-field="unRedistributeTotal" data-align="center" data-valign="middle" data-sortable="true"
									>未分配数量(吨)
									</th>
									<th data-field="beginDate" data-align="center" data-valign="middle" data-sortable="true">
										装货开始时间
									</th>
									<th data-field="redistributeTotal" data-align="center" data-valign="middle" data-sortable="true"
									>手动分配的吨数
									</th>

								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="manualOrderTablepage" class="form-control" style="width: 60px;" type="number"
									   value="1" min="1">
								<button id="manualOrderTablegoBtn" class="btn btn-success">GO</button>
							</div>
						</div>
						<h4>
							不可用计划运输列表
						</h4>
						<div class="buttons">
							<a type="button" class="btn btn_detail" onclick="btn_UnModalDetail()">
								详情
							</a>
						</div>
						<div class="col-md-12" >
							<%--不可运输计划--%>
							<table data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="manualFinishedOrderTable"
								   data-url="/iwuliu/transportationOrderManager/manualFinishedOrderList"
								   data-query-params="manualOrderTableparams"
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
									<th data-field="planNumber" class="hidden" data-align="center" data-valign="middle">
										计划编号
									</th>
									<th data-field="cargo_type_id" data-align="center" data-valign="middle">货物类型</th>
									<th data-field="lineName" data-align="center" data-valign="middle">对应运营干线</th>
									<th data-field="loading_cargo_yard_id" data-align="center" data-valign="middle" data-sortable="true"
									>装货货场
									</th>
									<th data-field="unloading_cargo_yard_id" data-align="center" data-valign="middle" data-sortable="true"
									>卸货货场
									</th>
									<th data-field="price" data-align="center" data-valign="middle" data-sortable="true"
									>运输单价(元/吨)
									</th>
									<th data-field="unRedistributeTotal" data-align="center" data-valign="middle" data-sortable="true"
									>未分配数量(吨)
									</th>
									<th data-field="beginDate" data-align="center" data-valign="middle" data-sortable="true">
										装货开始时间
									</th>
									<th data-field="redistributeTotal" data-align="center" data-valign="middle" data-sortable="true"
									>手动分配的吨数
									</th>

								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="manualFinishedOrderTablepage" class="form-control" style="width: 60px;" type="number"
									   value="1" min="1">
								<button id="manualFinishedOrderTablegoBtn" class="btn btn-success">GO</button>
							</div>
						</div></div>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal 可用计划详情-->
		<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel1">详情</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="form-group1" class="form-inline">
					<div class="form-group docubleUneditable">
						<label>货物种类：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="cargo_type_id" />
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>对应干线：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="lineName"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货货场名称：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="loading_cargo_yard_id"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>运输单价（元/吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="price" />
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>开始时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="beginDate"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>未分配数量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="unRedistributeTotal" />
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>手动分配数量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="distributionState" />
							<p></p>
						</div>
					</div>
				</form>
		      	<h4>
		      		不可撤销车辆
		      	</h4>

					  <%--详情手动运输计划--%>
					  <table data-toggle="table" class="table table-striped table-bordered table-hover"
							 id="manualOrderTable3"
							 data-query-params="manualOrderTableparams"
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
							  <th data-field="plateNumber" data-align="center" data-valign="middle">车辆牌照
							  </th>
							  <th data-field="typeName" data-align="center" data-valign="middle">车厢类型
							  </th>
							  <th data-field="position" data-align="center" data-valign="middle" data-sortable="true"
							  >车辆当前位置

							  </th>
							  <th data-field="statusName" data-align="center" data-valign="middle" data-sortable="true"
							  >车辆目前运输状态

							  </th>
							  <th data-field="status" data-align="center" data-valign="middle" data-sortable="true"
							  >手动派单状态

							  </th>

						  </tr>
						  </thead>
					  </table>
					  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
						  <input id="manualOrderTablepage3" class="form-control" style="width: 60px;" type="number"
								 value="1" min="1">
						  <button id="manualOrderTablegoBtn3" class="btn btn-success">GO</button>
					  </div>

		      	<h4>
		      		可撤销车辆
		      	</h4>
		      	<div class="buttons">
		      		<a class="btn" onclick="cancelSheetDummy()">撤销派单</a>
		      	</div>

					  <%-- 详情手动未查看运输计划--%>
					  <table data-toggle="table" class="table table-striped table-bordered table-hover"
							 id="manualOrderTable4"
							 data-query-params="manualOrderTableparams"
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
							  <th data-field="planNumber" class="hidden" data-align="center" data-valign="middle">
							  </th>
							  <th data-field="plateNumber" data-align="center" data-valign="middle">车辆牌照
							  </th>
							  <th data-field="typeName" data-align="center" data-valign="middle">车厢类型
							  </th>
							  <th data-field="position" data-align="center" data-valign="middle" data-sortable="true"
							  >车辆当前位置

							  </th>
							  <th data-field="statusName" data-align="center" data-valign="middle" data-sortable="true"
							  >车辆目前运输状态

							  </th>
							  <th data-field="status" data-align="center" data-valign="middle" data-sortable="true"
							  >手动派单状态
							  </th>
						  </tr>
						  </thead>
					  </table>
					  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
						  <input id="manualOrderTablepage4" class="form-control" style="width: 60px;" type="number"
								 value="1" min="1">
						  <button id="manualOrderTablegoBtn4" class="btn btn-success">GO</button>
					  </div>

		      </div>
		      <div class="modal-footer modalFooter hidden">
		        <a type="button" class="btn hold">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Modal 可用计划手动分配-->
		<div class="modal fade bs-example-modal-lg" id="mymanuallyAssign" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabe3">手动分配</h4>
		      </div>
		      <div class="modal-body">
		      	<form id="form-group" class="form-inline">
					<div class="form-group docubleUneditable">
						<label>货物种类：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="goodType"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>对应干线：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="mainLineName"class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货货场名称：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="loadCargoId" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>卸货货场名称：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unloading_cargo_yard_id"  class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>未分配数量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unRedistributeCount"  class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>运输单价（元/吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="unitprice"  class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>开始时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="startDate" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>车辆牌照：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" placeholder="请输入车牌号" id="plate_number"onblur="searchInfo() "/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车厢类型：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="typeName" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆当前位置：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="position" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>车辆目前运输状态：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="status" class="form-control"/>
							<p></p>
						</div>
					</div>
				</form>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn hold" onclick="addScheduleSheetDummy()">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Modal 不可用计划详情-->
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
						<label>货物种类：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="unGoodType"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group docubleUneditable">
						<label>对应干线：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="unLine"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>装货货场名称：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="unload"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group docubleUneditable">
						<label>未分配数量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="undisbuite"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>运输单价（元/吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="unPrice"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>开始时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="unStart" />
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>手动分配数量（吨）：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="total" />
							<p></p>
						</div>
					</div>
				</form>
		      	<h4>
		      		不可撤销车辆
		      	</h4>
					  <%--手动运输计划--%>
					  <table data-toggle="table" class="table table-striped table-bordered table-hover"
							 id="manualFinishOrderTable"
							 data-query-params="manualOrderTableparams"
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

							  <th data-field="plateNumber" data-align="center" data-valign="middle">车辆牌照
							  </th>
							  <th data-field="typeName" data-align="center" data-valign="middle">车厢类型
							  </th>
							  <th data-field="position" data-align="center" data-valign="middle" data-sortable="true"
							  >车辆当前位置

							  </th>
							  <th data-field="statusName" data-align="center" data-valign="middle" data-sortable="true"
							  >车辆目前运输状态

							  </th>
							  <th data-field="status" data-align="center" data-valign="middle" data-sortable="true"
							  >手动派单状态
							  </th>
						  </tr>
						  </thead>
					  </table>
					  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
						  <input id="manualFinishTablepage" class="form-control" style="width: 60px;" type="number"
								 value="1" min="1">
						  <button id="manualFinishTablegoBtn" class="btn btn-success">GO</button>
					  </div>
		      	<h4>
		      		可撤销车辆
		      	</h4>
		      	<div class="buttons">
		      		<a class="btn" onclick="deleteUnSheetDummy()">撤销派单</a>
		      	</div>
				  <%--手动未查看运输计划--%>
				  <table data-toggle="table" class="table table-striped table-bordered table-hover"
						 id="manualOrderTable2"
						 data-query-params="manualOrderTableparams"
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
						  <th data-field="planNumber" class="hidden" data-align="center" data-valign="middle">
						  </th>
						  <th data-field="plateNumber" data-align="center" data-valign="middle">车辆牌照
						  </th>
						  <th data-field="typeName" data-align="center" data-valign="middle">车厢类型
						  </th>
						  <th data-field="position" data-align="center" data-valign="middle" data-sortable="true"
						  >车辆当前位置

						  </th>
						  <th data-field="statusName" data-align="center" data-valign="middle" data-sortable="true"
						  >车辆目前运输状态

						  </th>
						  <th data-field="status" data-align="center" data-valign="middle" data-sortable="true"
						  >手动派单状态
						  </th>
					  </tr>
					  </thead>
				  </table>
				  <div class="form-inline" align="center" style="margin: -45px auto 17px;">
					  <input id="manualOrderTablepage2" class="form-control" style="width: 60px;" type="number"
							 value="1" min="1">
					  <button id="manualOrderTablegoBtn2" class="btn btn-success">GO</button>
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