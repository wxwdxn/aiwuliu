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
<%--clockpicker.css--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/clockpicker.css">
<%--Bootstrap-table CSS文件--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
<link rel="stylesheet"
	  href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/freightYardManager/css/freightYardManager.css"/>
<%--Bootstrap时间控件  JavaScript 文件以及汉化文件--%>
<script src="<%=request.getContextPath()%>/static/common/js/jquery.min.js" type="text/javascript"
		charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<%--clockpicker.js--%>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/clockpicker.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/operationManager/freightYardManager/js/freightYardManager.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>

<!--[if lt IE 9]>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<%--高德地图--%>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://webapi.amap.com/maps?v=1.3&key=a0faf6f34da0f9e9fa333547de2a4a9e"></script>
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
						货场管理
					</h4>
					<div class="infoConditions">
						<form class="form-inline">
							<div class="form-group">
								<label>省份：</label>
    							<div class="display_inlineBlcok provinceJson">
	    							<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder"></span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

									</ul>
    							</div>
							</div>
    						<div class="form-group nameOfinstitution">
    							<label>市区：</label>
								<div class="display_inlineBlcok cityJson">
    								<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder"></span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									</ul>
    							</div>
    						</div>
    						<div class="form-group TheOwnerName">
    							<label>县区：</label>
    							<div class="display_inlineBlcok areaJson">
    								<button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder"></span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									</ul>
    							</div>
    						</div>
    						<div class="form-group">
    							<label>街道：</label>
    							<div class="display_inlineBlcok">
    								<input type="text" class="form-control" id="street"/>
    								<p></p>
    							</div>
    						</div>
    						<div class="QuerytheReset">
								<a type="button" class="btn query" onclick="searchYard()">查询</a>
								<a type="button" class="btn reset" onclick="resetYard()">重置</a>
							</div>
						</form>
						<div class="buttons">
							<a type="button" class="btn btn_export">
								导出
							</a>
							<a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
								详情/编辑
							</a>
							<a type="button" class="btn" onclick="btn_NewAdd()">
								新增
							</a>
							<a tabindex="0" type="button" class="btn delet" onclick="deletefreightYard()">
								删除
							</a>
						</div>
						<div class="col-md-12" style="padding: 0;">
							<%--货场--%>
							<table style="width: 100%;" data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="freightYard"
								   data-query-params="freightYardParams"
								   data-url="/iwuliu/freightYardManager/list"
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
									<th data-field="state" data-checkbox="true" data-width="100px"></th>
									<th data-field="freightYardId" class="hidden"  data-align="center" data-valign="middle">货场id</th>
									<th data-field="position"  data-align="center" data-valign="middle">货场省市县</th>
									<th data-field="townStreet" data-align="center" data-valign="middle">货场所在村镇街道
									</th>
									<th data-field="longitude" data-align="center" data-valign="middle">经度</th>
									<th data-field="latitude" data-align="center" data-valign="middle">纬度</th>
								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="freightYardpage" class="form-control" style="width: 60px;" type="number"
									   value="1" min="1">
								<button id="freightYardgoBtn" class="btn btn-success">GO</button>
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
		        <button type="button" class="close closeLeft" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel2">详情</h4>
		      	<a type="button" isClick=true class="btn edit" onclick="detail_edit(this)">编辑</a>
		      </div>
		      <div class="modal-body update">
		      	<form id="form-group2 update" class="form-inline">
					<div class="form-group uneditable">
						<label>货场名称：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="CargoName" addAttr="CargoName" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>货场所在省市县：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="position" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>货场所在村镇街道：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="town"  class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>装货时间起点：</label>
						<div class="display_inlineBlcok ">
							<div class="input-group  clockpicker" data-autoclose="true" id="detailBeginTime">
							<input type="text"disabled class="form-control startDate"readonly="readonly" id="beginTime"  addAttr="beginTime"/>
							 <span class="input-group-addon hidden">
                            <span class="glyphicon glyphicon-time hidden"></span>
                            </span>
							</div>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>装货时间终点：</label>
						<div class="display_inlineBlcok">
						<div class="input-group clockpicker" data-autoclose="true" id="detailEndTime">
								<input type="text" disabled class="form-control endtDate" readonly="readonly" addAttr="endTime"  id="endTime"/>
								 <span class="input-group-addon hidden">
								<span class="glyphicon glyphicon-time hidden"></span>
								</span>
						</div>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>装货费用：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="loadCosts" addAttr="loadCost"class="form-control" value="普通"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>装货过磅费用：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="loadPumpCosts" addAttr="loadPumpCost"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>日装货车数：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="loadPerDay" addAttr="loadPerDay"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>卸货时间起点：</label>
						<div class="display_inlineBlcok ">
							<div class="input-group clockpicker" data-autoclose="true" id="detailUnloadBeginTime">
							<input disabled type="text" class="form-control start_date" readonly="readonly" addAttr="unloadBeginTime"id="unloadBeginTime" />
							 <span class="input-group-addon hidden">
                            <span class="glyphicon glyphicon-time hidden"></span>
                            </span>
						</div>
						<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>卸货时间终点：</label>
						<div class="display_inlineBlcok ">
							<div class="input-group clockpicker" data-autoclose="true" id="detailUnloadendTime">
							<input type="text" disabled  class="form-control start_end" readonly="readonly"  id="unloadendTime" addAttr="unloadendTime"  />
							 <span class="input-group-addon hidden">
                            <span class="glyphicon glyphicon-time hidden"></span>
                            </span>
						</div>
						<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>卸货费用：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unloadCosts" addAttr="unloadCost" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>卸货过磅费用：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="unloadPumpCosts" addAttr="unloadPumpCost"class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>日卸货车数：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="unloadPerDay" addAttr="unloadPerDay"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>经度：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" id="longitude"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>纬度：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control"id="latitude"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>前10趟平均装货时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="loadAverage" class="form-control"/>
							<p></p>
						</div>
					</div>
					<div class="form-group docubleUneditable">
						<label>前10趟平均卸货时间：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" id="unloadAverage" class="form-control"/>
							<p></p>
						</div>
					</div>
				</form>
		       </div>
		      <div class="modal-footer modalFooter elementDisplayNone">
		        <a type="button" class="btn hold" onclick="ipdateFreightYard()">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 新增-->
		<div class="modal fade bs-example-modal-lg" id="myModalNewAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel3">新增</h4>
		      </div>
		      <div class="modal-body">
		      	<h4>
		      		货场信息<span class="red">*号为必填项</span>
		      	</h4>
		      	<form id="form-group" class="form-inline myModalNewAdd">
					<div class="form-group">
						<label>省份：</label>
						<div class="display_inlineBlcok provinceJsons">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu14" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder" addAttr="province">请选择</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							</ul>
							<p></p>
						</div>
						<div class="display_inlineBlcok cityJsons" >
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu15" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder" addAttr="city">请选择</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

							</ul>
							<p></p>
						</div>
						<div class="display_inlineBlcok areaJsons">
							<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu16" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder" addAttr="area">请选择</span>
							    <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

							</ul>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>货场所在村镇街道：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control newAdd_address" id="townStreet"addAttr="townStreet"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>货场名称：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" id="freightYardName" addAttr="freightYardName"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>装货费用（元）：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control"id="loadCost"
								   onkeyup="checkLoadCost(this)" addAttr="loadCost"value="0"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>装货过磅费用（元）：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" id="loadPumpCost"
								   onkeyup="checkPumpCost(this)" addAttr="loadPumpCost" value="0"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>装货时间起点：</label>
						<div class="display_inlineBlcok">
							<div class="input-group  clockpicker" data-autoclose="true">
								<input type="text" class="form-control startDate"  readonly="readonly"id="startDate"  addAttr="beginStart"/>
							 <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                            </span>
							</div>
							<p></p>
						</div>

					</div>
					<div class="form-group">
						<label>装货时间终点：</label>
						<div class="display_inlineBlcok">
							<div class="input-group clockpicker" data-autoclose="true">
								<input type="text" class="form-control endtDate"  readonly="readonly" id="endtDate" addAttr="beginEnd"/>
							 <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                            </span>
							</div>
							<p></p>
						</div>
					</div>

					<div class="form-group">
						<label>日装货车数：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control"addAttr="loadCount" value="0"  id="loadCount" onkeyup="checkLoadCount(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>卸货费用（元）：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control"addAttr="unloadCost" value="0" id="unloadCost" onkeyup="checkunLoadCosts(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>卸货过磅费用（元）：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" addAttr="unloadPumpCost" value="0" id="unloadPumpCost" onkeyup="checkunPumpCosts(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>卸货时间起点：</label>
						<div class="display_inlineBlcok ">
							<div class="input-group clockpicker" data-autoclose="true">
								<input type="text" class="form-control start_date" readonly="readonly"  id="start_date"addAttr="endStart"/>
							 <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                            </span>
							</div>
							<p></p>
						</div>

					</div>
					<div class="form-group">
						<label>卸货时间终点：</label>
						<div class="display_inlineBlcok ">

							<div class="input-group clockpicker" data-autoclose="true">
								<input type="text" class="form-control start_end" id="start_end" readonly="readonly"  addAttr="endFinsh"/>
							 <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                            </span>
							</div>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>日卸货车数：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control"id="unloadCount"value="0" addAttr="unloadCount"  onkeyup="checkunLoadCount(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>经度：</label>
						<div class="display_inlineBlcok">
							<input disabled id="longitudeValue"addAttr="longitude"  class="newAdd_longitude form-control" type="text" class="form-control"/>
							<p></p>
							<span class="red">*</span>
						</div>
						<label>纬度：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text"id="latitudeValue" addAttr="latitude" class="newAdd_latitude form-control"/>
							<p></p>
							<span class="red">*</span>
						</div>
						<span class="getMap" style="color: red" onclick="getMap(this)">点击获取经纬度</span>
					</div>
					<div class="form-group">
						<label>前10趟平均装货时间（分钟）：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control"  addAttr="averageLoad" id="averageLoad" onkeyup="averageLoader(this)"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>前10趟平均卸货时间（分钟）：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control" addAttr="averageUnload" id="averageUnload" onkeyup="averageUnloader(this)"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
				</form>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn hold" onclick="saveFreightYard()">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 地图Map-->
		<div class="modal fade" id="ModalMap" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		       <div class="modal-header">
		        <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
		        <h4 class="modal-title" id="myModalLabel">获取经纬度</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="form-inline">
		      		<div class="form-group">
		      			<label>地址：</label>
		      			<div class="display_inlineBlcok">
		      				<input  disabled type="text" class="form-control map_address" />
		      			</div>
		      			<a class="btn btn-primary" onclick="getClicks()">点击获取经纬度</a>
		      		</div>
		      	</div>
		      	<div class="form-control modalMap" style="position: relative;">
						<div id="container"></div>
		      	</div>
		      	
		      </div>
		      <div class="modal-footer modalFooter form-inline">
				<div class="form-group ">
					<label>经度：</label>
					<div class="display_inlineBlcok margin-right">
						<input disabled type="text" class="form-control map_longitude uneditable" />
					</div>
					<label>纬度：</label>
					<div class="display_inlineBlcok ">
						<input  disabled type="text" class="form-control map_latitude uneditable" />
					</div>
		       		 <a type="button" class="btn btn-primary" onclick="determine()"data-dismiss="modal">确定</a>
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
		<!--删除Modal 小提示框-->
		<div class="modal fade bs-example-modal-sm" id="smallModalInfoDelete" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">提示信息</h4>
					</div>
					<div class="modal-body text-center">
						<h3 class="titleInfoDelete">

						</h3>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-default deletefreightYard" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>