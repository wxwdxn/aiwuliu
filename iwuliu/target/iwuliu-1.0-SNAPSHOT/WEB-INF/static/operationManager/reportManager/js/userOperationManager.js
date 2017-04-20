/**
 * Created by YK on 2016/12/14.
 */
// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
    $("#userOperationManager").on("hidden.bs.modal",function(){
        //详情模态框还原需添加这两行
        if($(".userFullScreen").attr("isClick") == "false"){
            $(".userFullScreen").click()
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
function queryUserOperationParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        beginDate: document.getElementById('use_beginTime').value,
        endDate: document.getElementById('use_endTime').value,
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 日历
$('.form_datetime').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1
});

// 查询
function user_searchInfo(){
    var now = new Date().getTime();//当前时间戳
    var use_beginTime = new Date(document.getElementById('use_beginTime').value).getTime();//1的时间戳
    var use_endTime = new Date(document.getElementById('use_endTime').value).getTime();//2的时间戳
    if(use_beginTime > use_endTime)
    {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("结束日期不能小于开始日期！")
        return false;
    }
    $('#userOperationTable').bootstrapTable('refresh', {'url': '/iwuliu/reportManager/userOperationList'});
    return true;
}

// 重置时间
function user_resetTime() {
    $('#use_beginTime').val("");
    $('#use_endTime').val("");
    $('#userOperationTable').bootstrapTable('removeAll');
}

// 导出excel
function userOperation_export(){
    $.ajax({
        type: "POST",
        url: '/iwuliu/reportManager/userOperationExport',
        success: function () {
            window.location = '/iwuliu/reportManager/userOperationExport';
        }
    });
}

// 全局
$(function() {
    var table = $('#userOperationTable'),
        page = $('#user_page'),
        goBtn = $('#user_goBtn');
    // 导出
    $('#toolbar').find('select').change(function() {
        table.bootstrapTable('refreshOptions', {
            exportDataType: $(this).val()
        });
    });
    // 跳转到某页
    goBtn.click(function () {
        table.bootstrapTable('selectPage', +page.val());
    });
    $("#userOperationTable.th-inner").eq(5).append('<span>全选</span>');
});