
$(function() {
    //账户类型下拉
    var settleStatus = new customDropDown($(".settleStatus"));
    var checkStatus = new customDropDown($(".checkStatus"));

    // 加载当前页面菜单
    $(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
        $(".withdrawalApproval").parents(".collapse").addClass("in");
        $(".withdrawalApproval").addClass("menuActive");
        roleManager()
    });
    // 表格go跳转
    var table = $('#withdrawalApprovalTable'),
        page = $('#withdrawal_page'),
        goBtn = $('#withdrawal_goBtn');
    // 跳转到某页
    goBtn.click(function () {
        table.bootstrapTable('selectPage', +page.val());
    });
    $("#withdrawalApprovalTable .th-inner").eq(0).append('<span>全选</span>');

})

$(function () {

    // 时间查询日历框
    $('.form_datetime').datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        todayBtn: 1,
        autoclose: 1
    });

})

//配置参数
function withdrawalApprovalQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        transaction_no:$("#transactionNo").val(),
        order_no:$("#orderNo").val(),
        account_name:$("#accountName").val(),
        settle_status:$(".settleStatus").find(".placeHolder").text(),
        transaction_name:$("#transactionName").val(),
        check_status:$(".checkStatus").find(".placeHolder").text(),
        start_time: $("#start_time").val(),
        end_time: $("#end_time").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询按钮
function queryWithdrawalInfo() {
    $('#withdrawalApprovalTable').bootstrapTable('refresh', {'url': '/iwuliu/withdrawalApprovalManager/withdrawalApplyList'});
}
//重置按钮
function withdrawReset(){
       $("#transactionNo").val("");
       $("#orderNo").val("");
       $("#accountName").val("");
      $(".settleStatus").find(".placeHolder").text("请选择");
      $("#transactionName").val("");
      $(".checkStatus").find(".placeHolder").text("请选择");
      $("#start_time").val("");
      $("#end_time").val("");
    $('#withdrawalApprovalTable').bootstrapTable('removeAll')
}

// 格式化“余额”列,保留两位小数
function moneyFormatter(value) {
    var f = parseFloat(value);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(value * 100) / 100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}
