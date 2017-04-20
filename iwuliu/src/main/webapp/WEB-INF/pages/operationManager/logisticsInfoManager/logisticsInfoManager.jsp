<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <title></title>
</head>
<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/static/operationManager/logisticsInfoManager/css/logisticsInfoManager.css"/>
<%--clockpicker.css--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/clockpicker.css">
<%--Bootstrap-table CSS文件--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
<%--Bootstrap-Datetimepicker CSS文件--%>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript"
        charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript"
        charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
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
        <div class="col-md-2 leftMenu eleHeight">
        </div>
        <div class="iframeMain">
            <h4>
                物流公司信息管理
            </h4>

            <div class="infoConditions">
                <form class="form-inline">
                    <div class="form-group">
                        <label>
                            物流公司名称:
                        </label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="company_name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            公司联系人名称:
                        </label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="contact_name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            联系人手机:
                        </label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="contact_mobile_phone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            营业执证号码:
                        </label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="business_licence"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            公司地址:
                        </label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="city_id"/>
                        </div>
                    </div>
                    <div class="QuerytheReset">
                        <a tabindex="0" role="button" type="button" class="btn query"
                           onclick="companyManagerQuery();">查询</a>
                        <a type="button" class="btn reset"
                           onclick="companyManagerQueryReset();">重置</a>
                    </div>
                </form>
                <div class="buttons">
                    <a type="button" class="btn">
                        批量导入物流公司
                    </a>
                    <a type="button" class="btn btn_detail" id="companyDetailClick"
                       onclick="btn_ModalDetail()" rel="popover">
                        详情/编辑
                    </a>
                    <a type="button" class="btn" onclick="btn_Del()">
                        删除
                    </a>
                    <a type="button" class="btn btn_newAdd" onclick="btn_NewAdd()">
                        新增物流公司
                    </a>
                    <a type="button" class="btn" onclick="btn_ModalPwd()">
                        修改密码
                    </a>
                </div>
                <div class="col-md-12">
                    <%--物流公司管理页面--%>
                    <table data-toggle="table" class="table table-striped table-bordered table-hover"
                           id="companyManagerTable"
                           data-query-params="companyManagerTableQueryParams"
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
                            <th data-field="state" data-checkbox="true" data-width="100px"></th>
                            <th data-field="company_id" class="hidden" data-align="center"
                                data-valign="middle">企业ID
                            </th>
                            <th data-field="company_name" data-align="center" class="companyName"
                                data-valign="middle">物流公司名称
                            </th>
                            <th data-field="business_licence" data-align="center" data-valign="middle"
                                data-sortable="true">营业执照号码
                            </th>
                            <th data-field="city_id" data-align="center" data-valign="middle"
                                data-sortable="true">公司地址</th>
                            <th data-field="start_work_time" data-align="center" data-valign="middle">工作时间起点</th>
                            <th data-field="finish_work_time" data-align="center" data-valign="middle">工作时间终点</th>
                            <th data-field="contact_name" data-align="center" data-valign="middle">
                                公司联系人姓名</th>
                            <th data-field="contact_mobile_phone" data-align="center" data-valign="middle"
                                data-sortable="true">联系人手机</th>
                            <th data-field="bank_account" data-align="center" data-valign="middle" data-width="150px">银行账号</th>
                            <th data-field="payment_password" data-align="center" data-valign="middle"
                                data-formatter="paymentPasswordFormatter">支付密码
                            </th>
                            <th data-field="login_password" data-align="center" data-valign="middle"
                                data-formatter="loginPasswordFormatter">登录密码
                            </th>
                            <th data-field="two_dimension_code" data-align="center" data-valign="middle"
                                data-formatter="checkCodeFormatter">二维码照片
                            </th>
                        </tr>
                        </thead>
                    </table>
                    <div class="form-inline" align="center" style="margin: -45px auto 17px;">
                        <input id="company_page" class="form-control" style="width: 60px;" type="number" value="1"
                               min="1">
                        <button id="company_goBtn" class="btn btn-success">GO</button>
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
                <h4>
                    物流公司信息
                </h4>

                <form class="form-inline" id="companyEditForm" method="post" enctype="multipart/form-data" >
                    <div class="form-group uneditable">
                        <label>物流公司名称：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control company_name"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在省：</label>

                        <div class="display_inlineBlcok detail_province">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder province"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在市：</label>

                        <div class="display_inlineBlcok detail_city">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder city"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在区县：</label>

                        <div class="display_inlineBlcok detail_area">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder area"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>所在村镇街道：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control townStreet"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable latitudeAndlongitude">
                        <label>节点经度：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control longitude"/>

                            <p></p>
                        </div>
                        <label>节点纬度：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control latitude"/>

                            <p></p>
                        </div>
                        <a class="btn hidden btnGetMap" onclick="getMapOfDetail(this)">点击获取经纬度</a>
                    </div>
                    <div class="form-group uneditable">
                        <label>营业执照号码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control business_licence"
                                   onkeyup="checkLicence(this)"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>工作时间起点：</label>

                        <div class="display_inlineBlcok">
                            <div class="input-group clockpicker" data-autoclose="true" id="start_work_time">
                                <input disabled type="text" class="form-control start_work_time"
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
                            <div class="input-group clockpicker" data-autoclose="true" id="finish_work_time">
                                <input disabled type="text" class="form-control finish_work_time"
                                       readonly="readonly">
                            <span class="input-group-addon hidden">
                            <span class="glyphicon glyphicon-time hidden"></span>
                            </span>
                            </div>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable">
                        <label>联络人姓名：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control contact_name"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>联系人手机：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control contact_mobile_phone" readonly="readonly"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>性别：</label>

                        <div class="display_inlineBlcok detail_sex">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder contact_sex"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>公司固定电话：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control company_fixed_phone"
                                   onkeyup="checkFixedPhone(this)"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>开户银行：</label>

                        <div class="display_inlineBlcok detail_bank_id">
                            <button disabled class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder bank_id"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>银行账户号：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control bank_account"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable Pwd">
                        <label>登录密码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control member_password"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group uneditable Pwd">
                        <label>支付密码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control payment_password"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group formgroupImg">
                        <lable>营业执照照片:</lable>
                        <div class="display_inlineBlcok" id="business_licence_pic">
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)" name="business_licence_pic"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
                            上传
                        </a>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group formgroupImg">
                        <lable>店面照片:</lable>
                        <div class="display_inlineBlcok" id="store_pic">
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)" name="store_pic"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn hidden">
                            上传
                        </a>
                        <span class="red">*</span>
                    </div>
                    <br/>

                    <div class="form-group formgroupImg_erweima">
                        <label>二维码图片：</label>

                        <div class="display_inlineBlcok Detail_ModalQRCode">
                        </div>
                        <span class="red">*</span>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" onclick="companyEditSave()">保存</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!--Modal 新增-->
<div class="modal fade bs-example-modal-lg" id="myModalNewAdd" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增物流公司</h4>
            </div>
            <div class="modal-body">
                <h4>
                    物流公司信息 <span class="red">*号必填</span>
                </h4>

                <form id="companyNewAddForm" class="form-inline">
                    <div class="form-group">
                        <label>物流公司名称：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_company_name" id="add_company_name"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>所在省：</label>

                        <div class="display_inlineBlcok add_province">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder province_add">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>所在城市：</label>

                        <div class="display_inlineBlcok add_city">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder city_add">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>所在区县：</label>

                        <div class="display_inlineBlcok add_area">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder area_add">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu ulHeight">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>所在村镇街道：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_townStreet" id="add_townStreet"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>节点经度：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control" name="add_longitude" id="add_longitude"/>

                            <p></p>
                        </div>
                        <label>节点纬度：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control" name="add_latitude" id="add_latitude"/>

                            <p></p>
                        </div>
                        <a class="btn btnGetMap" onclick="getMap(this)">点击获取经纬度</a>
                    </div>
                    <div class="form-group">
                        <label>营业执照号码：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_business_licence"
                                   onkeyup="checkLicence(this)"
                                   id="add_business_licence"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>工作时间起点：</label>

                        <div class="display_inlineBlcok">
                            <div class="input-group clockpicker" data-autoclose="true">
                                <input type="text" class="form-control" name="add_start_work_time"
                                       id="add_start_work_time"
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
                                <input type="text" class="form-control" name="add_finish_work_time"
                                       id="add_finish_work_time"
                                       readonly="readonly">
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                            </span>
                            </div>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>联络人姓名：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_contact_name" id="add_contact_name"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>

                    <div class="form-group">
                        <label>联系人手机：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_contact_mobile_phone"
                                   onkeyup="checkPhone(this)"
                                   id="add_contact_mobile_phone"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>公司固定电话：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_company_fixed_phone"
                                   onkeyup="checkFixedPhone(this)"
                                   id="add_company_fixed_phone"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>性别：</label>

                        <div class="display_inlineBlcok add_contact_sex">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>开户银行：</label>

                        <div class="display_inlineBlcok add_bank_id">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>银行账户号：</label>

                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" name="add_bank_account"
                                   id="add_bank_account" onkeyup="checkBankAccount(this)"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>登录密码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="password" class="form-control"
                                   name="add_login_password"
                                   id="add_login_password" value="000000"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>支付密码：</label>

                        <div class="display_inlineBlcok">
                            <input disabled type="password" class="form-control"
                                   name="add_payment_password"
                                   id="add_payment_password" value="000000"/>

                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <br/>

                    <div class="form-group formgroupImg">
                        <label>营业执照照片：</label>

                        <div class="display_inlineBlcok add_business_licence_pic">
                            点击右侧按钮上传
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)"
                               id="add_business_licence_pic_id"
                               name="add_business_licence_pic_id"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn">
                            上传
                        </a>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group formgroupImg">
                        <label>店面照片：</label>

                        <div class="display_inlineBlcok add_store_pic">
                            点击右侧按钮上传
                            <p></p>
                        </div>
                        <input class="sr-only" type="file" onchange="previewImage(this)" id="add_store_pic_id"
                               name="add_store_pic_id"/>
                        <a onclick="loadImg(this)" href="javascript:;" class="btn">
                            上传
                        </a>
                        <span class="red">*</span>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" id="companyNewAddSaveClick" onclick="companyNewAddSave()">保存</a>
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
                <h4 class="modal-title" id="myModalLabel">图片信息</h4>
            </div>
            <div class="modal-body modalImg">

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
<!--Modal 修改密码-->
<div class="modal fade" id="ModalPwd" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modalDialog_pwd" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></a>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="text-center btn_pwdType">
                    <a href="javascript:;" class="btn clickActive">修改登录密码</a>
                    <a href="javascript:;" class="btn">修改支付密码</a>
                </div>
                <div class="text-center form-horizontal loginPwd lPwd">
                    <div class="form-group">
                        <label>&nbsp;&nbsp;旧的登录密码：</label>

                        <div class="display_inlineBlcok">
                            <input type="password" class="form-control old_member_password" onkeyup="checkPsd(this)"
                                   oncopy="return false" onpaste="return false" ondragstart="return false"
                                   onselectstart="return false"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>&nbsp;&nbsp;新的登录密码：</label>

                        <div class="display_inlineBlcok">
                            <input type="password" class="form-control new_member_password" onkeyup="checkPsd(this)"
                                   oncopy="return false" onpaste="return false" ondragstart="return false"
                                   onselectstart="return false"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>再次确认新密码：</label>

                        <div class="display_inlineBlcok">
                            <input type="password" class="form-control new_member_password_again"
                                   onkeyup="checkPsd(this)"
                                   oncopy="return false" onpaste="return false" ondragstart="return false"
                                   onselectstart="return false"/>

                            <p></p>
                        </div>
                    </div>
                </div>
                <div class="text-center form-horizontal payPwd pPwd hidden">
                    <div class="form-group">
                        <label>&nbsp;&nbsp;旧的支付密码：</label>

                        <div class="display_inlineBlcok">
                            <input type="password" class="form-control old_payment_password" onkeyup="checkPsd(this)"
                                   oncopy="return false" onpaste="return false" ondragstart="return false"
                                   onselectstart="return false"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>&nbsp;&nbsp;新的支付密码：</label>

                        <div class="display_inlineBlcok">
                            <input type="password" class="form-control new_payment_password" onkeyup="checkPsd(this)"
                                   oncopy="return false" onpaste="return false" ondragstart="return false"
                                   onselectstart="return false"/>

                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>再次确认新密码：</label>

                        <div class="display_inlineBlcok">
                            <input type="password" class="form-control new_payment_password_again"
                                   onkeyup="checkPsd(this)"
                                   oncopy="return false" onpaste="return false" ondragstart="return false"
                                   onselectstart="return false"/>

                            <p></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer modalFooter form-inline lPwd">
                <button type="button" class="btn btn-default" onclick="editLoginPassword()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            <div class="modal-footer modalFooter form-inline pPwd hidden">
                <button type="button" class="btn btn-default" onclick="editPaymentPassword()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--Modal 小信息提示框-->
<div class="modal fade bs-example-modal-sm" id="smallModalInfo" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
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
<!--Modal Map-->
<div class="modal fade" id="ModalMap" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></a>
                <h4 class="modal-title">获取经纬度</h4>
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
<!--Modal qrCode-->
<div class="modal fade" id="ModalQRCode" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modalImg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></a>
                <h4 class="modal-title QRCodeCompanyName">图片信息</h4>
            </div>
            <div class="modal-body ModalQRCode">

            </div>
        </div>
    </div>
</div>
</body>
<script src="<%=request.getContextPath()%>/static/common/js/jquery.form.js" type="text/javascript" charset="utf-8">
</script>
<%--clockpicker.js--%>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/clockpicker.js"></script>
<%--Bootstrap-table JavaScript 文件以及汉化文件--%>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<%--引入自己的js--%>
<script src="<%=request.getContextPath()%>/static/operationManager/logisticsInfoManager/js/logisticsInfoManager.js"
        type="text/javascript" charset="utf-8"></script>
<%--common.js的引用放在自己单独引用的js下面--%>
<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
</html>