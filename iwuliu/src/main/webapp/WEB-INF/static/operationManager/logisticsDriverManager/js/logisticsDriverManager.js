// 正则表达式
// 手机号正则
var regExpMob = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; //手机号码正则验证准确版
// 身份证正则
var regIDCard = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
// 数字
var regNum = /^\+?[0-9][0-9]*$/;

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
});

// 全局控制
$(function () {
    $(".leftMenu").load("/iwuliu/welcomeManager/home", function () {
        $(".logisticsDriverManager").parents(".collapse").addClass("in");
        $(".logisticsDriverManager").addClass("menuActive");
        roleManager();
    });

    // 点击详情按钮，当审核状态为不合格时，显示编辑按钮
    $(".detailClick").bind("click",function(){
        $(".detail_button").addClass("hidden");
        if (verify_status==3){
            $(".edit").removeClass("hidden");
        } else{
            $(".edit").addClass("hidden");
        }
    });

    // 查询条件下拉框
    var IdType = new customDropDown($(".IdType"));

    // logisticsDriverManagerTable表格
    var table = $('#logisticsDriverManagerTable'),
        page = $('#driver_page'),
        goBtn = $('#driver_goBtn');
    // 跳转到某页
    goBtn.click(function () {
        table.bootstrapTable('selectPage', +page.val());
    });
    // 单选
    $("#logisticsDriverManagerTable .th-inner").eq(0).append('<span>单选</span>');

    // vehicleManagerTable表格
    var table = $('#vehicleManagerTable'),
        page = $('#vehicle_page'),
        goBtn = $('#vehicle_goBtn');
    // 跳转到某页
    goBtn.click(function () {
        table.bootstrapTable('selectPage', +page.val());
    });
    // 单选
    $("#vehicleManagerTable .th-inner").eq(0).append('<span>单选</span>');

    //点击编辑按钮
    $(".edit").bind("click", function () {
        $("#myModalDetail").find(".hidden").removeClass("hidden");
    })
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
    //新增模态框消失
    $("#myModalNewAdd").on("hidden.bs.modal", function () {
        $("#myModalNewAdd").find("input").val("");
        $(".first_type").val("身份证");
        $(".second_type").val("驾驶证");
        $("#myModalNewAdd").find(".placeHolder").html("请选择");
        $("#myModalNewAdd").find(".placeHolder").attr("value","");
        $("#myModalNewAdd").find(".dropdown-menu").html("");
        $("#myModalNewAdd").find("p").html("");
        $("#myModalNewAdd").find(".formgroupImg").find(".display_inlineBlcok").html("点击右侧按钮上传图片"+"<p></p>");
    })

    //详情模态框消失
    $("#myModalDetail").on("hidden.bs.modal", function () {
        $("#myModalDetail").find(".form-group").addClass("uneditable");
        $("#myModalDetail").find("input").attr("disabled", "true");
        $("#myModalDetail").find(".form-group").find("button").attr("disabled", "true");
        $("#myModalDetail").find(".edit").attr("isClick", "true");
        $("#myModalDetail").find(".edit").removeAttr("disabled");
        $("#myModalDetail").find(".edit").css("background", "#69a2b5")
    })

    // 设定车辆模态框消失
    $("#ModalsetVehicle").on("hidden.bs.modal", function () {
       $(".plate_number_set").val("");
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

// 格式化“证件类型一”
function firstIDTypeFormatter(value, row) {
    return '<span>身份证</span>';
}

// 格式化“证件类型二”
function secondIDTypeFormatter(value, row) {
    return '<span>驾驶证</span>';
}

// logisticsDriverManagerTable配置查询参数
function logisticsDriverManagerTableQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        person_name: $("#person_name").val(),
        plate_number: $("#plate_number").val(),
        ID_type: $(".ID_type").attr("value"),
        ID_num: $(".ID_num").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询物流司机管理列表
function driverManagerQuery() {
    $('#logisticsDriverManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/logisticsDriverManager/driverManagerList'});
}

// 重置功能
function driverManagerQueryReset() {
    $(".ID_type").find(".placeHolder").html("请选择");
    $("#person_name").val("");
    $("#plate_number").val("");
    $(".ID_num").val("");
    $('#logisticsDriverManagerTable').bootstrapTable('removeAll');
}


// vehicleManagerTable配置查询参数
function vehicleQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        plate_number: $(".plate_number_set").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 设定车辆中查询车辆
function setVehicleQuery(){
    $('#vehicleManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/logisticsDriverManager/findTrucksOfCompany'});
}
// 绑定车辆
function bindTruck(){
    var selects = $('#vehicleManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        var truck_id = newSelects[0].truck_id;
        $.ajax({
            type: "post",
            url: "/iwuliu/logisticsDriverManager/bindTruck",
            data: {truck_id: encodeURI(truck_id),
                member_name: encodeURI($(".person_mobile_phone_set").val())
            },
            success: function (data) {
                var obj = eval(data);
                if (obj.result == 1) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("绑定成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#ModalsetVehicle').modal('hide');

                    })
                    driverManagerQuery();
                } else {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("绑定失败！");
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
        })
    }else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
        return false;
    }
}

// 解绑司机
function unbundling(){
    var selects = $('#logisticsDriverManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    // 判断是否选择一行数据
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        //person_id_set = newSelects[0].person_id_set;
        // 判断是否是验证通过的司机
        if (newSelects[0].manager_member_name == ""&&newSelects[0].verify_status==2) {
            // 判断是否无运单
            $.ajax({
                type: 'post',
                url: '/iwuliu/logisticsDriverManager/judgeOrderOfDriver',
                dataType: 'json',
                cache: 'false',
                success: function (data) {
                    var obj = eval(data);
                    if (obj.result == 1) {
                        //bootstrap提示框
                        $(".titleInfoDelet").html("该司机无运单,确定解绑？")
                        $("#smallModalInfoDelet").modal();
                        $(".confirmDelet").click(function () {
                            $.ajax({
                                type: "post",
                                url: "/iwuliu/logisticsDriverManager/unbundlingDriver",
                                async: true,
                                data: {
                                    "person_id": newSelects[0].person_id
                                },
                                success: function (object) {
                                    // json 类型的专为对象
                                    var obj = eval(object);
                                    console.log(obj)
                                    //  reload the user data
                                    if (obj.result == 1) {
                                        $('#obdEquipmentManagerTable').bootstrapTable('refresh');
                                        $(".titleInfo").html("解绑成功！");
                                        $("#smallModalInfo").modal();
                                        driverManagerQuery();
                                    } else {
                                        $(".titleInfo").html("解绑失败！");
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
                        //bootstrap提示框
                        $("#smallModalInfo").modal();
                        $("#smallModalInfo").find(".titleInfo").html("该司机正在运单！")
                        return false;
                    }


                }
            });
        } else {
            //bootstrap提示框
            $("#smallModalInfo").modal();
            $("#smallModalInfo").find(".titleInfo").html("请选择验证通过的司机！")
            return false;
        }
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
        return false;
    }
}

//点击新增
function btn_NewAdd() {
    // ==================>>>新增动态下拉框
    //获取性别
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/sexJson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_sex").find(".dropdown-menu").html(str);
            var add_sex = new customDropDown($(".add_sex"));
        }
    });
    $("#myModalNewAdd").modal()
}

// 新增保存
function driverNewAddSave() {
    if (checkNotNull($(".add_person_name")) & checkPhone($(".add_person_mobile_phone")) &
        checkIdCard($(".add_id_card_number")) & checkDLN($(".add_driver_licence_number")) &
        checkQCN($(".add_qualification_certificate_number")) & checkImgNotNull($(".add_id_card_front_pic_id")) &
        checkImgNotNull($(".add_id_card_back_pic_id")) & checkImgNotNull($(".add_driver_licence_main_pic_id")) &
        checkImgNotNull($(".add_driver_licence_sub_pic_id")) & checkSelectNotNull($(".add_sex").find(".placeHolder"))
        &checkImgNotNull($(".add_qualification_certificate_number_pic_id"))) {
        // 封装表单中的参数
        var driverInfoObj = {
            // input框
            "add_person_name": $(".add_person_name").val(),
            "add_person_mobile_phone": $(".add_person_mobile_phone").val(),
            "add_id_card_number": $(".add_id_card_number").val(),
            "add_driver_licence_number": $(".add_driver_licence_number").val(),
            "add_qualification_certificate_number": $(".add_qualification_certificate_number").val(),
            // 下拉框
            "add_sex": $(".sex_add").attr("value"),
        }
        //开始ajax操作，data中传的是表单中的参数
        $("#driverNewAddForm").ajaxSubmit({
            type: "POST",//提交类型
            dataType: "json",//返回结果格式
            url: "/iwuliu/logisticsDriverManager/saveDriverInfo",//请求地址
            data: {
                "driverInfo": JSON.stringify(driverInfoObj)
            },
            success: function (data) {
                var obj = eval(data);
                if (obj.result == 1) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#myModalNewAdd').modal('hide');
                        driverManagerQuery();
                    })
                } else if (obj.result == 2) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("该司机已经加入平台，请联络其加入贵公司！");
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

//点击详情按钮
var person_id;
var verify_status;
function driverDetail() {
    var selects = $('#logisticsDriverManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        person_id = newSelects[0].person_id;
        verify_status = newSelects[0].verify_status;
        $.ajax({
            type: "post",
            url: "/iwuliu/logisticsDriverManager/getDriverInfo",
            data: {person_id: encodeURI(person_id)},
            success: function (data) {
                var JsonData = eval(data);
                console.log(JsonData);
                // 让表单上所有p标签提示清空,避免编辑出现的提示保留到详情中
                $("#driverDetailForm").find("p").html("");
                $(".person_name").val(JsonData.person_name);
                $(".person_mobile_phone").val(JsonData.person_mobile_phone);
                $(".id_card_number").val(JsonData.id_card_number);
                $(".driver_licence_number").val(JsonData.driver_licence_number);
                $(".qualification_certificate_number").val(JsonData.qualification_certificate_number);
                $(".sex").html(JsonData.sex_value);
                $(".sex").attr("value",JsonData.sex);// 绑定id
                // 不可编辑
                $(".detail_verify_status").val(JsonData.verify_status_value);
                // 图片
                $(".id_card_front_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.id_card_front_pic_id + ">");
                $(".id_card_back_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.id_card_back_pic_id + ">");
                $(".driver_licence_main_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.driver_licence_main_pic_id + ">");
                $(".driver_licence_sub_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.driver_licence_sub_pic_id + ">");
                $(".qualification_certificate_number_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
                    "fileUrl=" + JsonData.qualification_certificate_number_pic_id + ">");
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
function driverDetailSave() {
    if (checkNotNull($(".person_name")) &
        checkIdCard($(".id_card_number")) & checkDLN($(".driver_licence_number")) & checkQCN($(".qualification_certificate_number"))
        & checkImgNotNull($(".id_card_front_pic_id")) & checkImgNotNull($(".id_card_front_pic_id"))
        & checkImgNotNull($(".id_card_back_pic_id")) & checkImgNotNull($(".driver_licence_main_pic_id")) &
        checkImgNotNull($(".driver_licence_sub_pic_id"))) {
        // 封装表单中的参数
        var driverEditInfoObj = {
            // 主键
            "person_id": person_id,
            // input框
            "person_name": $(".person_name").val(),
            "id_card_number": $(".id_card_number").val(),
            "driver_licence_number": $(".driver_licence_number").val(),
            "qualification_certificate_number": $(".qualification_certificate_number").val(),
            "sex": $(".sex").attr("value")
        }
        //开始ajax操作，data中传的是表单中的参数
        $("#driverDetailForm").ajaxSubmit({
            type: "POST",//提交类型
            dataType: "json",//返回结果格式
            url: "/iwuliu/logisticsDriverManager/editDriverInfo",//请求地址
            data: {"driverEditInfo": JSON.stringify(driverEditInfoObj)},
            success: function (data) {
                var obj = eval(data);
                if (obj.result == 1) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#myModalDetail').modal('hide');
                        driverManagerQuery();
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
            error: function (XMLHttpRequest, textStatus, errorThrown) {
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

//点击设定车辆
var person_id_set;
function btn_setVehicle() {
    setVehicleQuery();
    var selects = $('#logisticsDriverManagerTable').bootstrapTable('getSelections'); //获取表选择的行
    // 判断是否选择一行数据
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        person_id_set = newSelects[0].person_id_set;
        // 判断是否是司机
        if (newSelects[0].manager_member_name == ""&&newSelects[0].verify_status==2) {
            $(".person_name_set").val(newSelects[0].owner_member_name);
            $(".person_mobile_phone_set").val(newSelects[0].person_mobile_phone);
            $("#ModalsetVehicle").modal();
        } else {
            //bootstrap提示框
            $("#smallModalInfo").modal();
            $("#smallModalInfo").find(".titleInfo").html("请选择验证通过的司机！")
            return false;
        }
    } else {
        //bootstrap提示框
        $("#smallModalInfo").modal();
        $("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
        return false;
    }
}

//===================>>>图片处理
//图片放大
function ImgBig(ele) {
    var _src = $(ele).attr("src");
    $(".modalImg").html("<img src=" + _src + "/>");
    $('#ModalImg').modal();
    //图片放大模态框隐藏后触发的事件
    $("#ModalImg").on("hidden.bs.modal", function (e) {
        $("body").addClass("modal-open")
    })
}

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


//==============================编辑页面
//获取性别
$.ajax({
    type:'post',
    url:'/iwuliu/dictionaryManager/sexJson',
    dataType:'json',
    cache:'false',
    success:function(data){
        var str="";
        for (var i= 0;i<data.length;i++){
            str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
        }
        $(".detail_sex").find(".dropdown-menu").html(str);
        var detail_sex = new customDropDown($(".detail_sex"));
    }
});

// ==================>>>字段校验
//验证公司手机号码
function checkPhone(ele) {
    var total = $(ele).val();
    if (total != "") {
        if (regExpMob.test(total)) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的手机号码！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入手机号码");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}

// 验证身份证号码
function checkIdCard(ele) {
    var total = $(ele).val();
    $(ele).val((total.trim()).toLocaleUpperCase());
    if (total != "") {
        if (regIDCard.test(total)) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的身份证号码！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入身份证号码！");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}

// 验证驾驶档案编号
function checkDLN(ele) {
    var total = $(ele).val();
    if (total != "") {
        if (regNum.test(total)) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的驾驶档案编号！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入驾驶档案编号！");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}

// 验证运输从业资格证号码
function checkQCN(ele) {
    var total = $(ele).val();
    if (total != "") {
        if (regNum.test(total)) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的资格证号码！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入资格证号码！");
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
    if(_value!=''&&_value!=undefined){
        _p.html("");
        return true;
    }else{
        _p.css("color","red");
        _p.html("不能为空！");
        return false;
    }
}

// 不能为空的正则校验(图片使用)
function checkImgNotNull(inp) {
    var _value = inp.find("img").attr("src");
    if (_value != '' && _value != undefined) {
        return true;
    } else {
        var _str = '<p></p>';
        inp.append(_str);
        inp.find("p").css("color", "red");
        inp.find("p").html("未上传图片！");
        return false;
    }
}
