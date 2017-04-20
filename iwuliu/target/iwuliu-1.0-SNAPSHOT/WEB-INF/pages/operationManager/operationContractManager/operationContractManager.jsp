<%--
  Created by IntelliJ IDEA.
  User: WXW
  Date: 2017/2/23
  Time: 15:22
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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/operationContractManager/css/operationContractManager.css"/>
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
	<script src="<%=request.getContextPath()%>/static/operationManager/operationContractManager/js/operationContractManager.js" type="text/javascript" charset="utf-8"></script>
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
						合同管理
					</h4>
					<div class="infoConditions">
						<div class="buttons">
							<a class="btn" onclick="newAddContract()">
								新增单一合同
							</a>
							<a type="button" class="btn btn_export">
								导出
							</a>
						</div>
						<div class="col-md-12">
							<%--运输合同--%>
							<table data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="transportationContractTable"
								   data-url="/iwuliu/operationContractManager/transportationContractList"
								   data-query-params="transportationContractParams"
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
									<th class="hidden" data-field="contract_id"  data-align="center" data-valign="middle" width="0">
										合同ID
									</th>
									<th data-field="code" data-align="center" data-valign="middle" data-formatter="codeFormatter" data-sortable="true">
										合同编号
									</th>
									<th data-field="first_party_relevance_info_id" data-align="center" data-valign="middle" data-sortable="true">甲方姓名</th>
									<th data-field="cargo_type_id" data-align="center" data-valign="middle" data-sortable="true">货物类型</th>
									<th data-align="center" data-valign="middle" data-formatter="operationFormatter" >操作</th>
								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="transportationContractTablepage" class="form-control" style="width: 60px;" type="number"
									   value="1" min="1">
								<button id="transportationContractTablegoBtn" class="btn btn-success">GO</button>
							</div>
						</div>
						<div class="buttons">
							<a type="button" class="btn btn_export">
								导出
							</a>
						</div>
						<div class="col-md-12" >
							<%--运输计划--%>
							<table data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="transportationPlanTable"
								   data-query-params="transportationContractParams"
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
									<th data-field="code"  data-align="center" data-valign="middle" data-sortable="true">
										合同编号
									</th>
									<th data-field="name" data-align="center" data-valign="middle" data-sortable="true">货主</th>
									<th data-field="cargo_type_id" data-align="center" data-valign="middle" data-sortable="true">货物类型</th>
									<th data-field="lineName" data-align="center" data-valign="middle" data-sortable="true">运营干线</th>
									<th data-field="loading_cargo_yard_id" data-align="center" data-valign="middle" data-sortable="true"
									>装货货场
									</th>
									<th data-field="loading_begin_date" data-width="120px" data-align="center" data-valign="middle" data-sortable="true">
										装货开始时间
									</th>
									<th data-field="loading_contact_name" data-width="130px"data-align="center" data-valign="middle" data-sortable="true"
									>装货联系人姓名
									</th>
									<th data-field="loading_contact_phone" data-width="130px"data-align="center" data-valign="middle" data-sortable="true"
									>装货联系人电话
									</th>
									<th data-field="unloading_cargo_yard_id" data-align="center" data-valign="middle" data-sortable="true"
									>卸货货场
									</th>
									<th data-field="unloading_finish_date" data-width="120px"data-align="center" data-valign="middle" data-sortable="true"
									>卸货结束时间
									</th>
									<th data-field="unloading_contact_name"data-width="130px" data-align="center" data-valign="middle" data-sortable="true"
									>卸货联系人姓名
									</th>
									<th data-field="unloading_contact_phone" data-width="130px"data-align="center" data-valign="middle" data-sortable="true"
									>卸货联系人电话
									</th>
									<th data-field="transport_unit_price" data-width="130px"data-align="center" data-valign="middle" data-sortable="true"
									>运输单价(元/吨)
									</th>
									<th data-field="cargo_total" data-width="130px"data-align="center" data-valign="middle" data-sortable="true"
									>货物总数（吨）
									</th>
								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="transportationPlanTablepage" class="form-control" style="width: 60px;" type="number"
									   value="1" min="1">
								<button id="transportationPlanTablegoBtn" class="btn btn-success">GO</button>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!--Modal 新增合同页面-->
		<div class="modal fade bs-example-modal-lg" id="ModalContract" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modalDialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<a type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
						<h4 class="modal-title" id="myModalLabel3">新增单一合同</h4>
					</div>
					<div class="modal-body">
						<h4>合同信息<span class="red">*号为必填项</span></h4>
						<form class="form-inline">
							<div class="form-group">
								<label>合同编号:</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="code"/>
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>货物类型：</label>
								<div class="display_inlineBlcok cargoType">
									<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder" >请选择</span>
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									</ul>
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>货主类型：</label>
								<div class="display_inlineBlcok ownerType">
									<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder" >请选择</span>
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									</ul>
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>货主：</label>
								<div class="display_inlineBlcok owner">
									<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu7" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder" >请选择</span>
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									</ul>
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>结算方式：</label>
								<div class="display_inlineBlcok payType">
									<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder" >请选择</span>
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									</ul>
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>联系人：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" id="contact_name" />
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>联系电话：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control" onkeyup="loadPhone(this)" id="contact_phone" />
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>合同开始时间：</label>
								<div class="display_inlineBlcok">
									<input type="text" id="start_date" class="form-control start_date"  onchange="checkContractStarDate(this)" />
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="form-group">
								<label>合同终止时间：</label>
								<div class="display_inlineBlcok">
									<input type="text" id="end_date" class="form-control end_date"onchange="checkContractEndndDate(this)"  />
									<p></p>
								</div>
								<span class="red">*</span>
							</div>
							<div class="hidden first_routerInfo statics">
								<h4>&nbsp;&nbsp;
									<span onclick="addContractRouter(this)" class="glyphicon glyphicon-plus"></span>
									<span onclick="removeContractRouter(this)" class="glyphicon glyphicon-minus"></span>
									干线信息
								</h4>
								<div class="form-group">
									<label>干线信息：</label>
									<div class="display_inlineBlcok mainLine" >
										<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											<span class="placeHolder" addAttr="operate_main_line_id" >请选择</span>
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" >
										</ul>
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>装货货场：</label>
									<div class="display_inlineBlcok loadFreightYard">
										<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											<span class="placeHolder" addAttr="loading_cargo_yard_id">请选择</span>
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										</ul>
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>卸货货场：</label>
									<div class="display_inlineBlcok unloadFreightYard">
										<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											<span class="placeHolder" addAttr="unloading_cargo_yard_id">请选择</span>
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										</ul>
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>装货开始时间：</label>
									<div class="display_inlineBlcok">
										<input type="text" class="form-control startDate"  addAttr="loading_begin_date" onchange="checkStarDate(this)" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>装货联系人：</label>
									<div class="display_inlineBlcok">
										<input type="text" class="form-control loading_contact_name" addAttr="loading_contact_name"/>
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>装货人电话：</label>
									<div class="display_inlineBlcok">
										<input type="text" class="form-control loading_contact_phone" addAttr="loading_contact_phone" onkeyup="loadPhone(this)" />
										<p></p>
									</div>
								</div>

								<div class="form-group">
									<label>卸货终止时间：</label>
									<div class="display_inlineBlcok">
										<input type="text" class="form-control endDate" addAttr="unloading_finish_date" onchange="checkEndndDate(this)" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>卸货联系人：</label>
									<div class="display_inlineBlcok">
										<input type="text" class="form-control unloading_contact_name" addAttr="unloading_contact_name" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>卸货人电话：</label>
									<div class="display_inlineBlcok">
										<input type="text"addAttr="unloading_contact_phone" class="form-control unloading_contact_phone"onkeyup="unloadPhone(this)" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>单价（元/吨）：</label>
									<div class="display_inlineBlcok">
										<input type="text" addAttr="transport_unit_price" class="form-control transport_unit_price" onkeyup="checkPrice(this)" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>货物数量（吨）：</label>
									<div class="display_inlineBlcok">
										<input type="text" addAttr="cargo_total" class="form-control cargo_total" onkeyup="checkNumber(this)"/>
										<p></p>
									</div>
								</div>
							</div>
							<div>
								<a onclick="newAddBtn_router(this)" href="javascript:;" class="btn newAdd_routerNew">添加计划</a>
							</div>
						</form>
					</div>
					<div class="modal-footer modalFooter">
						<a type="button" class="btn hold">保存</a>
						<a type="button" class="btn cancel" data-dismiss="modal">取消</a>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal 详情-->
		<div class="modal fade bs-example-modal-lg" id="myModalContractDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog modalDialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close closeLeft" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">详情</h4>
						<a type="button" isClick=true class="btn edit" onclick="detail_edit(this)">编辑</a>
					</div>
					<div class="modal-body">
						<form id="form-group contractDetail" class="form-inline contractDetail">
							<h4>合同信息</h4>
							<div class="form-group docubleUneditable">
								<label>合同编号：</label>
								<div class="display_inlineBlcok ">
									<input disabled type="text" class="form-control " id="codes"/>
									<p></p>
								</div>
							</div>
							<div class="form-group docubleUneditable">
								<label>	货物类型：</label>
								<div class="display_inlineBlcok ">
									<input disabled type="text" class="form-control " id="goodType"/>
									<p></p>
								</div>
							</div>

							<div class="form-group docubleUneditable matchGoodType">
								<label>货主类型：</label>
								<div class="display_inlineBlcok ">
									<input disabled type="text" class="form-control" id="shipperType"/>
									<p></p>
								</div>
							</div>
							<div class="form-group docubleUneditable">
								<label>货主姓名：</label>
								<div class="display_inlineBlcok">
									<input disabled type="text" class="form-control" id="shipperName"/>
									<p></p>
								</div>
							</div>
							<div class="form-group docubleUneditable">
								<label>联络人：</label>
								<div class="display_inlineBlcok">
									<input disabled type="text" class="form-control " id="name"/>
									<p></p>
								</div>
							</div>
							<div class="form-group docubleUneditable">
								<label>联络电话：</label>
								<div class="display_inlineBlcok">
									<input disabled type="text" class="form-control " id="namePhone"/>
									<p></p>
								</div>
							</div>

							<div class="form-group docubleUneditable">
								<label>结算方式：</label>
								<div class="display_inlineBlcok">
									<input disabled type="text" class="form-control " id="costType"/>
									<p></p>
								</div>
							</div>
							<div class="form-group docubleUneditable">
								<label>合同开始时间：</label>
								<div class="display_inlineBlcok">
									<input disabled type="text" class="form-control start_date" id="contractStart"/>
									<p></p>
								</div>
							</div>
							<div class="form-group docubleUneditable">
								<label>合同结束时间：</label>
								<div class="display_inlineBlcok">
									<input disabled type="text" class="form-control end_date" id="contractEnd"/>
									<p></p>
								</div>
							</div>
							<div class="first_routerInfo sr-only">
								<h4>&nbsp;&nbsp;
									<span onclick="addContractRouter(this)" class="glyphicon glyphicon-plus hidden"></span>
									<span onclick="removeContractRouter(this)" class="glyphicon glyphicon-minus hidden"></span>
									干线信息
								</h4>
								<div class="form-group">
									<label>干线信息：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled  type="text" class="form-control lineInfo"/>
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>装货货场：</label>
									<div class="display_inlineBlcok uneditable">
										<input  disabled type="text" class="form-control lineLoad"/>
										<p></p>
									</div>
								</div>

								<div class="form-group">
									<label>装货开始时间：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control lineLoadTime" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>装货联系人：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control lineLoadName" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>装货人电话：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control lineLoadNamePhone" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>卸货货场：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control lineUnload"/>
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>卸货终止时间：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control lineUnloadTime" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>卸货联系人：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control  lineUnloadName" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>卸货人电话：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control lineUnloadNamePhone" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>单价（元/吨）：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control  unitPrice" />
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label>货物数量（吨）：</label>
									<div class="display_inlineBlcok uneditable">
										<input disabled type="text" class="form-control lineTotal " />
										<p></p>
									</div>
								</div>
							</div>
							<div>
								<a onclick="newAddBtn_router(this)" href="javascript:;" class="btn newAdd_router hidden">点击添加干线</a>
							</div>
						</form>
					</div>
					<div class="modal-footer modalFooter elementDisplayNone">
						<a type="button" class="btn updateHold">保存</a>
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
						<h4 class="titleInfo">

						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>