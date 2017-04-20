<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <title></title>
</head>
<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap.min.css"/>
<%--Bootstrap-table CSS文件--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
<%--Bootstrap-Datetimepicker CSS文件--%>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/static/operationManager/logisticsVehicleManager/css/logisticsVehicleManager.css"/>
<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript"
        charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript"
        charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/operationManager/logisticsVehicleManager/js/logisticsVehicleManager.js"
        type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
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
        <div class="col-md-2 leftMenu eleHeight"></div>

        <div class="iframeMain">
            <h4>
                物流公司车辆管理
            </h4>

            <div class="infoConditions">
                <form class="form-inline">
                    <div class="form-group">
                        <label>
                            车牌号码:
                        </label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="vehicleManager_plateNumber"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            车厢类型:
                        </label>

                        <div class="display_inlineBlcok vehicleManager_truckCarriageType">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu"></ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            车辆品牌:
                        </label>

                        <div class="display_inlineBlcok vehicleManager_truckBrand">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu"></ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            车辆型号:
                        </label>

                        <div class="display_inlineBlcok vehicleManager_truckModel">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                    id="vehicleManager_truckModel">
                                <span class="placeHolder">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="vehicleManager_truckModel"></ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            有无车辆管理者:
                        </label>

                        <div class="display_inlineBlcok ownerMember">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li value="0"><a href="javascript:;">全部</a></li>
                                <li value="1"><a href="javascript:;">有</a></li>
                                <li value="2"><a href="javascript:;">无</a></li>
                            </ul>
                        </div>
                        <div class="display_inlineBlcok manager hidden">
                            <label>
                                车辆管理者姓名:
                            </label>

                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="vehicleManager_ownerName"/>
                            </div>
                            <label>
                                手机号码:
                            </label>

                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="vehicleManager_ownerPhone"/>
                            </div>
                            <label>
                                身份证号码:
                            </label>

                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="vehicleManager_ownerID"/>
                            </div>
                        </div>
                    </div>
                    <div class="QuerytheReset">
                        <a tabindex="0" role="button" type="button" class="btn query"
                           onclick="logisticsVehicleQuery();">查询</a>
                        <a type="button" class="btn reset" onclick="logisticsVehicleQueryReset();">重置</a>
                    </div>
                </form>
                <div class="buttons">
                    <%--<a type="button" class="btn">--%>
                    <%--车辆批量导入--%>
                    <%--</a>--%>
                    <%--<a type="button" class="btn">--%>
                    <%--导出--%>
                    <%--</a>--%>
                    <a type="button" class="btn detailClick" onclick="btn_detail()">
                        详情
                    </a>
                    <a type="button" class="btn" onclick="btn_ModalNewAdd()">
                        新增车辆
                    </a>
                    <a type="button" class="btn" onclick="btn_ModalMemberofVehicle()">
                        设定车组成员
                    </a>
                    <a type="button" class="btn" onclick="btn_ModalVehicleManager()">
                        设定/解绑车辆管理者
                    </a>
                    <a type="button" class="btn" onclick="btn_ModalUnbound()">
                        解绑车辆
                    </a>
                </div>
                <div class="col-md-12">
                    <table data-toggle="table" class="table table-striped table-bordered table-hover"
                           id="logisticsVehicleTable"
                           data-query-params="logisticsVehicleQueryParams"
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
                            <th data-field="plate_number" data-align="center" data-valign="middle">车牌号</th>
                            <th data-field="truck_brand" data-align="center" data-valign="middle">车辆品牌</th>
                            <th data-field="truck_model" data-align="center" data-valign="middle">车辆型号</th>
                            <th data-field="truck_carriage_type" data-align="center" data-valign="middle">车厢类型</th>
                            <th data-field="truck_type" data-align="center" data-valign="middle">车类型</th>
                            <th data-field="truck_fuel_type" data-align="center" data-valign="middle">燃料类型</th>
                            <th data-field="truck_length" data-align="center" data-valign="middle">车长（米）</th>
                            <th data-field="vehicle_identify_number" data-align="center" data-valign="middle">车架号</th>
                            <th data-field="load_weight" data-align="center" data-valign="middle">核定载重（吨）</th>
                            <th data-field="vehicle_crew" data-align="center" data-valign="middle">车组成员</th>
                            <th data-field="owner_name" data-align="center" data-valign="middle">车辆管理者姓名</th>
                            <th data-field="owner_phone" data-align="center" data-valign="middle">车辆管理者手机</th>
                            <th data-field="owner_id" data-align="center" data-valign="middle">车辆管理者身份证号</th>
                        </tr>
                        </thead>
                    </table>
                    <div class="form-inline" align="center" style="margin: -45px auto 17px;">
                        <input id="vehicle_page" class="form-control" style="width: 60px;" type="number" value="1"
                               min="1">
                        <button id="vehicle_goBtn" class="btn btn-success">GO</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal 详情-->
<div class="modal fade bs-example-modal-lg" id="myModalDetail" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close closeLeft" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">详情</h4>
                <a type="button" isClick=true class="btn edit hidden" onclick="detail_edit(this)">编辑</a>
            </div>
            <div class="modal-body">
                <form class="form-inline" id="vehicleManagementEditForm" enctype="multipart/form-data">
                    <h4>车辆信息</h4>

                    <div class="form-group uneditable">
                        <label>车牌号码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control plate_number" id="detail_plate_number" readonly="readonly"
                                   onkeyup="checkPlate(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>行驶证号：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control driving_licence" id="detail_driving_licence"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>核定载重(吨)：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control load_weight" id="detail_load_weight"
                                   onkeyup="checkLoadWeight(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>车架号：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control vehicle_identify_number" id="detail_vehicle_identify_number"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>发动机号：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control engine_number" id="detail_engine_number"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>车辆类型：</label>

                        <div class="display_inlineBlcok detail_truck_type">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder truck_type">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label> 车辆品牌：</label>

                        <div class="display_inlineBlcok detail_truck_brand">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder truck_brand">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>车辆型号：</label>

                        <div class="display_inlineBlcok detail_truck_model_name">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder truck_model_name">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>车厢类型：</label>

                        <div class="display_inlineBlcok detail_truck_carriage_type">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder truck_carriage_type">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>车长(米)：</label>

                        <div class="display_inlineBlcok detail_truck_length">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder truck_length">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>燃料类型：</label>

                        <div class="display_inlineBlcok detail_fuel_type">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder fuel_type">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>保险公司：</label>

                        <div class="display_inlineBlcok detail_insurance_company">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder insurance_company">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>车辆第一张照片：</label>

                        <div class="display_inlineBlcok detail_truck_first_pic_save_path">
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="detail_truck_first_pic_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>车辆第二张照片：</label>

                        <div class="display_inlineBlcok detail_truck_second_pic_save_path">
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="detail_truck_second_pic_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>车辆第三张照片：</label>

                        <div class="display_inlineBlcok detail_truck_third_pic_save_path">
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="detail_truck_third_pic_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>行驶证首页照片：</label>

                        <div class="display_inlineBlcok detail_driving_licence_first_page_save_path">
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="detail_driving_licence_first_page_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>行驶证第二页照片：</label>

                        <div class="display_inlineBlcok detail_driving_licence_second_page_save_path">
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="detail_driving_licence_second_page_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
                            上传
                        </a>
                    </div>
                    <div class="form-group uneditable">
                        <label>平台人员审核状态：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control verify_status" readonly="readonly"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>审核拒绝理由：</label>

                        <div class="display_inlineBlcok verify_refused_reason">
							<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason">

							</textarea>

                            <p></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter elementDisplayNone detail_button hidden">
                <a type="button" class="btn hold" onclick="logisticsVehicleEditSave()">保存</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal 新增-->
<div class="modal fade bs-example-modal-lg" id="myModalNewAdd" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline" id="logisticsVehicleNewAddForm" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>车牌号码：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="add_plate_number" onkeyup="checkPlate(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>行驶证号：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="add_driving_licence"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>核定载重(吨)：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="add_load_weight"
                                   onkeyup="checkLoadWeight(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>车架号：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="add_vehicle_identify_number"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>发动机号：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="add_engine_number"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>车辆类型：</label>

                        <div class="display_inlineBlcok add_truck_type">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label> 车辆品牌：</label>

                        <div class="display_inlineBlcok add_truck_brand">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>车辆型号：</label>

                        <div class="display_inlineBlcok add_truck_model_name">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>车厢类型：</label>

                        <div class="display_inlineBlcok add_truck_carriage_type">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>车长(米)：</label>

                        <div class="display_inlineBlcok add_truck_length">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>燃料类型：</label>

                        <div class="display_inlineBlcok add_fuel_type">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>保险公司：</label>

                        <div class="display_inlineBlcok add_insurance_company_id">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>车辆第一张照片：</label>

                        <div class="display_inlineBlcok add_truck_first_pic_save_path">
                            点击右侧按钮上传
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="add_truck_first_pic_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>车辆第二张照片：</label>

                        <div class="display_inlineBlcok add_truck_second_pic_save_path">
                            点击右侧按钮上传
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="add_truck_second_pic_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>车辆第三张照片：</label>

                        <div class="display_inlineBlcok add_truck_third_pic_save_path">
                            点击右侧按钮上传
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="add_truck_third_pic_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>行驶证首页照片：</label>

                        <div class="display_inlineBlcok add_driving_licence_first_page_save_path">
                            点击右侧按钮上传
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="add_driving_licence_first_page_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn">
                            上传
                        </a>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>行驶证第二页照片：</label>

                        <div class="display_inlineBlcok add_driving_licence_second_page_save_path">
                            点击右侧按钮上传
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               name="add_driving_licence_second_page_save_path"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn">
                            上传
                        </a>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" id="logisticsVehicleNewAddSaveClick"
                   onclick="logisticsVehicleNewAddSave()">保存</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal 设定车组成员-->
<div class="modal fade bs-example-modal-lg" id="myModalMemberOfvehicle" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">设定车组成员</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <div class="form-group">
                        <label>车牌号码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control driver_plate_number"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-inline">
                        <div class="form-group">
                            <label>司机姓名：</label>

                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="person_name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>手机号码：</label>

                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="person_mobile_phone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>身份证号：</label>

                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="id_card_number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="sr-only">查询按钮</label>

                            <div class="display_inlineBlcok">
                                <a class="btn btn-primary" onclick="logisticsVehicleDriverQuery();">查询</a>
                            </div>
                            <div class="display_inlineBlcok">
                                <a class="btn btn-primary" onclick="logisticsVehicleDriverSetUp();">设定为车组成员</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <table data-toggle="table" class="table table-striped table-bordered table-hover"
                               id="logisticsVehicleDriverTable"
                               data-query-params="logisticsVehicleDriverQueryParams"
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
                                <th data-field="state" data-checkbox="true" data-width="100px" onclick=""></th>
                                <th data-field="person_name" data-align="center" data-valign="middle">司机姓名</th>
                                <th data-field="person_mobile_phone" data-align="center" data-valign="middle"> 司机手机</th>
                                <th data-field="ID_type_first" data-align="center" data-valign="middle"
                                    data-formatter="firstIDTypeFormatter">证件类型一
                                </th>
                                <th data-field="id_card_number" data-align="center" data-valign="middle">证件号码</th>
                                <th data-field="ID_type_second" data-align="center" data-valign="middle"
                                    data-formatter="secondIDTypeFormatter">证件类型二
                                </th>
                                <th data-field="driver_licence_number" data-align="center" data-valign="middle">
                                    驾驶证档案编号
                                </th>
                            </tr>
                            </thead>
                        </table>
                        <div class="form-inline" align="center" style="margin: -45px auto 17px;">
                            <input id="vehicleDriver_page" class="form-control" style="width: 60px;" type="number"
                                   value="1"
                                   min="1">
                            <button id="vehicleDriver_goBtn" class="btn btn-success">GO</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal 设定车辆管理者-->
<div class="modal fade bs-example-modal-lg" id="myModalVehicleManager" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">设定车辆管理者</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <div class="form-group">
                        <label>车牌号码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control driver_plate_number"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>车辆管理者姓名：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control" id="manager_member_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <table data-toggle="table" class="table table-striped table-bordered table-hover"
                               id="logisticsVehicleManagerTable"
                               data-query-params="logisticsVehicleManagerQueryParams"
                               data-query-params-type="limit"
                               data-pagination="true"
                               data-pagination-first-text="首页"
                               data-pagination-pre-text="上一页"
                               data-pagination-next-text="下一页"
                               data-pagination-last-text="末页"
                               data-row-style="rowStyle"
                               data-page-list="[10,20,30]"
                               data-smart-display="false"
                               data-single-select="true"
                        >
                            <thead style="background-color:#dbfff2;">
                            <tr>
                                <th data-field="state" data-checkbox="true" data-width="100px"></th>
                                <th data-field="person_name" data-align="center" data-valign="middle">司机姓名</th>
                                <th data-field="person_mobile_phone" data-align="center" data-valign="middle"> 司机手机</th>
                                <th data-field="ID_type_first" data-align="center" data-valign="middle"
                                    data-formatter="firstIDTypeFormatter">证件类型一
                                </th>
                                <th data-field="id_card_number" data-align="center" data-valign="middle">证件号码</th>
                                <th data-field="ID_type_second" data-align="center" data-valign="middle"
                                    data-formatter="secondIDTypeFormatter">证件类型二
                                </th>
                                <th data-field="driver_licence_number" data-align="center" data-valign="middle">
                                    驾驶证档案编号
                                </th>
                            </tr>
                            </thead>
                        </table>
                        <div class="form-inline" align="center" style="margin: -45px auto 17px;">
                            <input id="vehicleManager_page" class="form-control" style="width: 60px;" type="number"
                                   value="1"
                                   min="1">
                            <button id="vehicleManager_goBtn" class="btn btn-success">GO</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" onclick="logisticsVehicleManagerSave()">保存</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!--Modal imgBig-->
<div class="modal fade" id="ModalImg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></a>
                <h4 class="modal-title">图片信息</h4>
            </div>
            <div class="modal-body modalImg">

            </div>
        </div>
    </div>
</div>
<!--Modal 地图-->
<div class="modal fade" id="ModalMap" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></a>
                <h4 class="modal-title">地图轨迹</h4>
            </div>
            <div class="modal-body">
                <div class="map">
                    地图
                </div>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" data-dismiss="modal">确定</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!--Modal 小提示框-->
<div class="modal fade bs-example-modal-sm" id="smallModalInfo" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body text-center">
                <h2 class="titleInfo">

                </h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--Modal 小提示框设定为车组成员-->
<div class="modal fade bs-example-modal-sm" id="smallModalInfoSetUp" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body text-center">
                <h2 class="titleInfoSetUp">

                </h2>
            </div>
            <div class="modal-footer text-center">
                <button type="button" class="btn btn-default confirmSetUp" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--Modal 小提示框车辆解绑-->
<div class="modal fade bs-example-modal-sm" id="smallModalInfoUnbound" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body text-center">
                <h2 class="titleInfoUnbound">

                </h2>
            </div>
            <div class="modal-footer text-center">
                <button type="button" class="btn btn-default confirmUnbound" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--Modal 小提示框车辆管理者解绑-->
<div class="modal fade bs-example-modal-sm" id="smallModalInfoManagerUnbound" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body text-center">
                <h2 class="titleInfoManagerUnbound">

                </h2>
            </div>
            <div class="modal-footer text-center">
                <button type="button" class="btn btn-default confirmManagerUnbound" data-dismiss="modal">确定</button>
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
</html>