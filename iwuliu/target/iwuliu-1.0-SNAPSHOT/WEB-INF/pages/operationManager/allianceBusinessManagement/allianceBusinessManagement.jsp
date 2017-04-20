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
<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript"
        charset="utf-8"></script>
<%--clockpicker.css--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/clockpicker.css">
<%--clockpicker.js--%>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/clockpicker.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript"
        charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/static/operationManager/allianceBusinessManagement/css/allianceBusinessManagement.css"/>
<script src="<%=request.getContextPath()%>/static/operationManager/allianceBusinessManagement/js/allianceBusinessManagement.js"
        type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript"
        charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/jquery.form.js" type="text/javascript" charset="utf-8">
</script>
<%--Bootstrap-table JavaScript 文件以及汉化文件--%>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<%--Bootstrap时间控件  JavaScript 文件以及汉化文件--%>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<%--生成二维码--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/common/js/jquery.qrcode.min.js"></script>
<%--高德地图--%>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://webapi.amap.com/maps?v=1.3&key=a0faf6f34da0f9e9fa333547de2a4a9e"></script>
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
                线下加盟商管理
            </h4>

            <div class="infoConditions">
                <form class="form-inline">
                    <div class="form-group">
                        <label>服务站名称：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="station_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>服务站类型：</label>

                        <div class="display_inlineBlcok station_type">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    id="station_type" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="station_type">
                                <li value=""><a href="javascript:;">全部类型</a></li>
                                <li value="0"><a href="javascript:;">维修站</a></li>
                                <li value="1"><a href="javascript:;">加气站</a></li>
                                <li value="2"><a href="javascript:;">4S店</a></li>
                                <li value="3"><a href="javascript:;">加油站</a></li>
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在省：</label>

                        <div class="display_inlineBlcok province_id">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在城市：</label>

                        <div class="display_inlineBlcok city_id">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在区县：</label>

                        <div class="display_inlineBlcok area_id">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在村镇街道：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="town_street" id="town_street"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="QuerytheReset">
                        <a type="button" class="btn query" onclick="allianceBusinessQuery();">查询</a>
                        <a type="button" class="btn reset" onclick="allianceBusinessQueryReset();">重置</a>
                    </div>
                </form>

                <div class="buttons">
                    <a type="button" class="btn" onclick="btn_newAdd()">
                        新增
                    </a>
                    <a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
                        详情/编辑
                    </a>
                    <a type="button" class="btn" onclick="stationDel()">
                        删除
                    </a>
                </div>
                <div class="col-md-12">
                    <table class="table table-striped table-bordered table-hover"
                           id="allianceBusinessTable"
                           data-query-params="allianceBusinessQueryParams"
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
                            <th class="hidden" data-field="station_id" data-align="center" data-valign="middle"
                                width="0">
                                线下加盟商ID
                            </th>
                            <th class="station_name" data-field="station_name" data-align="center" data-valign="middle">
                                服务站名称
                            </th>
                            <th class="station_type" data-field="station_type" data-align="center" data-valign="middle">
                                服务站类型
                            </th>
                            <th data-field="province_name" data-align="center" data-valign="middle">所在省</th>
                            <th data-field="city_name" data-align="center" data-valign="middle">所在市</th>
                            <th data-field="area_name" data-align="center" data-valign="middle">所在区县</th>
                            <th data-field="town_street" data-align="center" data-valign="middle">所在村街道</th>
                            <th data-field="work_begin_time" data-align="center" data-valign="middle">工作时间起点</th>
                            <th data-field="work_end_time" data-align="center" data-valign="middle">工作时间终点</th>
                            <th data-field="contact_name" data-align="center" data-valign="middle">联络人</th>
                            <th data-field="contact_phone" data-align="center" data-valign="middle">联络电话</th>
                            <th data-field="bank_name" data-align="center" data-valign="middle">开户银行</th>
                            <th data-field="bank_account" data-align="center" data-valign="middle">银行帐号</th>
                            <th class="qrCodePhoto" data-field="qr_code_photo" data-align="center" data-valign="middle"
                                data-formatter="qrCodePhotoFormatter">二维码照片
                            </th>
                        </tr>
                        </thead>
                    </table>
                    <div class="form-inline" align="center" style="margin: -45px auto 17px;">
                        <input id="allianceBusiness_page" class="form-control" style="width: 60px;" type="number"
                               value="1" min="1">
                        <button id="allianceBusiness_goBtn" class="btn btn-success">GO</button>
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
                <a type="button" isClick=true class="btn edit" onclick="detail_edit(this)">编辑</a>
            </div>
            <div class="modal-body">
                <form class="form-inline" id="stationEditForm" method="post">
                    <div class="form-group uneditable">
                        <label>服务站名称：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control detail_station_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>服务站类型：</label>

                        <div class="display_inlineBlcok detail_station_type">
                            <button disabled class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder stationType">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li value="0"><a href="javascript:;">维修站</a></li>
                                <li value="1"><a href="javascript:;">加气站</a></li>
                                <li value="2"><a href="javascript:;">4S店</a></li>
                                <li value="3"><a href="javascript:;">加油站</a></li>
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在省：</label>

                        <div class="display_inlineBlcok detail_province">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder province provinceCommon">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在市：</label>

                        <div class="display_inlineBlcok detail_city">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder city cityCommon">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在区县：</label>

                        <div class="display_inlineBlcok detail_area">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder area areaCommon">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在村镇街道：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control townStreet townStreetCommon"
                                   name="townStreet" id="townStreet"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable LatitudeAndLongitude">
                        <label>节点经度：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control detail_longitude longitudeCommon"/>

                            <p></p>
                        </div>
                        <label>节点纬度：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control detail_latitude latitudeCommon"/>

                            <p></p>
                        </div>
                        <span class="getMap hidden" onclick="getMap(this)">点击获取经纬度</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>工作时间起点：</label>

                        <div class="display_inlineBlcok">
                            <div class="input-group clockpicker" data-autoclose="true">
                                <input disabled type="text" class="form-control detail_work_begin_time"
                                       id="detail_work_begin_time" name="detail_work_begin_time"
                                       readonly="readonly">
                            <span class="input-group-addon hidden">
                            <span class="glyphicon glyphicon-time hidden"></span>
                            </span>
                            </div>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>工作时间终点：</label>

                        <div class="display_inlineBlcok">
                            <div class="input-group clockpicker" data-autoclose="true">
                                <input disabled type="text" class="form-control detail_work_end_time"
                                       id="detail_work_end_time" name="detail_work_end_time"
                                       readonly="readonly">
                            <span class="input-group-addon hidden">
                            <span class="glyphicon glyphicon-time hidden"></span>
                            </span>
                            </div>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>联络人：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control contact_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>联络电话：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control contact_phone" onkeyup="checkPhone(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>开户银行：</label>

                        <div class="display_inlineBlcok detail_bank_id">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder bank_id">全部</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>银行账户：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control bank_account"
                                   onkeyup="checkBankNumber(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>开户人姓名：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control bank_account_person_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>开户人身份证号：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control bank_account_person_id"
                                   onkeyup="checkID(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div>
                        <label class="erweima">二维码:</label>

                        <div class="display_inlineBlcok Detail_ModalQRCode"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" onclick="stationEditSave()">保存</a>
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
                <form class="form-inline" id="stationNewAddForm" method="post" enctype="multipart/form-data">
                    <div class="form-group ">
                        <label>服务站名称：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_station_name" id="add_station_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>服务站类型：</label>

                        <div class="display_inlineBlcok add_station_type">
                            <button class="btn form-control btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li value="0"><a href="javascript:;">维修站</a></li>
                                <li value="1"><a href="javascript:;">加气站</a></li>
                                <li value="2"><a href="javascript:;">4S店</a></li>
                                <li value="3"><a href="javascript:;">加油站</a></li>
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在省：</label>

                        <div class="display_inlineBlcok add_province">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder provinceCommon">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在城市：</label>

                        <div class="display_inlineBlcok add_city">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder cityCommon">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在区县：</label>

                        <div class="display_inlineBlcok add_area">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder areaCommon">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>所在村镇街道：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control townStreetCommon" name="add_townStreet"
                                   id="add_townStreet"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>节点经度：</label>

                        <div class="display_inlineBlcok add_longitude">
                            <input disabled type="text" class="form-control longitudeCommon" name="add_longitude"
                                   id="add_longitude"/>

                            <p></p>
                        </div>
                        <label>节点纬度：</label>

                        <div class="display_inlineBlcok add_latitude">
                            <input disabled type="text" class="form-control latitudeCommon" name="add_latitude"
                                   id="add_latitude"/>

                            <p></p>
                        </div>
                        <span class="getMap" onclick="getMap(this)">点击获取经纬度</span>
                    </div>
                    <div class="form-group">
                        <label>工作时间起点：</label>

                        <div class="display_inlineBlcok">
                            <div class="input-group clockpicker" data-autoclose="true">
                                <input type="text" class="form-control" name="add_work_begin_time"
                                       id="add_work_begin_time"
                                       readonly="readonly">
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                            </span>
                            </div>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>工作时间终点：</label>

                        <div class="display_inlineBlcok">
                            <div class="input-group clockpicker" data-autoclose="true">
                                <input type="text" class="form-control" name="add_work_end_time"
                                       id="add_work_end_time"
                                       readonly="readonly">
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                            </span>
                            </div>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>联络人：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_contact_name" id="add_contact_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>联络电话：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_contact_phone" id="add_contact_phone"
                                   onkeyup="checkPhone(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>开户银行：</label>

                        <div class="display_inlineBlcok add_bank_id">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>银行账户：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_bank_account" id="add_bank_account"
                                   onkeyup="checkBankNumber(this)"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>开户人姓名：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_bank_account_person_name"
                                   id="add_bank_account_person_name"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>开户人身份证号：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_bank_account_person_id"
                                   id="add_bank_account_person_id" onkeyup="checkID(this)"/>

                            <p></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" id="stationNewAddSaveClick" onclick="stationNewAddSave()">保存</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!--Modal qrCode-->
<div class="modal fade" id="ModalQRCode" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modalImg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></a>
                <h4 class="modal-title QRCodeStationName">图片信息</h4>
            </div>
            <div class="modal-body ModalQRCode">

            </div>
        </div>
    </div>
</div>
<!--Modal Map-->
<div class="modal fade" id="ModalMap" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></a>
                <h4 class="modal-title" id="myModalLabel">获取经纬度</h4>
            </div>
            <div class="modal-body">
                <div class="form-inline">
                    <div class="form-group">
                        <label>地址：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control map_address"/>
                        </div>
                        <a class="btn btn-primary" onclick="getClick()">点击获取经纬度</a>
                    </div>
                </div>
                <div class="form-control modalMap" style="position: relative;">
                    <div class="baiduMap" id="container">

                    </div>
                </div>
            </div>
            <div class="modal-footer modalFooter form-inline">
                <div class="form-group">
                    <label>经度：</label>

                    <div class="display_inlineBlcok margin-right">
                        <input type="text" class="form-control map_longitude"/>
                    </div>
                    <label>纬度：</label>

                    <div class="display_inlineBlcok">
                        <input type="text" class="form-control map_latitude"/>
                    </div>
                    <a type="button" class="btn btn-primary" data-dismiss="modal" onclick="determine()">确定</a>
                </div>
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
            <div class="modal-footer text-center">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>