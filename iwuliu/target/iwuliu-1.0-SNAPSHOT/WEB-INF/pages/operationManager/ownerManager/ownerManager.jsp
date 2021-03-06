<%--
  Created by IntelliJ IDEA.
  User: WXW
  Date: 2017/2/27
  Time: 10:04
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/ownerManager/css/ownerManager.css"/>
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
<script src="<%=request.getContextPath()%>/static/operationManager/ownerManager/js/ownerManager.js" type="text/javascript" charset="utf-8"></script>
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
                货主管理
            </h4>
            <div class="infoConditions">
                <form class="form-inline shipperList">
                    <div class="form-group">
                        <label>货主类型：</label>
                        <div class="display_inlineBlcok shipperType">
                            <button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu12" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span value="1" class="placeHolder">公司货主</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li value="1"><a href="javascript:;">公司货主</a></li>
                                <li value="0"><a href="javascript:;">个人货主</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group companyOwner">
                        <label>货主名称：</label>
                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" addAttr="ownerName"id="ownerName"/>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group individualOwner hidden">
                        <label>证件号码：</label>
                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control"addAttr="id_card_number" id="id_card_number"/>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>货主手机：</label>
                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control"addAttr="person_mobile_phone" id="person_mobile_phone" />
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>货主姓名：</label>
                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" addAttr="person_name" id="person_name" />
                            <p></p>
                        </div>
                    </div>
                    <div class="QuerytheReset">
                        <a type="button" class="btn query searchOwnerList">查询</a>
                        <a type="button" class="btn reset">重置</a>
                    </div>
                </form>
                <div class="buttons">
                    <a type="button" class="btn">
                        货主批量导入
                    </a>
                    <a type="button" class="btn btn_export">
                        导出
                    </a>
                    <a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
                        详情/编辑
                    </a>
                    <a type="button" class="btn" onclick="btn_NewAdd()">
                        新增货主
                    </a>
                </div>
                <div class="col-md-12" >
                    <%--货主--%>
                    <table data-toggle="table" class="table table-striped table-bordered table-hover"
                           id="ownerList"
                           data-query-params="ownerListTable"
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
                            <th data-field="personId" class="hidden" data-align="center" data-valign="middle">
                                个人
                            </th>
                            <th data-field="companyId" class="hidden" data-align="center" data-valign="middle">
                                公司
                            </th>
                            <th data-field="type" data-align="center" data-valign="middle">货主类型</th>
                            <th data-field="ownerName" data-align="center" data-valign="middle">货主名称</th>
                            <th data-field="personName" data-align="center" data-valign="middle" data-sortable="true"
                            >货主姓名
                            </th>
                            <th data-field="personMobilePhone" data-align="center" data-valign="middle" data-sortable="true"
                            >货主联系方式
                            </th>
                            <th data-field="idCardNumber" data-align="center" data-valign="middle" data-sortable="true"
                            >身份证号码
                            </th>
                        </tr>
                        </thead>
                    </table>
                    <div class="form-inline" align="center" style="margin: -45px auto 17px;">
                        <input id="ownerListTablepage" class="form-control" style="width: 60px;" type="number"
                               value="1" min="1">
                        <button id="ownerListTablegoBtn" class="btn btn-success">GO</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal详情编辑 -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close closeLeft" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">货主详情</h4>
                <%--<a type="button" isClick=true class="btn edit" onclick="detail_edit(this)">编辑</a>--%>
            </div>
            <div class="modal-body owner">
                <form id="form-group2" class="form-inline">
                    <div class="form-group docubleUneditable ownerType">
                        <label>货主类型：</label>
                        <div class="display_inlineBlcok">
                            <input disabled type="text" class="form-control" id="types"/>
                            <p></p>
                        </div>
                    </div>
                    <div class="companyOwner">
                        <div class="form-group uneditable">
                            <label>货主名称：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="companyName"/>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group uneditable">
                            <label>货主姓名：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="contactName"/>
                                <p></p>
                            </div>
                        </div>

                        <div class="form-group uneditable">
                            <label>&nbsp;营业执照号码&nbsp;：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control"id="licence"  />
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group uneditable">
                            <label>货主手机：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="phone"/>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group uneditable">
                            <label>公司固定电话：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="fixPhone"/>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group uneditable">
                            <label>联系人性别：</label>
                            <div class="display_inlineBlcok sex" >
                                <input disabled type="text" class="form-control" id="sex"/>
                                <p></p>
                            </div>
                        </div><br/>
                        <div class="form-group docubleUneditable ">
                            <label>所在省：</label>
                            <div class="display_inlineBlcok provinceId">
                                <button disabled class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="placeHolder"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                                </ul>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group docubleUneditable pro">
                            <label>所在城市：</label>
                            <div class="display_inlineBlcok cityId">
                                <button disabled class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu14" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="placeHolder"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                                </ul>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group docubleUneditable">
                            <label>所在区县：</label>
                            <div class="display_inlineBlcok areaId">
                                <button disabled class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu15" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="placeHolder"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                                </ul>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group docubleUneditable">
                            <label>所在村镇街道：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="townStreet"/>
                                <p></p>
                            </div>
                        </div>
                    </div>
                    <div class="individualOwners">
                        <div class="form-group docubleUneditable">
                            <label>货主联系人姓名：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="name"/>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group docubleUneditable">
                            <label>货主手机：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="Mophone"/>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group docubleUneditable">
                            <label>货主证件号码：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="number"/>
                                <p></p>
                            </div>
                        </div>
                        <div class="form-group docubleUneditable">
                            <label>货主联系人性别：</label>
                            <div class="display_inlineBlcok">
                                <input disabled type="text" class="form-control" id="personSex"/>
                                <p></p>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter elementDisplayNone">
                <a type="button" class="btn hold">保存</a>
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
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <h4>
                    货主信息<span class="red">*号为必填项</span>
                </h4>
                <form id="form-group" class="form-inline">
                    <div class="form-group">
                        <label>货主类型：</label>
                        <div class="display_inlineBlcok newAdd_shipperType">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li value="1"><a href="javascript:;">公司货主</a></li>
                                <li value="0"><a href="javascript:;">个人货主</a></li>
                            </ul>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-inline company">
                        <div class="form-group">
                            <label>
                                货主名称：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="company_name" addAttr="company_name"/>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                货主方联系人姓名：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" addAttr="contact_name" class="form-control" id="contact_name"/>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                货主手机：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text"addAttr="contact_mobile_phone" onkeyup="phone(this)"  class="form-control" id="contact_mobile_phone" />
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                营业执照号码：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" addAttr="business_licence" onkeyup="checkNumber(this)"  class="form-control" id="business_licence" />
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                公司固定电话：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" addAttr="company_fixed_phone"  onkeyup="loadPhone(this)"class="form-control" id="company_fixed_phone" />
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                联系人性别：
                            </label>
                            <div class="display_inlineBlcok sexJson">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span  class="placeHolder" addAttr="contact_sex"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                                </ul>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                所在省份：
                            </label>
                            <div class="display_inlineBlcok provinceJson" >
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu18" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="placeHolder" addAttr="province"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                                </ul>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                所在城市：
                            </label>
                            <div class="display_inlineBlcok cityJson">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu19" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="placeHolder" addAttr="city"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                                </ul>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                所在区县：
                            </label>
                            <div class="display_inlineBlcok areaJson">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu31" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="placeHolder" addAttr="area"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1"></ul>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                货场所在村镇街道：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="town" addAttr="townStreet"/>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                    </div>
                    <div class="form-inline individual person hidden">
                        <div class="form-group">
                            <label>
                                货主方联系人姓名：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="personName" addAttr="personName" />
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                货主方联系人手机：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="phones"onkeyup="phone(this)" addAttr="phone" />
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>
                                货主方联系人证件号码：
                            </label>
                            <div class="display_inlineBlcok">
                                <input type="text" class="form-control" id="numbers"onkeyup="checkNumber(this)"  addAttr="number"/>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                        <div class="form-group">
                            <label>性别：</label>
                            <div class="display_inlineBlcok sexJson">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="placeHolder" addAttr="sex"></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                </ul>
                                <p></p>
                            </div>
                            <span class="red">*</span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" onclick="holdOwner()">保存</a>
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