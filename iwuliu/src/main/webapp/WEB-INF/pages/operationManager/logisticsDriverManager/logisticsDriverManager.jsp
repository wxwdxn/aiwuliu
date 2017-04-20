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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/logisticsDriverManager/css/logisticsDriverManager.css"/>
	<%--Bootstrap-table CSS文件--%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
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
						物流司机管理
					</h4>
					<div class="infoConditions">
						<form class="form-inline">
							<div class="form-group">
    							<label>司机姓名：</label>
    							<div class="display_inlineBlcok">
    								<input type="text" class="form-control" id="person_name"/>
    								<p></p>
    							</div>
    						</div>
    						<div class="form-group">
    							<label>绑定车牌号：</label>
    							<div class="display_inlineBlcok">
    								<input type="text" class="form-control" id="plate_number"/>
    								<p></p>
    							</div>
    						</div>
							<div class="form-group TheOwnerName">
    							<label>证件类型：</label>
    							<div class="display_inlineBlcok IdType">
    								<button class="btn form-control btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder ID_type">请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									    <li value="0"><a href="javascript:;">身份证</a></li>
									    <li value="1"><a href="javascript:;">驾驶证</a></li>
									</ul>
    							</div>
    						</div>
    						<div class="form-group">
    							<label>证件号码：</label>
    							<div class="display_inlineBlcok">
    								<input type="text" class="form-control ID_num" />
    								<p></p>
    							</div>
    						</div>
    						<div class="QuerytheReset">
								<a type="button" class="btn query" onclick="driverManagerQuery()">查询</a>
								<a type="button" class="btn reset" onclick="driverManagerQueryReset()">重置</a>
							</div>
						</form>
						<div class="buttons">
							<a type="button" class="btn">
								司机批量导入
							</a>
							<a type="button" class="btn">
								导出
							</a>
							<a type="button" class="btn detailClick" onclick="driverDetail()">
								详情/编辑
							</a>
							<a type="button" class="btn" onclick="btn_NewAdd()">
								新增司机
							</a>
							<a type="button" class="btn" onclick="btn_setVehicle()">
								设定车辆
							</a>
							<a type="button" class="btn" onclick="unbundling()">
								解绑
							</a>
						</div>
						<div class="col-md-12">
							<%--物流公司司机管理页面--%>
							<table data-toggle="table" class="table table-striped table-bordered table-hover"
								   id="logisticsDriverManagerTable"
								   data-query-params="logisticsDriverManagerTableQueryParams"
								   data-query-params-type="limit"
								   data-pagination="true"
								   data-pagination-first-text="首页"
								   data-pagination-pre-text="上一页"
								   data-pagination-next-text="下一页"
								   data-pagination-last-text="末页"
								   data-row-style="rowStyle"
								   data-page-list="[10, 20,30]"
								   data-smart-display="false"
								   data-single-select = "true"

							>
								<thead style="background-color:#dbfff2;">
								<tr>
									<th data-field="state" data-checkbox="true" data-width="100px"></th>
									<th data-field="person_id" data-visible="false" data-align="center"
										data-valign="middle">个人ID</th>
									<th data-field="manager_member_name" data-align="center"
										data-valign="middle"data-sortable="true">车辆管理者姓名</th>
									<th data-field="owner_member_name" data-align="center" data-valign="middle">
										司机姓名
									</th>
									<th data-field="person_mobile_phone" data-align="center" data-valign="middle"
										data-sortable="true">手机</th>
									<th data-field="ID_type_first" data-align="center" data-valign="middle"
										data-formatter="firstIDTypeFormatter">证件类型一</th>
									<th data-field="id_card_number" data-align="center" data-valign="middle"
										data-sortable="true">证件号码</th>
									<th data-field="ID_type_second" data-align="center" data-valign="middle"
										data-formatter="secondIDTypeFormatter">证件类型二</th>
									<th data-field="driver_licence_number" data-align="center" data-valign="middle"
										data-sortable="true">驾驶证档案编号</th>
									<th data-field="plate_number" data-align="center" data-valign="middle"
										data-sortable="true">绑定车牌号</th>
									<th data-field="line" data-align="center" data-valign="middle">常跑干线</th>
									<th data-field="truck_id" data-visible="false" data-align="center" data-valign="middle">车辆id</th>
									<th data-field="verify_status_value" data-align="center" data-valign="middle">审核状态</th>
									<th data-field="verify_status" data-visible="false"  data-align="center" data-valign="middle">审核状态</th>
									<th data-field="verify_refused_reason" data-align="center" data-valign="middle">拒绝理由</th>
								</tr>
								</thead>
							</table>
							<div class="form-inline" align="center" style="margin: -45px auto 17px;">
								<input id="driver_page" class="form-control" style="width: 60px;" type="number" value="1" min="1">
								<button id="driver_goBtn" class="btn btn-success">GO</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<!-- Modal 新增-->
		<div class="modal fade bs-example-modal-lg" id="myModalNewAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" >新增</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-inline" id="driverNewAddForm" >
					<div class="form-group">
						<label>司机姓名：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control add_person_name" />
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>手机号码：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control add_person_mobile_phone"
								   onkeyup="checkPhone(this)"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>性别：</label>
						<div class="display_inlineBlcok add_sex">
							<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder sex_add">请选择</span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
							</ul>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>	证件类型一：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control first_type" value="身份证" readonly="readonly"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>证件号码：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control add_id_card_number"
								   onkeyup="checkIdCard(this)"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>	证件类型二：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control second_type" value="驾驶证" readonly="readonly"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>驾驶证档案编号：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control add_driver_licence_number"
								   onkeyup="checkDLN(this)"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div>
					<div class="form-group">
						<label>运输从业资格证号码：</label>
						<div class="display_inlineBlcok">
							<input type="text" class="form-control add_qualification_certificate_number"
								   onkeyup="checkQCN(this)"/>
							<p></p>
						</div>
						<span class="red">*</span>
					</div><br/>
					<div class="form-group formgroupImg">
						<label>手持身份证正面：</label>
						<div class="display_inlineBlcok add_id_card_front_pic_id">
							点击右侧按钮上传
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="add_id_card_front_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn">
							上传
						</a>
						<span class="red">*</span>
					</div>
					<div class="form-group formgroupImg">
						<label>手持身份证后面：</label>
						<div class="display_inlineBlcok add_id_card_back_pic_id">
							点击右侧按钮上传
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="add_id_card_back_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn">
							上传
						</a>
						<span class="red">*</span>
					</div>
					
					<div class="form-group formgroupImg">
						<label>手持驾驶证第一页照片：</label>
						<div class="display_inlineBlcok add_driver_licence_main_pic_id">
							点击右侧按钮上传
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="add_driver_licence_main_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn">
							上传
						</a>
						<span class="red">*</span>
					</div>
					<div class="form-group formgroupImg">
						<label>手持驾驶证第二页照片：</label>
						<div class="display_inlineBlcok add_driver_licence_sub_pic_id">
								点击右侧按钮上传
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="add_driver_licence_sub_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn">
							上传
						</a>
						<span class="red">*</span>
					</div>
					
					<div class="form-group formgroupImg">
						<label>手持运输从业资格证照片：</label>
						<div class="display_inlineBlcok add_qualification_certificate_number_pic_id">
								点击右侧按钮上传
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="add_qualification_certificate_number_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn">
							上传
						</a>
						<span class="red">*</span>
					</div>
		      	</form>
		      </div>
		      <div class="modal-footer modalFooter">
		        <a type="button" class="btn hold" onclick="driverNewAddSave()">保存</a>
		        <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- Modal 详情-->
		<div class="modal fade bs-example-modal-lg" id="myModalDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modalDialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close closeLeft" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" >详情</h4>
		      	<a type="button" isClick=true class="btn edit hidden" onclick="detail_edit(this)">编辑</a>
		      </div>
		      <div class="modal-body">
				  <h4>
					  物流司机信息 <span class="red">信息不合格才能编辑</span>
				  </h4>
		      	<form class="form-inline" id="driverDetailForm" enctype="multipart/form-data">
					<div class="form-group uneditable">
						<label>司机姓名：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control person_name" />
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>性别：</label>
						<div class="display_inlineBlcok detail_sex">
							<button disabled class="btn btn-default dropdown-toggle" type="button"
									data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder sex"></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>手机号码：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control person_mobile_phone" readonly="readonly"
								   onkeyup="checkPhone(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>	证件类型一：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" value="身份证" readonly="readonly"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>证件号码：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control id_card_number"
								   onkeyup="checkIdCard(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group formgroupImg">
						<label>手持身份证正面：</label>
						<div class="display_inlineBlcok id_card_front_pic_id">
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="id_card_front_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
							上传
						</a>
					</div>
					<div class="form-group formgroupImg">
						<label>手持身份证后面：</label>
						<div class="display_inlineBlcok id_card_back_pic_id">
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="id_card_back_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
							上传
						</a>
					</div>
					<div class="form-group uneditable">
						<label>	证件类型二：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control" value="驾驶证" readonly="readonly"/>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>驾驶证档案编号：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control driver_licence_number"
								   onkeyup="checkDLN(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group formgroupImg">
						<label>手持驾驶证第一页照片：</label>
						<div class="display_inlineBlcok driver_licence_main_pic_id">
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="driver_licence_main_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
							上传
						</a>
					</div>
					<div class="form-group formgroupImg">
						<label>手持驾驶证第二页照片：</label>
						<div class="display_inlineBlcok driver_licence_sub_pic_id">
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)" name="driver_licence_sub_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
							上传
						</a>
					</div>
					<div class="form-group uneditable">
						<label>运输从业资格证号码：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control qualification_certificate_number"
								   onkeyup="checkQCN(this)"/>
							<p></p>
						</div>
					</div>
					<div class="form-group formgroupImg">
						<label>手持运输从业资格证照片：</label>
						<div class="display_inlineBlcok qualification_certificate_number_pic_id">
							<p></p>
						</div>
						<input class="sr-only" type="file" onchange="previewImage(this)"
							   name="qualification_certificate_number_pic_id"/>
						<a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
							上传
						</a>
					</div>
					<div class="form-group uneditable">
						<label>审核状态：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control detail_verify_status" readonly="readonly"/>
							<p></p>
						</div>
					</div>
		      	</form>
		      </div>
		      <div class="modal-footer modalFooter elementDisplayNone detail_button hidden">
		        <a type="button" class="btn hold" onclick="driverDetailSave()">保存</a>
		        <a type="button" class="btn cancel " data-dismiss="modal">取消</a>
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
		        <h4 class="modal-title" >图片信息</h4>
		      </div>
		      <div class="modal-body modalImg">
		      	
		      </div>
		    </div>
		  </div>
		</div>
		<!--Modal 设定车辆-->
		<div class="modal fade" id="ModalsetVehicle" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modalDialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<a type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
						<h4 class="modal-title" >设定车辆</h4>
					</div>
					<div class="modal-body">
						<form class="form-inline">
							<div class="form-group">
								<label>司机姓名：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control person_name_set" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label>手机号码：</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control person_mobile_phone_set"readonly="readonly" />
								</div>
							</div>
							<h4 class="setVehicleTitle">
								车辆列表
							</h4>
							<div class="form-inline">
								<div class="form-group">
									<label>车牌号：</label>
									<div class="display_inlineBlcok">
										<input type="text" class="form-control plate_number_set" />
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only">查询：</label>
									<div class="display_inlineBlcok">
										<a class="btn setVehicleQuery" onclick="setVehicleQuery()">查询</a>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<table data-toggle="table" class="table table-striped table-bordered table-hover"
									   id="vehicleManagerTable"
									   data-url = "/iwuliu/logisticsDriverManager/findTrucksOfCompany"
									   data-query-params="vehicleQueryParams"
									   data-query-params-type="limit"
									   data-pagination="true"
									   data-pagination-first-text="首页"
									   data-pagination-pre-text="上一页"
									   data-pagination-next-text="下一页"
									   data-pagination-last-text="末页"
									   data-row-style="rowStyle"
									   data-page-list="[10,20,30]"
									   data-smart-display="false"
									   data-single-select = "true"
								>
									<thead style="background-color:#dbfff2;">
									<tr>
										<th data-field="state" data-checkbox="true" data-width="100px"></th>
										<th data-field="plate_number" data-align="center" data-valign="middle"
											data-sortable="true">车牌号</th>
										<th data-field="truck_brand_id" data-align="center" data-valign="middle">车辆品牌</th>
										<th data-field="truck_model_no" data-align="center" data-valign="middle">车辆型号</th>
										<th data-field="truck_carriage_type_id" data-align="center" data-valign="middle">车厢类型</th>
										<th data-field="truck_type_id" data-align="center" data-valign="middle">车类型</th>
										<th data-field="truck_fuel_type" data-align="center" data-valign="middle">燃料类型</th>
										<th data-field="truck_length_id" data-align="center" data-valign="middle"
											data-sortable="true">车长(米)</th>
										<th data-field="vehicle_identify_number" data-align="center" data-valign="middle"
											data-sortable="true">车架号</th>
									</tr>
									</thead>
								</table>
								<div class="form-inline" align="center" style="margin: -45px auto 17px;">
									<input id="vehicle_page" class="form-control" style="width: 60px;" type="number" value="1"
										   min="1">
									<button id="vehicle_goBtn" class="btn btn-success">GO</button>
								</div>
							</div>
						</form>

					</div>
					<div class="modal-footer modalFooter">
						<a type="button" class="btn hold" onclick="bindTruck()" >绑定</a>
						<a type="button" class="btn cancel" data-dismiss="modal">取消</a>
					</div>
				</div>
			</div>
		</div>
		<!--Modal 小提示框删除-->
		<div class="modal fade bs-example-modal-sm" id="smallModalInfoDelet" tabindex="-1" role="dialog"
			 aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
								aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">提示信息</h4>
					</div>
					<div class="modal-body text-center">
						<h2 class="titleInfoDelet">

						</h2>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-default confirmDelet" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--Modal 小信息提示框-->
		<div class="modal fade bs-example-modal-sm" id="smallModalInfo" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">信息提示</h4>
					</div>
					<div class="modal-body text-center">
						<h2 class="titleInfo">

						</h2>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default confirm" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery.form.js" type="text/javascript" charset="utf-8">
	</script>
	<%--Bootstrap-table JavaScript 文件以及汉化文件--%>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<%--引入自己的js--%>
	<script src="<%=request.getContextPath()%>/static/operationManager/logisticsDriverManager/js/logisticsDriverManager.js"
			type="text/javascript" charset="utf-8"></script>
	<%--common.js的引用放在自己单独引用的js下面--%>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
</html>