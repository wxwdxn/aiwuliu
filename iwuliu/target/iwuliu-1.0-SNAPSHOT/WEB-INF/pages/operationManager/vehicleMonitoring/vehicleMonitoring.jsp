<%--
  Created by IntelliJ IDEA.
  User: WXW
  Date: 2017/02/09
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/vehicleMonitoringManager/css/vehicleMonitoringManager.css"/>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.min.js" type="text/javascript"
			charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/vehicleMonitoringManager/js/vehicleMonitoringManager.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<%--echart--%>
	<%--高德地图--%>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="http://webapi.amap.com/maps?v=1.3&key=a0faf6f34da0f9e9fa333547de2a4a9e"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/zTreeStyle.css"/>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.ztree.core.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.ztree.excheck.js" type="text/javascript" charset="utf-8"></script>
	<style type="text/css">
		.info {
			border: solid 1px silver;
		}
		div.info-top {
			position: relative;
			background: none repeat scroll 0 0 #F9F9F9;
			border-bottom: 1px solid #CCC;
			border-radius: 5px 5px 0 0;
		}
		div.info-top div {
			display: inline-block;
			color: #333333;
			font-size: 14px;
			font-weight: bold;
			line-height: 31px;
			padding: 0 10px;
		}
		div.info-top img {
			position: absolute;
			top: 10px;
			right: 10px;
			transition-duration: 0.25s;
		}
		div.info-top img:hover {
			box-shadow: 0px 0px 5px #000;
		}
		div.info-middle {
			font-size: 12px;
			padding: 6px;
			line-height: 20px;
		}
		div.info-bottom {
			height: 0px;
			width: 100%;
			clear: both;
			text-align: center;
		}
		div.info-bottom img {
			position: relative;
			z-index: 104;
		}
		span {
			margin-left: 5px;
			font-size: 11px;
		}
		.info-middle img {
			float: left;
			margin-right: 6px;
		}
	</style>
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
						车辆监控
					</h4>
					<div class="infoConditions">
						<div class="col-md-2 vehicleMonitoringManagerCarTree">
							<div class="form-group">
								<label>车牌号:</label>
								<input type="text" class="form-control" id="name"  onkeyup="findEach()"/>
							</div>
							<div class="carTree">
									<ul id="treeDemo" class="ztree"></ul>
							</div>
						</div>
						<div class="col-md-10 vehicleMonitoringManagerMap">
							<div class="stateOftheVehicle">
								<div class="col-md-2 text-center">
									<span value="0" isCheck=false class="checkbox glyphicon glyphicon-unchecked allVehicles"></span>
									<span class="checkTitle">全部车辆</span>
								</div>
								<div class="col-md-2 text-center">
									<span value="1" isCheck=false class="checkbox glyphicon glyphicon-unchecked"></span>
									<span  class="checkTitle">
										<img src="<%=request.getContextPath()%>/static/common/images/normal.png"/><br/>
										正常行驶
									</span>
								</div>
								<div class="col-md-2 text-center">
									<span value="2" isCheck=false class="checkbox glyphicon glyphicon-unchecked"></span>
									<span class="checkTitle">
										<img src="<%=request.getContextPath()%>/static/common/images/down.png"/><br/>
										熄火
									</span>
								</div>
								<div class="col-md-2 text-center">
									<span value="3" isCheck=false class="checkbox glyphicon glyphicon-unchecked"></span>
									<span class="checkTitle">
										<img src="<%=request.getContextPath()%>/static/common/images/cass.png"/><br/>
										报警
									</span>
								</div>
								<div class="col-md-2 text-center">
									<span value="4" isCheck=false class="checkbox glyphicon glyphicon-unchecked"></span>
									<span class="checkTitle">
										<img src="<%=request.getContextPath()%>/static/common/images/red.png"/><br/>
										车辆故障
									</span>
								</div>
							</div>
							<div class="map">
								<h2>
									地图展示
								</h2>
								<div class="col-md-12" id="container"></div>
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
						<a type="button" isClick=true class="btn edit" onclick="truckReplay()">轨迹回放</a>
					</div>
					<div class="modal-body">
						<h4>
							车辆信息
						</h4>
						<form id="form-group" class="form-inline">
							<div class="form-group">
								<label>车牌号码：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control"id="carNumber" />
								</div>
							</div>
							<div class="form-group uneditable">
								<label>车辆品牌：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="brandName"/>
									<p></p>
								</div>
							</div>
							<div class="form-group uneditable">
								<label>车厢类型：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="carriageTypeName"/>
									<p></p>
								</div>
							</div>
							<div class="form-group uneditable">
								<label>车辆类型：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="typeName"/>
									<p></p>
								</div>
							</div>
							<div class="form-group uneditable">
								<label>燃料类型：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="fuleName"/>
									<p></p>
								</div>
							</div>
							<div class="form-group uneditable">
								<label>车长（米）：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="lengthName"/>
									<p></p>
								</div>
							</div>
							<div class="form-group uneditable">
								<label>车架号码：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="vehicle_identify_number"/>
									<p></p>
								</div>
							</div>
							<div class="form-group uneditable">
								<label>核定载重(吨)：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="load_weight"/>
									<p></p>
								</div>
							</div>
							<div class="form-group uneditable">
								<label>发动机号：</label>
								<div class="display_inlineBlcok uneditable">
									<input disabled type="text" class="form-control" id="engine_number"/>
									<p></p>
								</div>
							</div>

							<div class="form-group formgroupImg">
								<label>行驶证第一页：</label>
								<div class="display_inlineBlcok uneditable" id="firstPageSavePath">

								</div>
							</div>
							<div class="form-group formgroupImg">
								<label>行驶证第二页：</label>
								<div class="display_inlineBlcok uneditable" id="secondPageSavePath">
								</div>
							</div>
							<div class="form-group formgroupImg">
								<label>车辆照片：</label>
								<div class="display_inlineBlcok"id="truckPic">
								</div>
							</div>
							<div class="form-group uneditable">
								<label>长跑干线：</label>
								<div class="display_inlineBlcok "id="lineName">

								</div>
							</div>
						</form>
						<%--<h4>--%>
							<%--货场信息--%>
						<%--</h4>--%>
						<%--<form class="form-inline">--%>
							<%--<div class="form-group">--%>
								<%--<label>货物类型：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>
							<%--<div class="form-group">--%>
								<%--<label>合同编号：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>
							<%--<div class="form-group">--%>
								<%--<label>线路信息：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>
							<%--<div class="form-group">--%>
								<%--<label>预计时间：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>
							<%--<div class="form-group">--%>
								<%--<label>预计到达时间：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>
							<%--<div class="form-group">--%>
								<%--<label>预计支出费用：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>
							<%--<div class="form-group">--%>
								<%--<label>预计运费：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<div class="form-group">--%>
								<%--<label>时间：</label>--%>
								<%--<div class="display_inlineBlcok uneditable">--%>
									<%--<input disabled type="text" class="form-control" />--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</form>--%>
						<h4>
							车辆运单记录
							<%--<a class="getMore pull-right" href="javascript:;">查看更多&gt;&gt;</a>--%>
						</h4>

							<div>
								<%--车辆运单记录--%>
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
									   data-page-list="[10,20]"
									   data-smart-display="false"
									   data-height="100">
									<thead style="background-color:#dbfff2;">
									<tr>
										<th data-field="code"  data-align="center" data-valign="middle" data-sortable="true" data-width="155px">
											运单号
										</th>
										<th data-field="carNumber" data-align="center" data-valign="middle" data-width="75px"data-sortable="true">车牌号</th>
										<th data-field="company" data-align="center" data-valign="middle" data-sortable="true">承运方</th>
										<th data-field="goodName" data-align="center" data-valign="middle" data-sortable="true">货物</th>
										<th data-field="managerMember" data-align="center" data-valign="middle" data-sortable="true"
										>车主
										</th>
										<th data-field="reciveMember" data-align="center" data-valign="middle" data-sortable="true">
											司机
										</th>
										<th data-field="mainLineName" data-align="center" data-valign="middle" data-sortable="true"
										>线路
										</th>
										<th data-field="unloadingEndTime" data-align="center" data-valign="middle" data-sortable="true"
										>完成时间
										</th>
										<th data-field="transportUnitPrice" data-align="center" data-valign="middle" data-sortable="true"
										>运费（元）
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
	</body>
</html>
