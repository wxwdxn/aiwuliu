//车牌号码验证
var regTicNum = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
//手机号正则
var regExpMob = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; //手机号码正则验证准确版
// 设备编号
var regObd = /^[0-9a-zA-Z]+$/; //只能输入数字和字母

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
});

// 全局
$(function () {
    // 加载当前页面菜单
    $(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
        $(".OBDEquipManager").parents(".collapse").addClass("in");
        $(".OBDEquipManager").addClass("menuActive");
        roleManager();
    })
    // 设备状态
    var equipmentStatics = new customDropDown($(".equipment_status"));
    // 时间查询日历框
    $('.form_datetime').datetimepicker({
        minView: "month",
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        todayBtn: 1,
        autoclose: 1
    });
    // 时间查询日历框,带时分秒
    $('#add_register_time').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii:00',
        todayBtn: 1,
        autoclose: 1
    });
    // 详情编辑中的时间
    $('.detail_register_time').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii:00',
        todayBtn: 1,
        autoclose: 1
    });

    // 表格
    var table = $('#obdEquipmentManagerTable'),
        page = $('#obd_page'),
        goBtn = $('#obd_goBtn');
    // 跳转到某页
    goBtn.click(function () {
        table.bootstrapTable('selectPage', +page.val());
    });
    $(".th-inner").eq(0).append('<span>全选</span>');

    //点击编辑按钮
    $(".edit").bind("click", function() {
        // 设备品牌
        var detail_obd_brand = new customDropDown($(".detail_obd_brand"));
    });

    //新增模态框消失
    $("#myModalNewAdd").on("hidden.bs.modal", function () {
        $("#myModalNewAdd").find("input").val("");
        $("#myModalNewAdd").find("p").html("");
        $("#myModalNewAdd").find(".placeHolder").html("请选择");
        $("#myModalNewAdd").find(".placeHolder").attr("value","");
    })

    //详情模态框消失
    $("#myModalDetail").on("hidden.bs.modal",function(){
        $("#myModalDetail").find(".form-group").addClass("uneditable");
        $("#myModalDetail").find("input").attr("disabled","true");
        $("#myModalDetail").find(".form-group").find("button").attr("disabled","true");
        $("#myModalDetail").find(".edit").attr("isClick","true");
        $("#myModalDetail").find(".edit").removeAttr("disabled");
        $("#myModalDetail").find(".edit").css("background","#69a2b5")
    })

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

//配置查询参数
function obdEquipmentManagerQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        obd_brand: $(".obd_brand").find(".placeHolder").attr("value"),
        obd_id: $("#obd_id").val(),
        equipment_phone_number: $("#equipment_phone_number").val(),
        plate_number: $("#plate_number").val(),
        equipment_status: $(".equipment_status").find(".placeHolder").attr("value"),
        register_time_start: $("#register_time_start").val(),
        register_time_end: $("#register_time_end").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询OBD设备管理列表
function obdEquipmentManagerQuery() {
    $('#obdEquipmentManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/OBDEquipManager/vehicleEquipManagerList'});
}

// 重置功能
function obdEquipmentManagerQueryReset() {
    $(".obd_brand").find(".placeHolder").html("请选择");
    $("#obd_id").val("");
    $("#equipment_phone_number").val("");
    $("#plate_number").val("");
    $(".equipment_status").find(".placeHolder").html("请选择");
    $("#register_time_start").val("");
    $("#register_time_end").val("");
    $('#obdEquipmentManagerTable').bootstrapTable('removeAll');
}

// 点击删除按钮
function obdEquipmentDel() {
    var selects = $('#obdEquipmentManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length != 0) {
        $(".titleInfoDelet").html("确定删除？")
        $("#smallModalInfoDelet").modal();
        $(".confirmDelet").click(function () {
                var binding_number = "";
                for (var i = 0; i < selects.length; i++) {
                    if (i == 0) {
                        binding_number = selects[i].binding_number;
                    } else {
                        binding_number += "," + selects[i].binding_number;
                    }
                };
                $.ajax({
                    type: "post",
                    url: "/iwuliu/OBDEquipManager/obdManagerDel?binding_number=" + binding_number,
                    async: true,
                    success: function (object) {
                        // json 类型的专为对象
                        var obj = eval(object);
                        console.log(obj)
                        //  reload the user data
                        if (obj.result == 1) {
                            $('#obdEquipmentManagerTable').bootstrapTable('refresh');
                            $(".titleInfo").html("删除成功！");
                            $("#smallModalInfo").modal();
                        } else {
                            $(".titleInfo").html("删除失败！");
                            $("#smallModalInfo").modal();
                        }
                    },
                    error: function () {
                        $(".titleInfo").html("系统错误！")
                        $("#smallModalInfo").modal();
                    }
                });
        })
    } else {
        $(".titleInfo").html("请选择一行数据")
        $("#smallModalInfo").modal();
    }
}

//点击详情按钮
var binding_number;
function obdEquipmentDetail() {
    var selects = $('#obdEquipmentManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        binding_number = newSelects[0].binding_number;
        $.ajax({
            type: "post",
            url: "/iwuliu/OBDEquipManager/getOBDTruckBindingInfo",
            data:{binding_number:encodeURI(binding_number)},
            success: function (data) {
                var JsonData = eval(data);
                console.log(JsonData);
                // 让表单上所有p标签提示清空,避免编辑出现的提示保留到详情中
                $("#obdEquipEditForm").find("p").html("");
                $(".detail_truck_brand_id").val(JsonData.truck_brand);
                $(".detail_truck_model_no").val(JsonData.truck_model_name);
                $(".detail_plate_number").val(JsonData.plate_number);
                // 下拉框初始化绑定id
                $(".detail_obd_brand").html(JsonData.obd_brand);
                $(".detail_obd_brand").attr("value",JsonData.obd_brand_value);//绑定id
                $(".detail_obd_id").val(JsonData.obd_id);
                $(".detail_equipment_phone_number").val(JsonData.equipment_phone_number);
                $(".detail_register_time").val(JsonData.register_time.substring(0,JsonData.register_time.length-2));
                if (JsonData.unbinding_time == "" || JsonData.unbinding_time == null || JsonData.unbinding_time == "undefined") {
                    $(".detail_equipment_status").val("已绑定");
                } else {
                    $(".detail_equipment_status").val("可绑定");
                }
                $(".detail_binding_time").val(JsonData.binding_time);
            }
        });
        $("#myModalDetail").modal();
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
        return false;
    }
}

// 点击详情编辑保存按钮
function obdEquipmentEditSave() {
    if(checkSelectNotNull($(".obd_brand_detail").find(".placeHolder"))&checkObdId($(".detail_obd_id"))&
        checkPhone($(".detail_equipment_phone_number"))&checkNotNull($(".detail_register_time"))){
        // 封装表单中的参数
        var obdEquipmentInfoObj = {
            // 下拉框
            "detail_obd_brand":$(".obd_brand_detail").find(".placeHolder").attr("value"),
            // input框
            "detail_obd_id":$(".detail_obd_id").val(),
            "detail_equipment_phone_number":$(".detail_equipment_phone_number").val(),
            "detail_register_time":$(".detail_register_time").val()
        };
        //开始ajax操作，data中传的是表单中的参数
        $.ajax({
            type: "POST",//提交类型
            dataType: "json",//返回结果格式
            url: "/iwuliu/OBDEquipManager/updateOBDInfo",//请求地址
            data:{"obdEquipmentInfo":JSON.stringify(obdEquipmentInfoObj),
                "binding_number":encodeURI(binding_number)
            },
            success:function(data){
                var obj = eval(data);
                if(obj.result==1){
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal",function(e){
                        $("body").addClass("modal-open");
                        $('#myModalDetail').modal('hide');
                        obdEquipmentManagerQuery();
                    })
                }else if (obj.result==2){
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("设备编号已存在！");
                    $("#smallModalInfo").on("hidden.bs.modal",function(e){
                        $("body").addClass("modal-open");
                    })
                    return false;
                } else {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存失败！");
                    $("#smallModalInfo").on("hidden.bs.modal",function(e){
                        $("body").addClass("modal-open");
                    })
                    return false;
                }
            },
            error:function(){
                //bootstrap提示框
                $("#smallModalInfo").modal();
                $("#smallModalInfo").find(".titleInfo").html("系统异常！");
                $("#smallModalInfo").on("hidden.bs.modal",function(e){
                    $("body").addClass("modal-open");
                })
                return false;
            }
        });
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请填写数据！")
        $("#smallModalInfo").on("hidden.bs.modal",function(e){
            $("body").addClass("modal-open");
        })
        return false;
    }
}

//点击新增按钮
function obdEquipmentNewAdd() {
    $("#myModalNewAdd").modal();
    //车辆品牌
    var vehicleBrand = new customDropDown($(".vehicleBrand"));
    //车辆型号
    var vehicleType = new customDropDown($(".vehicleType"));
    //设备品牌
    var equipmentBrand = new customDropDown($(".equipmentBrand"));


}

// 点击新增保存按钮
function obdEquipmentSave() {
    if (checkSelectNotNull($('.add_truck_brand_id').find(".placeHolder")) & checkplate($("#add_plate_number")) &
        checkSelectNotNull($('.add_obd_brand').find(".placeHolder")) & checkObdId($("#add_obd_id"))
        & checkPhone($("#add_equipment_phone_number")) & checkNotNull($("#add_register_time"))) {
        // 封装表单中的参数
        var obdInfoObj = {
            // 下拉框
            "add_truck_brand_id": $('.add_truck_brand_id').find(".placeHolder").attr("value"),
            "add_truck_model_no": $('.add_truck_model_no').find(".placeHolder").attr("value"),
            "add_obd_brand": $('.add_obd_brand').find(".placeHolder").attr("value"),
            // input框
            "add_plate_number": $("#add_plate_number").val(),
            "add_obd_id": $("#add_obd_id").val(),
            "add_equipment_phone_number": $("#add_equipment_phone_number").val(),
            "add_register_time": $("#add_register_time").val()
        };
        $.ajax({
            type: "post",
            url: "/iwuliu/OBDEquipManager/saveEquipment",
            data: {"obdInfo": JSON.stringify(obdInfoObj)},
            async: true,
            success: function (object) {
                // json 类型的专为对象
                var obj = eval(object);
                //  reload the user data
                if (obj.result == 1) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#myModalNewAdd').modal('hide');
                        obdEquipmentManagerQuery();
                    })
                } else if (obj.result == 0) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("OBD设备重绑成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                    })
                } else if (obj.result == 2) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("车牌不属于平台下的车辆！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                    })
                } else if (obj.result == 3) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("该车已绑定obd设备！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                    })
                    return false;
                } else if (obj.result == 4) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("obd已绑定设备！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                    })
                    return false;
                }
                else {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存失败！");
                    $("#smallModalInfo").on("hidden.bs.modal",function(e){
                        $("body").addClass("modal-open");
                    })
                    return false;
                }
            },
            error: function () {
                //bootstrap提示框
                $("#smallModalInfo").modal();
                $("#smallModalInfo").find(".titleInfo").html("系统异常！");
                $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                    $("body").addClass("modal-open");
                })
                return false;
            }
        });
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请填写数据！")
        $("#smallModalInfo").on("hidden.bs.modal", function (e) {
            $("body").addClass("modal-open");
        })
        return false;
    }


}

// =================查询条件的下拉框
// 获取设备品牌
$.ajax({
    type: 'post',
    url: "/iwuliu/dictionaryManager/obdBrandJson",
    dataType: 'json',
    cache: false,
    success: function (data) {
        var str = "";
        for (var i = 0; i < data.length; i++) {
            str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
        }
        $('.obd_brand').find(".dropdown-menu").html(str);
        var obd_brand = new customDropDown($(".obd_brand"))
    }
});

// ====================新增页面的下拉框
// 车辆品牌和车辆型号
$.ajax({
    type: 'post',
    url: '/iwuliu/vehicleManager/truckBrandJson',
    dataType: 'json',
    cache: 'false',
    success: function (data) {
        var str = "";
        for (var i = 0; i < data.length; i++) {
            str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
        }
        $(".add_truck_brand_id").find(".dropdown-menu").html(str);
        //车辆品牌
        var add_truck_brand_id = new customDropDown($(".add_truck_brand_id"));
        //根据车辆品牌获取车辆型号
        $(".add_truck_brand_id").find(".dropdown-menu li").bind("click", function () {
            var add_truck_brand_id = $(this).attr("value");
            if (add_truck_brand_id != '' && add_truck_brand_id != 'undefined') {
                $.ajax({
                    type: "POST",
                    url: "/iwuliu/vehicleManager/truckModelList?truck_brand_id=" + add_truck_brand_id,
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        var str = "";
                        for (var i = 0; i < data.length; i++) {
                            str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                        }
                        //车辆型号
                        $(".add_truck_model_no").find(".dropdown-menu").html("");
                        $(".add_truck_model_no").find(".placeHolder").html("请选择");
                        $(".add_truck_model_no").find(".dropdown-menu").html(str);
                        var add_truck_model_no = new customDropDown($(".add_truck_model_no"));
                    }
                });
            }
        })
    }
});

// 获取设备品牌
$.ajax({
    type: 'post',
    url: "/iwuliu/dictionaryManager/obdBrandJson",
    dataType: 'json',
    cache: false,
    success: function (data) {
        var str = "";
        for (var i = 0; i < data.length; i++) {
            str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
        }
        $('.add_obd_brand').find(".dropdown-menu").html(str);
        var add_obd_brand = new customDropDown($(".add_obd_brand"))
    }
});

// ======================>>详情编辑页面的下拉框
// 获取设备品牌
$.ajax({
    type: 'post',
    url: "/iwuliu/dictionaryManager/obdBrandJson",
    dataType: 'json',
    cache: false,
    success: function (data) {
        var str = "";
        for (var i = 0; i < data.length; i++) {
            str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
        }
        $('.obd_brand_detail').find(".dropdown-menu").html(str);
        var obd_brand_detail = new customDropDown($(".obd_brand_detail"))
    }
});

// =====================>>字段即时验证
// 车牌号的校验
function checkplate(ele) {
    var total =  $(ele).val();
    $(ele).val((total.trim()).toLocaleUpperCase());
    if (total != "") {
        if (regTicNum.test((total.trim()).toLocaleUpperCase())) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的车牌号！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入车牌号!");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}
// 设备编号的校验
function checkObdId(ele) {
    var total =  $(ele).val();
    $(ele).val((total.trim()).toLocaleUpperCase());
    if (total != "") {
        if (regObd.test(total)) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的设备编号！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入设备编号!");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }

}

// 手机号的校验
function checkPhone(ele) {
    var total = $(ele).val();
    if (total != "") {
        if (regExpMob.test(total)) {
            //$(ele).css("border","1px solid #ccc");
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的手机号码！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入手机号码!");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}

// 不能为空的正则校验(input输入框使用)
function checkNotNull(inp) {
    var _value = inp.val();
    var _p = $(inp).parents(".form-group").find("p");
    if (_value != '') {
        _p.html("");
        return true;
    } else {
        _p.css("color", "red");
        _p.html("不能为空！");
        return false;
    }
}
// 不能为空的正则校验(下拉框使用)
function checkSelectNotNull(inp) {
    var _value = inp.attr("value");
    var _p = $(inp).parents(".form-group").find("p");
    if (_value != '' && _value != undefined) {
        _p.html("");
        return true;
    } else {
        _p.css("color", "red");
        _p.html("不能为空！");
        return false;
    }
}





