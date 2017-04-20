/**
 * Created by YK on 2016/12/14.
 */
// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
    $("#platformAndSettlementManager").on("hidden.bs.modal",function(){
        //详情模态框还原需添加这两行
        if($(".platFullScreen").attr("isClick") == "false"){
            $(".platFullScreen").click()
        }
    })
});

// table表格隔行变色
function rowStyle(row, index) {
    if (index % 2 === 0) {
        return{};
    }
    return {
        css: {"background-color": "#eefff9"}
    };
}

//配置参数
function platformSettlementQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        beginDate: $("#plat_beginTime").val(),
        endDate: $("#plat_endTime").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 结算单汇总表导出excel
function plat_export(){
    $.ajax({
        type: "POST",
        url: '/iwuliu/reportManager/platformExport',
        success: function () {
            window.location = '/iwuliu/reportManager/platformExport';
        }
    });
}

// 结算单明细表
function settlementDetail_export(){
    $.ajax({
        type: "POST",
        url: '/iwuliu/reportManager/settlementExport',
        success: function () {
            window.location = '/iwuliu/reportManager/settlementExport';
        }
    });
}

// 派单明细表
function dispatchDetail_export(){
    $.ajax({
        type: "POST",
        url: '/iwuliu/reportManager/dispatchExport',
        success: function () {
            window.location = '/iwuliu/reportManager/dispatchExport';
        }
    });
}
//配置参数
function querySettlementDetailParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        settlementNum: $("#settlementNum").val(),
        itemName: $("#itemName").val(),
        beginDateDetail: $("#begin_time_detail").val(),
        endDateDetail: $("#end_time_detail").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 日历
$('#plat_beginTime').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1
});
$('#plat_endTime').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1
});
$('#begin_time_detail').datetimepicker({
    minView: "day", //选择日期后，会再跳转去选择时分秒
    language:  'zh-CN',
    format: 'yyyy-mm-dd hh:mm:ss',
    todayBtn:  1,
    autoclose: 1
});
$('#end_time_detail').datetimepicker({
    minView: "day", //选择日期后， 会再跳转去选择时分秒
    language:  'zh-CN',
    format: 'yyyy-mm-dd hh:ii:ss',
    todayBtn:  1,
    autoclose: 1
});
// 合计
function sumFormatter(data) {
    field = this.field;
    return '<span style="color: #FF0000" >'+data.reduce(function(sum, row) {
            var salary = row[field];
            if (salary=="undefined"||salary==null){
                salary = '0';
            }
            return sum + (+salary);
        }, 0)+'</span>';
}

function totalTextFormatter(data) {
    return '<span style="color: #FF0000" >总计</span>';
}
// 格式化“操作”列
function operationFormatter(){
    return '<a href="#" onclick="querySettlementDetail()">' +
        '<span style="color: #FF0000" >明细</span></a>';
}
// 格式化“订单号”列
function orderFormatter(value, row){
    return '<a href="#" onclick="queryDispatch()">' +
        '<span style="color: #FF0000" >'+value+'</span></a>';
}

// 结算汇总表查询
function plat_searchInfo(){
    $('#platformSettlementSummaryTable').bootstrapTable('refresh');
}
// 点击“明细”
function querySettlementDetail(){
    $('#settlementDetailTable').bootstrapTable('refresh',{'url':'/iwuliu/reportManager/settlementDetailList'});
}
// 查询结算单明细
function settlementDetail_searchInfo(){
    $('#settlementDetailTable').bootstrapTable('refresh');
}
// 查询派单明细
function queryDispatch(){
    $('#dispatchDetailTable').bootstrapTable('refresh',{'url':'/iwuliu/reportManager/dispatchDetailList'});
}
// 重置时间
function plat_resetTime(){
    $('#plat_beginTime').val("");
    $('#plat_endTime').val("");
    $('#platformSettlementSummaryTable').bootstrapTable('refresh');
}
function settlementDetail_resetTime(){
    $('#begin_time_detail').val("");
    $('#end_time_detail').val("");
    $('#settlementNum').val("");
    $('#itemName').val("");
    $('#settlementDetailTable').bootstrapTable('removeAll');
}

$(function() {
    var platformSettlementSummaryTable = $('#platformSettlementSummaryTable'),
        settlementDetailTable=$('#settlementDetailTable'),
        dispatchDetailTable=$('#dispatchDetailTable'),
        plat_page = $('#plat_page'),
        plat_goBtn = $('#plat_goBtn'),
        settlementDetail_page = $('#settlementDetail_page'),
        settlementDetail_goBtn = $('#settlementDetail_goBtn'),
        dispatchDetail_page = $('#dispatchDetail_page'),
        dispatchDetail_goBtn = $('#dispatchDetail_goBtn')
        ;
    // 结算单汇总表跳转到某页
    plat_goBtn.click(function () {
        platformSettlementSummaryTable.bootstrapTable('selectPage', +plat_page.val());
    });
    // 结算单明细表跳转到某页
    settlementDetail_goBtn.click(function () {
        settlementDetailTable.bootstrapTable('selectPage', +settlementDetail_page.val());
    });
    //派单明细表跳转到某页
    settlementDetail_goBtn.click(function () {
        dispatchDetailTable.bootstrapTable('selectPage', +settlementDetail_page.val());
    });
    $("#platformSettlementSummaryTable.th-inner").eq(0).append('<span>全选</span>');
});
