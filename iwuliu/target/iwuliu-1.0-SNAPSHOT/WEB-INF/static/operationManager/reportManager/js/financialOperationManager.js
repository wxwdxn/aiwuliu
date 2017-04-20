/**
 * Created by YK on 2016/12/14.
 */
// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
    $("#financialOperationManager").on("hidden.bs.modal",function(){
        //详情模态框还原需添加这两行
        if($(".financialFullScreen").attr("isClick") == "false"){
            $(".financialFullScreen").click()
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
function financialQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        beginDate: $("#financial_beginTime").val(),
        endDate: $("#financial_endTime").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询
function financial_searchInfo(){
    $('#financialOperationTable').bootstrapTable('refresh', {'url': '/iwuliu/reportManager/financialOperationList'});
    var now = new Date().getTime();//当前时间戳
    var financial_beginTime = new Date(document.getElementById('financial_beginTime').value).getTime();//1的时间戳
    var financial_endTime = new Date(document.getElementById('financial_endTime').value).getTime();//2的时间戳
    if(financial_beginTime > financial_endTime)
    {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("结束日期不能小于开始日期！")
        return false;
    }
    return true;
}

// 重置时间
function financial_resetTime(){
    $('#financial_beginTime').val("");
    $('#financial_endTime').val("");
    $('#financialOperationTable').bootstrapTable('removeAll');
}

// 导出excel
function financialOperation_export(){
    $.ajax({
        type: "POST",
        url: '/iwuliu/financialOperationManager/financialOperationExport',
        success: function () {
            window.location = '/iwuliu/reportManager/financialOperationExport';
        }
    });
}

// 日历
$('.form_datetime').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
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

$(function() {
    // 下拉框
    var mydropdown=new customDropDown($(".company_name"));
    // table
    var table = $('#financialOperationTable'),
        page = $('#financial_page'),
        goBtn = $('#financial_goBtn');
    // 跳转到某页
    goBtn.click(function () {
        table.bootstrapTable('selectPage', +page.val());
    });
    $("#financialOperationTable.th-inner").eq(8).append('<span>全选</span>');
});

