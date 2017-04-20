var verify_status = "";
var verify_refused_reason = "";
$(function () {
    $(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
        $(".vehicleManager").parents(".collapse").addClass("in");
        $(".vehicleManager").addClass("menuActive");
        roleManager();
    });

    //下拉框
    var organizationType = new customDropDown($(".vehicleManager_organizationType"));
    var status = new customDropDown($(".vehicleManager_status"));
    //点击机构类型显示不同的状态
    $(".vehicleManager_organizationType").find(".dropdown-menu>li>a").bind("click", function () {
        var _text = $(this).text();
        switch (_text) {
            case "全部":
                $(".nameOfinstitution").removeClass("sr-only");
                $(".TheOwnerName").removeClass("sr-only")
                break;
            case "物流公司":
                $(".nameOfinstitution").removeClass("sr-only");
                $(".TheOwnerName").addClass("sr-only")
                break;
            case "司机":
                $(".nameOfinstitution").addClass("sr-only");
                $(".TheOwnerName").removeClass("sr-only")
                break;
            default:
                break;
        }
    });

    //详情模态框消失
    $("#myModalDetail").on("hidden.bs.modal", function () {
        $("#myModalDetail").find(".form-group").addClass("uneditable");
        $("#myModalDetail").find("input").attr("disabled", "true");
        $("#myModalDetail").find(".form-group").find("button").attr("disabled", "true");
        $("#myModalDetail").find(".edit").attr("isClick", "true");
        $("#myModalDetail").find(".edit").removeAttr("disabled");
        $("#myModalDetail").find(".edit").css("background", "#69a2b5")
    })

    //点击编辑按钮
    $(".edit").bind("click", function () {
        // 审核状态
        var mydropdownvehicleManager_status = new customDropDown($(".detail_verify_status"));
        if (verify_status == "不合格") {
            $(".verify_refused_reason").html('<textarea name="" rows="5" cols="100" id="verify_refused_reason">' + verify_refused_reason + '</textarea><p></p>')
        } else {
            $(".verify_refused_reason").html('<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason"></textarea><p></p>')
        }
    });

    // 获取车辆品牌
    $.ajax({
        type: 'post',
        url: "/iwuliu/vehicleManager/truckBrandJson",
        dataType: 'json',
        cache: false,
        success: function (data) {
            var str = "<li value=''><a href='javascript:;'>全部</a></li>";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".vehicleManager_truckBrand").find(".dropdown-menu").html(str);
            var truckBrand = new customDropDown($(".vehicleManager_truckBrand"));
            // 根据品牌获取型号
            $(".vehicleManager_truckBrand").find(".dropdown-menu li").bind("click", function () {
                var truckBrand = $(this).attr("value");
                if (truckBrand != '' && truckBrand != 'undefined') {
                    $.ajax({
                        type: "POST",
                        url: "/iwuliu/vehicleManager/truckModelList?truck_brand_id=" + truckBrand,
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            var str = "<li value=''><a href='javascript:;'>全部</a></li>";
                            for (var i = 0; i < data.length; i++) {
                                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                            }
                            // 型号
                            $(".vehicleManager_truckModelName").find(".dropdown-menu").html("");
                            $(".vehicleManager_truckModelName").find(".placeHolder").html("请选择");
                            $(".vehicleManager_truckModelName").find(".dropdown-menu").html(str);
                            //型号
                            var city = new customDropDown($(".vehicleManager_truckModelName"));
                        }
                    });
                }
            })
        },
    });

    // 获取车类型
    $.ajax({
        type: 'post',
        url: "/iwuliu/vehicleManager/truckTypeIdJson",
        dataType: 'json',
        cache: false,
        success: function (data) {
            var str = "<li value=''><a href='javascript:;'>全部</a></li>";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".vehicleManager_truckType").find(".dropdown-menu").html(str);
            var truckType = new customDropDown($(".vehicleManager_truckType"));
        },
        error: function () {
            alert('There are some errors happened');
        }
    });

    var table = $('#vehicleManagerTable'),
        vehicle_page = $('#vehicle_page'),
        vehicle_goBtn = $('#vehicle_goBtn');
    // 跳转到某页
    vehicle_goBtn.click(function () {
        table.bootstrapTable('selectPage', +vehicle_page.val());
    });
    $(".th-inner").eq(0).append('<span>全选</span>');

})

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
});

//点击详情按钮
function btn_ModalDetail() {
    var selects = $('#vehicleManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        var plate_number = newSelects[0].plate_number;
        var organization_type = newSelects[0].organization_type;
        sessionStorage.setItem("plate_number", plate_number)
        sessionStorage.setItem("organization_type", organization_type)
        $.ajax({
            type: "post",
            url: "/iwuliu/vehicleManager/vehicleDetail",
            data: {plate_number: encodeURI(plate_number), organization_type: encodeURI(organization_type)},
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                var JsonData = JSON.parse(data);
                console.log(JsonData);
                // 让表单上所有p标签提示清空,避免编辑出现的提示保留到详情中
                $("#vehicleManagementEditForm").find("p").html("");
                $("#plate_number").val(JsonData.plate_number);
                $("#truck_brand").val(JsonData.truck_brand);
                $("#truck_model_name").val(JsonData.truck_model_name);
                $("#truck_carriage_type").val(JsonData.truck_carriage_type);
                $("#truck_type").val(JsonData.truck_type);
                $("#fuel_type").val(JsonData.fuel_type);
                $("#truck_length").val(JsonData.truck_length);
                $("#vehicle_identify_number").val(JsonData.vehicle_identify_number);
                $("#load_weight").val(JsonData.load_weight);
                $("#engine_number").val(JsonData.engine_number);
                $("#driving_licence_first_page_save_path").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driving_licence_first_page_save_path + ">");
                $("#driving_licence_second_page_save_path").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driving_licence_second_page_save_path + ">");
                $("#truck_pic_save_path").html("<span><img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.truck_first_pic_save_path + ">"
                    + "<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.truck_second_pic_save_path + ">"
                    + "<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.truck_third_pic_save_path + "></span>");
                $("#transport_line").val(JsonData.transport_line);
                $("#organization_type").val(JsonData.organization_type);
                $("#organization_name").val(JsonData.organization_name);
                $("#owner_name").val(JsonData.owner_name);
                $("#person_mobile_phone").val(JsonData.person_mobile_phone);
                $("#id_card_number").val(JsonData.id_card_number);
                $("#id_card_front_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.id_card_front_pic_id + ">");
                $("#id_card_back_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.id_card_back_pic_id + ">");
                $("#denied_schedule_count").val(JsonData.denied_schedule_count);
                $("#operate_status").val(JsonData.operate_status);
                $("#operate_status_changed_time").val(JsonData.operate_status_changed_time);
                $("#denied_schedule_last_time").val(JsonData.denied_schedule_last_time);
                $("#submit_verify_time").val(JsonData.submit_verify_time);
                $("#verify_passed_time").val(JsonData.verify_passed_time);
                $("#verify_refused_time").val(JsonData.verify_refused_time);
                $("#verify_refused_reason").val(JsonData.verify_refused_reason);
                $(".verify_status").html(JsonData.verify_status);
                verify_status = JsonData.verify_status;
                $(".verify_refused_reason").html('<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason">' + (JsonData.verify_refused_reason) + '</textarea><p></p>');
                verify_refused_reason = JsonData.verify_refused_reason;
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

//点击图片放大
function ImgBig(ele) {
    var _src = $(ele).attr("src");
    $(".modalImg").html("<img src=" + _src + "/>");
    $('#ModalImg').modal();
    //图片放大模态框隐藏后触发的事件
    $("#ModalImg").on("hidden.bs.modal", function (e) {
        $("body").addClass("modal-open")
    })
}

// table表格隔行变色
function rowStyle(row, index) {
    if (index % 2 === 0) {
        return {};
    }
    return {
        css: {"background-color": "#eefff9"}
    };
}

//配置参数
function vehicleQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        plate_number: $("#vehicleManager_plateNumber").val(),
        organization_type: $(".vehicleManager_organizationType").find(".placeHolder").attr("value"),
        organization_name: $("#vehicleManager_organizationName").val(),
        owner_name: $("#vehicleManager_ownerName").val(),
        truck_brand: $(".vehicleManager_truckBrand").find(".placeHolder").attr("value"),
        truck_model_name: $(".vehicleManager_truckModelName").find(".placeHolder").attr("value"),
        truck_type: $(".vehicleManager_truckType").find(".placeHolder").attr("value"),
        start_city: $("#vehicleManager_startCity").val(),
        end_city: $("#vehicleManager_endCity").val(),
        verify_status: $(".vehicleManager_status").find(".placeHolder").attr("value"),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询车辆管理列表
function vehicleManagerQuery() {
    $('#vehicleManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/vehicleManager/vehicleList'});
}

// 查询重置
function vehicleManagerQueryReset() {
    $('#vehicleManager_plateNumber').val("");
    $('#vehicleManager_organizationName').val("");
    $('#vehicleManager_ownerName').val("");
    $('#vehicleManager_startCity').val("");
    $('#vehicleManager_endCity').val("");
    $('.placeHolder').html("请选择");
    $('.placeHolder').attr("value", "");
    $('.vehicleManager_organizationType').find(".placeHolder").html("全部");
    $('.vehicleManager_organizationType').find(".placeHolder").attr("value", "全部");
    $(".vehicleManager_truckModelName").find(".dropdown-menu").html("");
    $(".sr-only").removeClass("sr-only")
    $('#vehicleManagerTable').bootstrapTable('removeAll');
}

//goBtn按钮无数据时隐藏
var webservice = $('#vehicleManagerTable').attr("data-url");
$.ajax({
    url: webservice,
    success: function (data) {
        if (data.length == 0) {
            $(".form-inline").css("display", "none");
        }
    }
});
//==============================编辑页面
//获取车辆审核状态
$.ajax({
    type: 'post',
    url: '/iwuliu/dictionaryManager/truckStatusJson',
    dataType: 'json',
    cache: 'false',
    success: function (data) {
        var str = "";
        for (var i = 0; i < data.length; i++) {
            str += "<li onclick='undesire(this)' value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
        }
        $(".detail_verify_status").find(".dropdown-menu").html(str);
        var detailVerifyStatus = new customDropDown($(".detail_verify_status"));
    }
});

//判断审核不通过填写审核拒绝理由
function undesire(ele) {
    var _text = $(ele).attr("value");
    if (_text == 3) {
        $(".verify_refused_reason").html('<textarea name="" rows="5" cols="100" id="verify_refused_reason"></textarea><p></p>')
    } else {
        $(".verify_refused_reason").html('<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason"></textarea><p></p>')
    }
}

// 点击编辑保存按钮
function editSave() {
    //开始ajax操作，data中传的是表单中的参数
    var plate_number = $("#plate_number").val();
    var verify_status = $(".detail_verify_status").find(".placeHolder").attr("value");
    var verify_refused_reason = $("#verify_refused_reason").val();
    $.ajax({
        type: "POST",//提交类型
        dataType: "json",//返回结果格式
        url: "/iwuliu/vehicleManager/updateVehicleInfo",//请求地址
        data: {
            "plate_number": plate_number,
            "verify_status": verify_status,
            "verify_refused_reason": verify_refused_reason
        },
        success: function (data) {
            var obj = eval(data);
            if (obj.result == 0) {
                //bootstrap提示框
                $("#smallModalInfo").modal();
                $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                $('#vehicleManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/vehicleManager/vehicleList'});
                $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                    $("body").addClass("modal-open");
                    $('#myModalDetail').modal('hide');
                    companyManagerQuery();
                })
            } else {
                //bootstrap提示框
                $("#smallModalInfo").modal();
                $("#smallModalInfo").find(".titleInfo").html("保存失败！");
                $("#smallModalInfo").on("hidden.bs.modal", function (e) {
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
}