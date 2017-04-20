$(function () {
    // 平台账户总额和平台账户可用余额
    $.ajax({
        type:'post',
        url:'/iwuliu/virtualAccountManager/searchAccountAmount',
        cache:'false',
        success:function(object){
            var obj = eval(object);
            var personalAccountSum = obj.personalAccountSum;
            var truckAccountSum = obj.truckAccountSum;
            $("#platAmountSum").html(moneyFormatter(parseFloat(personalAccountSum) + parseFloat(truckAccountSum)));
            $("#personalAccountSum").html(moneyFormatter(personalAccountSum));
            $("#truckAccountSum").html(moneyFormatter(truckAccountSum));
            $("#platAmountUsable").html(moneyFormatter(personalAccountSum - truckAccountSum));
        },
        error:function(){
            alert('There are some errors happened');
        }
    });
    // 加载当前页面菜单
    $(function(){
        $(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
            $(".virtualAccount").parents(".collapse").addClass("in");
            $(".virtualAccount").addClass("menuActive")
        })
    })


    //个人账户总额度明细
    $(".personalAccountDetailClick").click(function () {
        $('#memberAccountTable').bootstrapTable('refresh', {'url': '/iwuliu/virtualAccountManager/personalAccountDetail'});
    })
    //车辆账户总额度
    $(".vehicleAccountDetailClick").click(function () {
        $('#truckAccountTable').bootstrapTable('refresh', {'url': '/iwuliu/virtualAccountManager/truckAccountDetail'});
    })
    // table表格隔行变色
    function rowStyle(row, index) {
        if (index % 2 === 0) {
            return {};
        }
        return {
            css: {"background-color": "#eefff9"}
        };
    }

    // 个人账户明细table页脚
    $(function () {
        var table = $('#memberAccountTable'),
            page = $('#member_page'),
            goBtn = $('#member_goBtn');
        // 跳转到某页
        goBtn.click(function () {
            table.bootstrapTable('selectPage', +page.val());
        });
        $("#memberAccountTable .th-inner").eq(0).append('<span>全选</span>');
    });
    // 车辆账户明细table页脚
    $(function () {
        var table = $('#truckAccountTable'),
            page = $('#truckAccount_page'),
            goBtn = $('#truckAccount_goBtn');
        // 跳转到某页
        goBtn.click(function () {
            table.bootstrapTable('selectPage', +page.val());
        });
        $("#truckAccountTable .th-inner").eq(0).append('<span>全选</span>');
    });

})

// 格式化“余额”列,保留两位小数
function moneyFormatter(value) {
    var f = parseFloat(value);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(value*100)/100;
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