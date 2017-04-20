<%--
  Created by IntelliJ IDEA.
  User: WXW
  Date: 2017/2/16
  Time: 16:55
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
<%--Bootstrap-Datetimepicker CSS文件--%>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/carTypeManager/css/carTypeManager.css"/>
<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/operationManager/carTypeManager/js/carTypeManager.js" type="text/javascript" charset="utf-8"></script>
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
                车厢类型管理
            </h4>
            <div class="infoConditions">
                <form class="form-inline truckTypeSelect">
                    <div class="form-group">
                        <label>货物类型：</label>
                        <div class="display_inlineBlcok logisticsCompany">
                            <button class="btn form-control btn-default dropdown-toggle " type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" >
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                        </div>
                    </div>
                    <div class="QuerytheReset form-group">
                        <label class="sr-only">查询按钮</label>
                        <div class="display_inlineBlcok">
                            <a type="button" onclick="match()" class="btn query">匹配车厢</a>
                            <a type="button" class="btn reset" onclick="resetCarType()">重置</a>
                        </div>
                    </div>
                </form>
                <div class="buttons">
                    <a type="button" class="btn btn_export">
                        导出
                    </a>
                    <a type="button" class="btn btn_detail" onclick="btn_ModalDetail()">
                        详情/编辑
                    </a>
                    <a type="button" class="btn" onclick="btn_ModalNewAdd()">
                        新增
                    </a>
                    <a type="button" class="btn" onclick="btn_ModalMatch()">
                        新增匹配
                    </a>
                    <a type="button" class="btn" onclick="deleteTruckType()">
                        删除
                    </a>
                </div>
                <div class="col-md-12" style="padding: 0;">
                    <%--车厢--%>
                    <table style="width: 100%;" data-toggle="table" class="table table-striped table-bordered table-hover"
                           id="carTypeTable"
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
                            <th data-field="state" data-checkbox="true" data-width="100px"></th>
                            <th data-field="matchId" class="hidden"  data-align="center" data-valign="middle">匹配id </th>
                            <th data-field="carTypeId" class="hidden"  data-align="center" data-valign="middle">货物类型</th>
                            <th data-field="carTypeName"  data-align="center" data-valign="middle">车厢类型</th>
                            <th data-field="carTypeDesc" data-align="center" data-valign="middle">车厢描述</th>
                            <th data-field="carTypeUseable" data-align="center" data-valign="middle">可用状态</th>
                        </tr>
                        </thead>
                    </table>
                    <div class="form-inline" align="center" style="margin: -45px auto 17px;">
                        <input id="cargoTypeTablePage" class="form-control" style="width: 60px;" type="number"
                               value="1" min="1">
                        <button id="cargoTypeTablePagegoBtn" class="btn btn-success">GO</button>
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
            <div class="modal-body">
                <form id="form-group" class="form-inline">
                    <div class="form-group docubleUneditable">
                        <label>车厢名称：</label>
                        <div class="display_inlineBlcok">
                            <input disabled type="text" id="carType" class="form-control "/>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group uneditable">
                        <label>车厢类型描述：</label>
                        <div class="display_inlineBlcok">
                            <input disabled type="text"  id="carTypeDesc" class="form-control "/>
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group docubleUneditable">
                        <label>可用状态：</label>
                        <div class="display_inlineBlcok">
                            <input disabled type="text" id="userable" class="form-control "/>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter elementDisplayNone">
                <a type="button" class="btn hold" onclick="saveEdit()">保存</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal 新增-->
<div class="modal fade bs-example-modal-lg" id="myNewAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe2">新增车厢类型</h4>
            </div>
            <div class="modal-body">
                <h4><span class="red">*号为必填项</span></h4>
                <form id="form-group2" class="form-inline">
                    <div class="form-group">
                        <label>车厢名称：</label>
                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="plateNumber"/>
                            <p></p>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>车厢类型描述：</label>
                        <div class="display_inlineBlcok">
                            <input type="text" class="form-control" id="plateDesc"/>
                            <p></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" onclick="saveAdd()" class="btn hold">保存</a>
                <a type="button" class="btn cancel" data-dismiss="modal">取消</a>
            </div>
        </div>
    </div>
</div>

<!--Modal 新增匹配-->
<div class="modal fade bs-example-modal-lg" id="ModalMatch" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
                <h4 class="modal-title" id="myModalLabel">新增车厢货物匹配</h4>
            </div>
            <div class="modal-body">
                <h4><span class="red">*号为必填项</span></h4>
                <form class="form-inline addMatch">
                    <div class="form-group">
                        <label>车厢类型名称：</label>
                        <div class="display_inlineBlcok truckType">
                            <button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu13" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder" >请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                        </div>
                        <span class="red">*</span>
                    </div>
                    <div class="form-group">
                        <label>车厢描述:</label>
                        <div class="display_inlineBlcok">
                            <input disabled type="text" id="truckTypeDesc" class="form-control" />
                            <p></p>
                        </div>
                    </div>
                    <div class="form-group matchCarType">
                        <label>匹配货物类型：</label>
                        <div class="display_inlineBlcok goodType">
                            <button class="btn form-control btn-default dropdown-toggle" type="button" id="dropdownMenu14" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="placeHolder">请选择</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            </ul>
                        </div>
                        <span onclick="newAddMatch(this)" class="glyphicon glyphicon-plus margin-right"></span>
                        <span class="red">*</span>
                    </div>
                </form>
            </div>
            <div class="modal-footer modalFooter">
                <a type="button" class="btn hold" onclick="saveTruckGoodMatch()">保存</a>
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
<script src="<%=request.getContextPath()%>/static/common/js/jquery.form.js" type="text/javascript" charset="utf-8">
</script>
<%--Bootstrap-table JavaScript 文件以及汉化文件--%>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-table-zh-CN.js"></script>
</html>
