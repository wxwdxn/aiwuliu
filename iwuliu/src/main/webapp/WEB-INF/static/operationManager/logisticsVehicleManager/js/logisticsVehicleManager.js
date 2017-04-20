// 车牌号正则
var regExpPlate = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
// 数字
var regNum= /^\+?[0-9][0-9]*$/;

$(function () {
    $(".leftMenu").load("/iwuliu/welcomeManager/home", function () {
        $(".logisticsVehicleManager").parents(".collapse").addClass("in");
        $(".logisticsVehicleManager").addClass("menuActive")
        roleManager();
    });
    //有无司机管理者
    new customDropDown($(".ownerMember"));
    $(".ownerMember").find(".dropdown-menu").find("li").bind("click", function () {
        console.log($(this).index());
        var _index = $(this).index();
        switch (_index) {
            case 0:
                $(".manager").removeClass("hidden");
                break;
            case 1:
                $(".manager").removeClass("hidden");
                break;
            case 2:
                $(".manager").addClass("hidden");
                break;
        }
    })


    // 勾选选择设定的车辆管理者
    $("#logisticsVehicleManagerTable").bootstrapTable({
        onCheck:function(row){
            var selects = $('#logisticsVehicleManagerTable').bootstrapTable('getSelections'); //获取表选择的行
            if (selects.length == 1) {
                var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
                var person_name = newSelects[0].person_name;
                $("#manager_member_name").val(person_name);
            } else {
                $("#manager_member_name").val("");
            }
        }
    });
    // 取消勾选选择设定的车辆管理者
    $("#logisticsVehicleManagerTable").bootstrapTable({
        onUncheck:function(row){
            var selects = $('#logisticsVehicleManagerTable').bootstrapTable('getSelections'); //获取表选择的行
            if (selects.length == 1) {
                $("#manager_member_name").val("");
            } else {
                $("#manager_member_name").val("");
            }
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
                            $(".vehicleManager_truckModel").find(".dropdown-menu").html("");
                            $(".vehicleManager_truckModel").find(".placeHolder").html("全部");
                            $(".vehicleManager_truckModel").find(".dropdown-menu").html(str);
                            //型号
                            var city = new customDropDown($(".vehicleManager_truckModel"));
                        }
                    });
                }
            })
        },
    });

    // 获取车厢类型
    $.ajax({
        type: 'post',
        url: "/iwuliu/truckCarriageTypeManager/truck_carriage_type_idJson",
        dataType: 'json',
        cache: false,
        success: function (data) {
            var str = "<li value=''><a href='javascript:;'>全部</a></li>";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".vehicleManager_truckCarriageType").find(".dropdown-menu").html(str);
            var truckType = new customDropDown($(".vehicleManager_truckCarriageType"));
        }
    });

    var table = $('#logisticsVehicleTable'),
        vehicle_page = $('#vehicle_page'),
        vehicle_goBtn = $('#vehicle_goBtn');
    // 跳转到某页
    vehicle_goBtn.click(function () {
        table.bootstrapTable('selectPage', +vehicle_page.val());
    });
    $("#logisticsVehicleTable .th-inner").eq(0).append('<span>全选</span>');

    var table = $('#logisticsVehicleDriverTable'),
        vehicleDriver_page = $('#vehicleDriver_page'),
        vehicleDriver_goBtn = $('#vehicleDriver_goBtn');
    // 跳转到某页
    vehicle_goBtn.click(function () {
        table.bootstrapTable('selectPage', +vehicleDriver_page.val());
    });
    $("#logisticsVehicleDriverTable .th-inner").eq(0).append('<span>全选</span>');

    var table = $('#logisticsVehicleManagerTable'),
        vehicleManager_page = $('#vehicleManager_page'),
        vehicleManager_goBtn = $('#vehicleManager_goBtn');
    // 跳转到某页
    vehicle_goBtn.click(function () {
        table.bootstrapTable('selectPage', +vehicleManager_page.val());
    });
    $("#logisticsVehicleManagerTable .th-inner").eq(0).append('<span>单选</span>');

    //新增模态框消失
    $("#myModalNewAdd").on("hidden.bs.modal", function () {
        $("#myModalNewAdd").find("input").val("");
        $("#myModalNewAdd").find(".placeHolder").html("请选择");
        $("#myModalNewAdd").find(".dropdown-menu").html("");
        $("#myModalNewAdd").find("p").html("");
        $("#myModalNewAdd").find(".formgroupImg").find(".display_inlineBlcok").html("点击右侧按钮上传图片")
    })

    //详情模态框消失
    $("#myModalDetail").on("hidden.bs.modal", function () {
        $("#myModalDetail").find(".form-group").addClass("uneditable");
        $("#myModalDetail").find("input").attr("disabled", "true");
        $("#myModalDetail").find(".form-group").find("button").attr("disabled", "true");
        $("#myModalDetail").find(".edit").attr("isClick", "true");
        $("#myModalDetail").find(".edit").removeAttr("disabled");
        $("#myModalDetail").find(".edit").css("background", "#69a2b5");
        $("#myModalDetail").find(".modal-body").find("a.btn").addClass("hidden")
    })

    //点击编辑按钮
    $(".edit").bind("click", function () {
        $("#myModalDetail").find(".hidden").removeClass("hidden");
        //获取车辆类型
        $.ajax({
            type: 'post',
            url: '/iwuliu/dictionaryManager/truckTypeJson',
            dataType: 'json',
            cache: 'false',
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_truck_type").find(".dropdown-menu").html(str);
                var add_truckType = new customDropDown($(".detail_truck_type"));
            }
        });

        // 获取车辆品牌
        $.ajax({
            type: 'post',
            url: "/iwuliu/vehicleManager/truckBrandJson",
            dataType: 'json',
            cache: false,
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_truck_brand").find(".dropdown-menu").html(str);
                var truckBrand = new customDropDown($(".detail_truck_brand"));
                // 根据品牌获取型号
                $(".detail_truck_brand").find(".dropdown-menu li").bind("click", function () {
                    var truckBrand = $(this).attr("value");
                    if (truckBrand != '' && truckBrand != 'undefined') {
                        $.ajax({
                            type: "POST",
                            url: "/iwuliu/vehicleManager/truckModelList?truck_brand_id=" + truckBrand,
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                var str = "";
                                for (var i = 0; i < data.length; i++) {
                                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                                }
                                // 型号
                                $(".detail_truck_model_name").find(".dropdown-menu").html("");
                                $(".detail_truck_model_name").find(".placeHolder").html("请选择");
                                $(".detail_truck_model_name").find(".dropdown-menu").html(str);
                                //型号
                                var city = new customDropDown($(".detail_truck_model_name"));
                            }
                        });
                    }
                })
            }
        });

        //获取车厢类型
        $.ajax({
            type: 'post',
            url: '/iwuliu/truckCarriageTypeManager/truck_carriage_type_idJson',
            dataType: 'json',
            cache: 'false',
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_truck_carriage_type").find(".dropdown-menu").html(str);
                var add_truckCarriageType = new customDropDown($(".detail_truck_carriage_type"));
            }
        });

        //获取车长
        $.ajax({
            type: 'post',
            url: '/iwuliu/dictionaryManager/truckLengthIdJson',
            dataType: 'json',
            cache: 'false',
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_truck_length").find(".dropdown-menu").html(str);
                var add_truckLength = new customDropDown($(".detail_truck_length"));
            }
        });

        //获取燃料类型
        $.ajax({
            type: 'post',
            url: '/iwuliu/dictionaryManager/truckFuelTypejson',
            dataType: 'json',
            cache: 'false',
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_fuel_type").find(".dropdown-menu").html(str);
                var add_fuelType = new customDropDown($(".detail_fuel_type"));
            }
        });

        //获取保险公司
        $.ajax({
            type: 'post',
            url: '/iwuliu/dictionaryManager/insuranceCompanyjson',
            dataType: 'json',
            cache: 'false',
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_insurance_company").find(".dropdown-menu").html(str);
                var add_insuranceCompanyId = new customDropDown($(".detail_insurance_company"));
            }
        });

    })

});

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
});

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
function logisticsVehicleQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        plate_number: $("#vehicleManager_plateNumber").val(),
        truck_carriage_type: $(".vehicleManager_truckCarriageType").find(".placeHolder").attr("value"),
        truck_brand: $(".vehicleManager_truckBrand").find(".placeHolder").attr("value"),
        truck_model: $(".vehicleManager_truckModel").find(".placeHolder").attr("value"),
        owner_member: $(".ownerMember").find(".placeHolder").attr("value"),
        owner_name: $("#vehicleManager_ownerName").val(),
        owner_phone: $("#vehicleManager_ownerPhone").val(),
        owner_id: $("#vehicleManager_ownerID").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询物流公司下的车辆列表
function logisticsVehicleQuery() {
    $('#logisticsVehicleTable').bootstrapTable('refresh', {'url': '/iwuliu/logisticsVehicleManager/vehicleList'});
}

// 查询重置
function logisticsVehicleQueryReset() {
    $('#vehicleManager_plateNumber').val("");
    $('.placeHolder').html("请选择");
    $('.placeHolder').attr("value", "");
    $('.station_type').find(".placeHolder").html("全部");
    $('.station_type').find(".placeHolder").attr("value", "");
    $('#vehicleManager_ownerName').val("");
    $('#vehicleManager_ownerPhone').val("");
    $('#vehicleManager_ownerID').val("");
    $(".sr-only").removeClass("sr-only");
    $('#town_street').val("");
    $('#logisticsVehicleTable').bootstrapTable('removeAll');
}

//点击详情
function btn_detail() {
    $(".detail_button").addClass("hidden");
    var selects = $('#logisticsVehicleTable').bootstrapTable('getSelections'); //获取表选择的行
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
                $(".plate_number").val(JsonData.plate_number);
                $(".driving_licence").val(JsonData.driving_licence);
                $(".vehicle_identify_number").val(JsonData.vehicle_identify_number);
                $(".load_weight").val(JsonData.load_weight);
                $(".engine_number").val(JsonData.engine_number);
                // 不可编辑
                $(".verify_status").val(JsonData.verify_status);
                $(".verify_refused_reason").val(JsonData.verify_refused_reason);
                verify_status = JsonData.verify_status;
                // 点击详情按钮，当审核状态为不合格时，显示编辑按钮
                if (verify_status == '不合格') {
                    $(".edit").removeClass("hidden");
                } else {
                    $(".edit").addClass("hidden");
                }
                $(".verify_refused_reason").html('<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason">' + (JsonData.verify_refused_reason) + '</textarea><p></p>');
                verify_refused_reason = JsonData.verify_refused_reason;
                $(".insurance_company").html(JsonData.insurance_company);
                $(".insurance_company").attr("value", JsonData.insurance_company_id);
                $(".truck_brand").html(JsonData.truck_brand);
                $(".truck_brand").attr("value", JsonData.truck_brand_id);
                $(".truck_model_name").html(JsonData.truck_model_name);
                $(".truck_model_name").attr("value", JsonData.truck_model_id);
                $(".truck_carriage_type").html(JsonData.truck_carriage_type);
                $(".truck_carriage_type").attr("value", JsonData.truck_carriage_type_id);
                $(".truck_type").html(JsonData.truck_type);
                $(".truck_type").attr("value", JsonData.truck_type_id);
                $(".fuel_type").html(JsonData.fuel_type);
                $(".fuel_type").attr("value", JsonData.fuel_type_id);
                $(".truck_length").html(JsonData.truck_length);
                $(".truck_length").attr("value", JsonData.truck_length_id);
                $(".detail_driving_licence_first_page_save_path").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.driving_licence_first_page_save_path + ">");
                $(".detail_driving_licence_second_page_save_path").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.driving_licence_second_page_save_path + ">");
                $(".detail_truck_first_pic_save_path").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.truck_first_pic_save_path + ">");
                $(".detail_truck_second_pic_save_path").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.truck_second_pic_save_path + ">");
                $(".detail_truck_third_pic_save_path").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.truck_third_pic_save_path + ">");
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
//点击新增按钮
function btn_ModalNewAdd() {
    $("#myModalNewAdd").modal();

    //获取车辆类型
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/truckTypeJson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_truck_type").find(".dropdown-menu").html(str);
            var add_truckType = new customDropDown($(".add_truck_type"));
        }
    });

    // 获取车辆品牌
    $.ajax({
        type: 'post',
        url: "/iwuliu/vehicleManager/truckBrandJson",
        dataType: 'json',
        cache: false,
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_truck_brand").find(".dropdown-menu").html(str);
            var truckBrand = new customDropDown($(".add_truck_brand"));
            // 根据品牌获取型号
            $(".add_truck_brand").find(".dropdown-menu li").bind("click", function () {
                var truckBrand = $(this).attr("value");
                if (truckBrand != '' && truckBrand != 'undefined') {
                    $.ajax({
                        type: "POST",
                        url: "/iwuliu/vehicleManager/truckModelList?truck_brand_id=" + truckBrand,
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            var str = "";
                            for (var i = 0; i < data.length; i++) {
                                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                            }
                            // 型号
                            $(".add_truck_model_name").find(".dropdown-menu").html("");
                            $(".add_truck_model_name").find(".placeHolder").html("请选择");
                            $(".add_truck_model_name").find(".dropdown-menu").html(str);
                            //型号
                            var city = new customDropDown($(".add_truck_model_name"));
                        }
                    });
                }
            })
        }
    });

    //获取车厢类型
    $.ajax({
        type: 'post',
        url: '/iwuliu/truckCarriageTypeManager/truck_carriage_type_idJson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_truck_carriage_type").find(".dropdown-menu").html(str);
            var add_truckCarriageType = new customDropDown($(".add_truck_carriage_type"));
        }
    });

    //获取车长
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/truckLengthIdJson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_truck_length").find(".dropdown-menu").html(str);
            var add_truckLength = new customDropDown($(".add_truck_length"));
        }
    });

    //获取燃料类型
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/truckFuelTypejson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_fuel_type").find(".dropdown-menu").html(str);
            var add_fuelType = new customDropDown($(".add_fuel_type"));
        }
    });

    //获取保险公司
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/insuranceCompanyjson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_insurance_company_id").find(".dropdown-menu").html(str);
            var add_insuranceCompanyId = new customDropDown($(".add_insurance_company_id"));
        }
    });
}

// 新增保存
function logisticsVehicleNewAddSave() {
    if (checkNotNull($("#add_plate_number")) & checkNotNull($("#add_driving_licence")) &
        checkNotNull($("#add_load_weight")) & checkNotNull($("#add_vehicle_identify_number")) &
        checkNotNull($("#add_engine_number")) & checkSelectNotNull($(".add_truck_type").find(".placeHolder")) &
        checkSelectNotNull($(".add_truck_brand").find(".placeHolder")) & checkSelectNotNull($(".add_truck_model_name").find(".placeHolder")) &
        checkSelectNotNull($(".add_truck_carriage_type").find(".placeHolder")) & checkSelectNotNull($(".add_truck_length").find(".placeHolder")) &
        checkSelectNotNull($(".add_fuel_type").find(".placeHolder")) & checkSelectNotNull($(".add_insurance_company_id").find(".placeHolder")) &
        checkImgNotNull($(".add_truck_first_pic_save_path")) & checkImgNotNull($(".add_truck_second_pic_save_path")) &
        checkImgNotNull($(".add_truck_third_pic_save_path")) & checkImgNotNull($(".add_driving_licence_first_page_save_path")) &
        checkImgNotNull($(".add_driving_licence_second_page_save_path")) & checkPlate($("#add_plate_number")) &
        checkLoadWeight($("#add_load_weight"))) {

        // 封装表单中的参数
        var truckInfoObj = {
            // 下拉框
            "truck_type_id":$(".add_truck_type").find(".placeHolder").attr("value"),
            "truck_model_no":$(".add_truck_model_name").find(".placeHolder").attr("value"),
            "truck_carriage_type_id":$(".add_truck_carriage_type").find(".placeHolder").attr("value"),
            "truck_length_id":$(".add_truck_length").find(".placeHolder").attr("value"),
            "truck_fuel_type_id":$(".add_fuel_type").find(".placeHolder").attr("value"),
            "insurance_company_id":$(".add_insurance_company_id").find(".placeHolder").attr("value"),
            // input框
            "plate_number":$("#add_plate_number").val(),
            "driving_licence":$("#add_driving_licence").val(),
            "load_weight":$("#add_load_weight").val(),
            "vehicle_identify_number":$("#add_vehicle_identify_number").val(),
            "engine_number":$("#add_engine_number").val()
        }

        console.log(truckInfoObj);
        //开始ajax操作，data中传的是表单中的参数
        $("#logisticsVehicleNewAddForm").ajaxSubmit({
            type: "POST",//提交类型
            dataType: "json",//返回结果格式
            url: "/iwuliu/logisticsVehicleManager/saveTruckInfo",//请求地址
            data: {"truckInfo":JSON.stringify(truckInfoObj)},
            async: true,
            success: function (data) {
                var obj = eval(data);
                if (obj.result == 1) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#myModalNewAdd').modal('hide');
                        logisticsVehicleQuery();
                    })
                } else if (obj.result == 0) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("车牌号重复！");
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                    })
                    return false;
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
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("数据格式不正确！")
        $("#smallModalInfo").on("hidden.bs.modal", function (e) {
            $("body").addClass("modal-open");
        })
        return false;
    }
}

// 编辑保存
function logisticsVehicleEditSave() {
    if (checkNotNull($("#detail_plate_number")) & checkNotNull($("#detail_driving_licence")) &
        checkNotNull($("#detail_load_weight")) & checkNotNull($("#detail_vehicle_identify_number")) &
        checkNotNull($("#detail_engine_number")) & checkSelectNotNull($(".detail_truck_type").find(".placeHolder")) &
        checkSelectNotNull($(".detail_truck_brand").find(".placeHolder")) & checkSelectNotNull($(".detail_truck_model_name").find(".placeHolder")) &
        checkSelectNotNull($(".detail_truck_carriage_type").find(".placeHolder")) & checkSelectNotNull($(".detail_truck_length").find(".placeHolder")) &
        checkSelectNotNull($(".detail_fuel_type").find(".placeHolder")) & checkSelectNotNull($(".detail_insurance_company").find(".placeHolder")) &
        checkImgNotNull($(".detail_truck_first_pic_save_path")) & checkImgNotNull($(".detail_truck_second_pic_save_path")) &
        checkImgNotNull($(".detail_truck_third_pic_save_path")) & checkImgNotNull($(".detail_driving_licence_first_page_save_path")) &
        checkImgNotNull($(".detail_driving_licence_second_page_save_path")) & checkPlate($("#detail_plate_number")) &
        checkLoadWeight($("#detail_load_weight"))) {

        // 封装表单中的参数
        var truckInfoObj = {
            // 下拉框
            "truck_type_id":$(".detail_truck_type").find(".placeHolder").attr("value"),
            "truck_model_no":$(".detail_truck_model_name").find(".placeHolder").attr("value"),
            "truck_carriage_type_id":$(".detail_truck_carriage_type").find(".placeHolder").attr("value"),
            "truck_length_id":$(".detail_truck_length").find(".placeHolder").attr("value"),
            "truck_fuel_type_id":$(".detail_fuel_type").find(".placeHolder").attr("value"),
            "insurance_company_id":$(".detail_insurance_company").find(".placeHolder").attr("value"),
            // input框
            "plate_number":$("#detail_plate_number").val(),
            "driving_licence":$("#detail_driving_licence").val(),
            "load_weight":$("#detail_load_weight").val(),
            "vehicle_identify_number":$("#detail_vehicle_identify_number").val(),
            "engine_number":$("#detail_engine_number").val()
        }

        console.log(truckInfoObj);
        //开始ajax操作，data中传的是表单中的参数
        $("#vehicleManagementEditForm").ajaxSubmit({
            type: "POST",//提交类型
            dataType: "json",//返回结果格式
            url: "/iwuliu/logisticsVehicleManager/editTruckInfo",//请求地址
            data: {"truckInfo":JSON.stringify(truckInfoObj)},
            success: function (data) {
                var obj = eval(data);
                if (obj.result == 1) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#myModalDetail').modal('hide');
                        logisticsVehicleQuery();
                    })
                } else if (obj.result == 0) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("车牌号重复！");
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                    })
                    return false;
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
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("数据格式不正确！")
        $("#smallModalInfo").on("hidden.bs.modal", function (e) {
            $("body").addClass("modal-open");
        })
        return false;
    }
}


//点击设定车组成员
var plate_number;
function btn_ModalMemberofVehicle() {
    var selects = $('#logisticsVehicleTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        plate_number = newSelects[0].plate_number;
        $(".driver_plate_number").val(plate_number);
        $("#myModalMemberOfvehicle").modal();
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
        return false;
    }
}

//配置参数
function logisticsVehicleDriverQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        person_name: $("#person_name").val(),
        person_mobile_phone: $("#person_mobile_phone").val(),
        id_card_number: $("#id_card_number").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询物流公司下无车辆的司机列表
function logisticsVehicleDriverQuery() {
    $('#logisticsVehicleDriverTable').bootstrapTable('refresh', {'url': '/iwuliu/logisticsVehicleManager/vehicleDriverList'});
}

// 格式化“证件类型一”
function firstIDTypeFormatter(value, row){
    return '<span>身份证</span>';
}

// 格式化“证件类型二”
function secondIDTypeFormatter(value, row){
    return '<span>驾驶证</span>';
}

//点击设定为车组成员
function logisticsVehicleDriverSetUp() {
    var selects = $('#logisticsVehicleDriverTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length != 0) {
        $(".titleInfoSetUp").html("确定将选中的人设定为该车组成员？");
        $("#smallModalInfoSetUp").modal();
        $(".confirmSetUp").click(function () {
            var person_mobile_phones = "";
            for (var i = 0; i < selects.length; i++) {
                if (i == 0) {
                    person_mobile_phones = selects[i].person_mobile_phone;
                } else {
                    person_mobile_phones += "," + selects[i].person_mobile_phone;
                }
            }
            var driver_plate_number = $(".driver_plate_number").val();
            $.ajax({
                type: "post",
                url: "/iwuliu/logisticsVehicleManager/logisticsVehicleDriverSetUp",
                data:{"person_mobile_phones":person_mobile_phones,
                "plate_number":driver_plate_number},
                cache:'false',
                success: function (object) {
                    // json 类型的专为对象
                    var obj = eval(object);
                    //  reload the user data
                    if (obj.result == 0) {
                        $('#logisticsVehicleDriverTable').bootstrapTable('refresh');
                        $(".titleInfo").html("设定成功！");
                        $("#smallModalInfo").modal();
                    } else {
                        $(".titleInfo").html("设定失败！");
                        $("#smallModalInfo").modal();
                    }
                },
            });
        })
    } else {
        $(".titleInfo").html("请选择一行数据");
        $("#smallModalInfo").modal();
    }
}

//点击设定车辆管理者
var owner_name;
function btn_ModalVehicleManager() {
    var selects = $('#logisticsVehicleTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        plate_number = newSelects[0].plate_number;
        owner_name = newSelects[0].owner_name;
        console.log("owner_name:" + owner_name);
        if (owner_name != null && owner_name != "" && owner_name != 'undefined') {
            //bootstrap提示框
            $(".titleInfoManagerUnbound").html(plate_number + "已拥有车辆管理者！是否要解除管理者与车辆的绑定关系？");
            $("#smallModalInfoManagerUnbound").modal();
            $(".confirmManagerUnbound").click(function () {
                $.ajax({
                    type: "post",
                    url: "/iwuliu/logisticsVehicleManager/logisticsVehicleManagerUnbound",
                    data: {"plate_number": plate_number},
                    cache: 'false',
                    success: function (object) {
                        // json 类型的专为对象
                        var obj = eval(object);
                        console.log(obj);
                        if (obj.result == 1) {
                            $('#logisticsVehicleTable').bootstrapTable('refresh');
                            $(".titleInfo").html("解绑成功！");
                            $("#smallModalInfo").modal();
                        } else if (obj.result == 2) {
                            $(".titleInfo").html("车辆有未完成订单！");
                            $("#smallModalInfo").modal();
                        } else {
                            $(".titleInfo").html("解绑失败！");
                            $("#smallModalInfo").modal();
                        }
                    }
                });
            });
        } else {
            $(".driver_plate_number").val(plate_number);
            $("#myModalVehicleManager").modal();
            $('#logisticsVehicleManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/logisticsVehicleManager/vehicleDriverAndManagerList'});
        }
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
        return false;
    }
}

// 点击保存勾选车辆管理者
function logisticsVehicleManagerSave() {
    var selects = $('#logisticsVehicleManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        $(".titleInfoSetUp").html("确定将选中的人设定为该管理员？");
        $("#smallModalInfoSetUp").modal();
        $(".confirmSetUp").click(function () {
            var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
            var person_mobile_phone = newSelects[0].person_mobile_phone;
            var driver_plate_number = $(".driver_plate_number").val();
            $.ajax({
                type: "post",
                url: "/iwuliu/logisticsVehicleManager/logisticsVehicleManagerSetUp",
                data:{"person_mobile_phone":person_mobile_phone,
                    "plate_number":driver_plate_number},
                cache:'false',
                success: function (object) {
                    // json 类型的专为对象
                    var obj = eval(object);
                    //  reload the user data
                    if (obj.result == 0) {
                        $('#logisticsVehicleTable').bootstrapTable('refresh');
                        $(".titleInfo").html("设定成功！");
                        $("#smallModalInfo").modal();
                    } else {
                        $(".titleInfo").html("设定失败！");
                        $("#smallModalInfo").modal();
                    }
                },
            });
        });
    } else {
        $(".titleInfo").html("请选择一行数据");
        $("#smallModalInfo").modal();
    }
}


// 点击解绑
var plate_numberUnbound
function btn_ModalUnbound() {
    var selects = $('#logisticsVehicleTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        plate_numberUnbound = newSelects[0].plate_number;
        $(".titleInfoUnbound").html("确定解绑" + plate_numberUnbound + "？");
        $("#smallModalInfoUnbound").modal();
        $(".confirmUnbound").click(function () {
            $.ajax({
                type: "post",
                url: "/iwuliu/logisticsVehicleManager/logisticsVehicleUnbound",
                data: {"plate_number": plate_numberUnbound},
                cache: 'false',
                success: function (object) {
                    // json 类型的专为对象
                    var obj = eval(object);
                    console.log(obj);
                    if (obj.result == 1) {
                        $('#logisticsVehicleTable').bootstrapTable('refresh');
                        $(".titleInfo").html("解绑成功！");
                        $("#smallModalInfo").modal();
                    } else if (obj.result == 2) {
                        $(".titleInfo").html("车辆有未完成订单！");
                        $("#smallModalInfo").modal();
                    } else {
                        $(".titleInfo").html("解绑失败！");
                        $("#smallModalInfo").modal();
                    }
                }
            });
        });
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
        return false;
    }
}



//===================>>>图片
//图片上传
function loadImg(ele) {
    $(ele).parents(".formgroupImg").find(".sr-only").click();
    //点击图片放大
    $(".formgroupImg").find("img").bind("click", function () {
        var _src = $(this).attr("src");
        $(".modalImg").html("<img src=" + _src + "/>");
        $('#ModalImg').modal();
        //图片放大模态框隐藏后触发的事件
        $("#ModalImg").on("hidden.bs.modal", function (e) {
            $("body").addClass("modal-open")
        })
    })
}

//图片上传预览    IE是用了滤镜。
function previewImage(ele) {
    var MAXWIDTH = 100;
    var MAXHEIGHT = 40;
    var imgSrc;
    if (ele.files && ele.files[0]) {
        $(ele).parents(".formgroupImg").find(".display_inlineBlcok").html('<img title="点击放大显示"/>')
        var img = $(ele).parents(".formgroupImg").find("img")[0];
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
            imgSrc = evt.target.result;
        }
        reader.readAsDataURL(ele.files[0]);
    } else {
        ele.blur();
        var sFilter = 'FILTER: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)';
        $(ele).parents(".formgroupImg").find(".display_inlineBlcok").html('<img title="点击放大显示"/>')
        var img = $(ele).parents(".formgroupImg").find("img")[0];
        var src = document.selection.createRange().htmlText;
        img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=" + src + ")";
        img.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
    }
}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {
        top: 0,
        left: 0,
        width: width,
        height: height
    };
    if (width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
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

// 不能为空的正则校验(图片使用)
function checkImgNotNull(inp) {
    var _value = inp.find("img").attr("src");
    var _p = $(inp).find("p");
    if (_value != '' && _value != undefined) {
        _p.html("");
        return true;
    } else {
        _p.css("color", "red");
        _p.html("未上传图片！");
        return false;
    }
}

// 字段验证
// 车牌号
function checkPlate(ele) {
    var total = $(ele).val();
    if (total != "") {
        if (regExpPlate.test(total)) {
            //$(ele).css("border","1px solid #ccc");
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的车牌号！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入车牌号");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}
// 核定载重
function checkLoadWeight(ele){
    var total=$(ele).val();
    if(total != ""){
        if (regNum.test(total)){
            $(ele).parent().find("p").html("");
            return true;
        }else {
            $(ele).parent().find("p").html("请输入正确的核定载重！");
            $(ele).parent().find("p").css("color","red");
            return false;
        }
    }else{
        $(ele).parent().find("p").html("请输入数字！");
        $(ele).parent().find("p").css("color","red");
        return false;
    }
}