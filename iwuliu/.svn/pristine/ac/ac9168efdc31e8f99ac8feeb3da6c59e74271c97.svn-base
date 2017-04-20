// 手机号正则
var regExpMob = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
//身份证号码正则
var regExpID = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
// 银行卡号正则
var regExpBankNumber = /^(\d{16}|\d{19})$/;

$(function () {
    $(".leftMenu").load("/iwuliu/welcomeManager/home", function () {
        $(".allianceBusinessManagement").parents(".collapse").addClass("in");
        $(".allianceBusinessManagement").addClass("menuActive")
        roleManager();
    });
// 时分插件加载
    $('.clockpicker').clockpicker();
    //获取省、市、区
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/provinceJson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".province_id").find(".dropdown-menu").html(str);
            //省
            var province = new customDropDown($(".province_id"));
            //根据省获取市
            $(".province_id").find(".dropdown-menu li").bind("click", function () {
                var province = $(this).attr("value");
                if (province != '' && province != 'undefined') {
                    $.ajax({
                        type: "POST",
                        url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + province,
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            var str = "";
                            for (var i = 0; i < data.length; i++) {
                                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                            }
                            //市
                            $(".city_id").find(".dropdown-menu").html("");
                            $(".city_id").find(".placeHolder").html("请选择");
                            $(".city_id").find(".dropdown-menu").html(str);
                            //县
                            $(".area_id").find(".dropdown-menu").html("");
                            $(".area_id").find(".placeHolder").html("请选择");
                            $(".area_id").find(".placeHolder").attr("value", "");
                            //市
                            var city = new customDropDown($(".city_id"));
                            //根据市获取县
                            $(".city_id").find(".dropdown-menu li").bind("click", function () {
                                var city = $(this).attr("value");
                                if (city != '' && city != 'undefined') {
                                    $.ajax({
                                        type: "POST",
                                        url: "/iwuliu/dictionaryManager/areaJson?dicdata_code=" + city,
                                        cache: false,
                                        dataType: "json",
                                        success: function (data) {
                                            var str = "";
                                            for (var i = 0; i < data.length; i++) {
                                                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                                            }
                                            $(".area_id").find(".placeHolder").html(data[0].name);
                                            $(".area_id").find(".placeHolder").attr("value", data[0].id);
                                            $(".area_id").find(".dropdown-menu").html(str);
                                            var area = new customDropDown($(".area_id"));
                                        }
                                    });
                                }
                            })
                        }
                    });
                }
            })
        },
        error: function () {
            alert("错误");
        }
    });

    //启用表格
    $("#allianceBusinessTable").bootstrapTable({
        onLoadSuccess: function (data) {
            var station_type = $(".station_type");
            for (var i = 1; i < station_type.length; i++) {
                var type = station_type[i].innerHTML;
                switch (type) {
                    case "0":
                        station_type[i].innerHTML = "维修站"
                        break;
                    case "1":
                        station_type[i].innerHTML = "加气站"
                        break;
                    case "2":
                        station_type[i].innerHTML = "4S店"
                        break;
                    case "3":
                        station_type[i].innerHTML = "加油站"
                        break;
                    default:
                        break;
                }
            }
        }
    });

    //下拉框
    var stationType = new customDropDown($(".station_type"));
    //点击图片放大
    $(".formgroupImg").find("img").bind("click", function () {
        var _src = $(this).attr("src");
        $(".modalImg").html("<img src=" + _src + "/>");
        $('#ModalImg').modal();
        //图片放大模态框隐藏后触发的事件
        $("#ModalImg").on("hidden.bs.modal", function (e) {
            $("body").addClass("modal-open")
        })
    });

    // 点击编辑
    $(".edit").bind("click", function () {
        $("#detail_work_begin_time").find("span").removeClass("hidden");
        $("#detail_work_end_time").find("span").removeClass("hidden");
        $(".getMap").removeClass("hidden");
        $(".LatitudeAndLongitude").find("input").attr("disabled", "true")
        //服务站类型
        var detailStationType = new customDropDown($(".detail_station_type"));

        //获取开户银行
        $.ajax({
            type: 'post',
            url: '/iwuliu/dictionaryManager/bankJson',
            dataType: 'json',
            cache: 'false',
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_bank_id").find(".dropdown-menu").html(str);
                var detailBankId = new customDropDown($(".detail_bank_id"));
            }
        });

//获取省、市、区
        $.ajax({
            type: 'post',
            url: '/iwuliu/dictionaryManager/provinceJson',
            dataType: 'json',
            cache: 'false',
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                }
                $(".detail_province").find(".dropdown-menu").html(str);
                //省
                var detail_province = new customDropDown($(".detail_province"));
                //根据省获取市
                $(".detail_province").find(".dropdown-menu li").bind("click", function () {
                    var detail_province = $(this).attr("value");
                    if (detail_province != '' && detail_province != 'undefined') {
                        $.ajax({
                            type: "POST",
                            url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + detail_province,
                            cache: false,
                            dataType: "json",
                            success: function (data) {
                                var str = "";
                                for (var i = 0; i < data.length; i++) {
                                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                                }
                                //市
                                $(".detail_city").find(".dropdown-menu").html("");
                                $(".detail_city").find(".placeHolder").html("请选择");
                                $(".detail_city").find(".dropdown-menu").html(str);
                                //县
                                $(".detail_area").find(".dropdown-menu").html("");
                                $(".detail_area").find(".placeHolder").html("请选择");
                                $(".detail_area").find(".placeHolder").attr("value", "");
                                //市
                                var detail_city = new customDropDown($(".detail_city"));
                                //根据市获取县
                                $(".detail_city").find(".dropdown-menu li").bind("click", function () {
                                    var detail_city = $(this).attr("value");
                                    if (detail_city != '' && detail_city != 'undefined') {
                                        $.ajax({
                                            type: "POST",
                                            url: "/iwuliu/dictionaryManager/areaJson?dicdata_code=" + detail_city,
                                            cache: false,
                                            dataType: "json",
                                            success: function (data) {
                                                var str = "";
                                                for (var i = 0; i < data.length; i++) {
                                                    str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                                                }
                                                $(".detail_area").find(".placeHolder").html(data[0].name);
                                                $(".detail_area").find(".placeHolder").attr("value", data[0].id);
                                                $(".detail_area").find(".dropdown-menu").html(str);
                                                var detail_area = new customDropDown($(".detail_area"));
                                            }
                                        });
                                    }
                                })
                            }
                        });
                    }
                })
            }
        });
    });

    var table = $('#allianceBusinessTable'),
        allianceBusiness_page = $('#allianceBusiness_page'),
        allianceBusiness_goBtn = $('#allianceBusiness_goBtn');
    // 跳转到某页
    allianceBusiness_goBtn.click(function () {
        table.bootstrapTable('selectPage', +allianceBusiness_page.val());
    });
    $(".th-inner").eq(0).append('<span>全选</span>');

    //新增模态框消失重置表单数据
    $('#myModalNewAdd').on('hidden.bs.modal', function () {
        $("#myModalNewAdd").find("input").val("");
        $("#myModalNewAdd").find("p").html("");
        $("#myModalNewAdd").find(".placeHolder").html("请选择");
        $(".add_city").find(".dropdown-menu").html("");
        $(".add_area").find(".dropdown-menu").html("");
    })

    //详情模态框消失
    $("#myModalDetail").on("hidden.bs.modal", function () {
        $("#myModalDetail").find(".form-group").addClass("uneditable");
        $("#myModalDetail").find("input").attr("disabled", "true");
        $("#myModalDetail").find(".form-group").find("button").attr("disabled", "true");
        $("#myModalDetail").find(".edit").attr("isClick", "true");
        $("#myModalDetail").find(".edit").removeAttr("disabled");
        $("#myModalDetail").find(".edit").css("background", "#69a2b5");
        $("#detail_work_begin_time").find("span").addClass("hidden");
        $("#detail_work_end_time").find("span").addClass("hidden");
        $(".Detail_ModalQRCode").html("");
    })
});

// 格式化“二维码”列
function qrCodePhotoFormatter() {
    return '<a type="button" isClick=true class="NewDetail btn btn-success" onclick="qrCodePhotoDetail(this.parentElement.parentElement)">查看二维码</a>';
}

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
    $("#smallModalInfo").on("hidden.bs.modal",function(e){
        $("body").addClass("modal-open");
    });
});

// 点击“查看二维码”
function qrCodePhotoDetail(value) {
    var script = document.createElement("script");
    script.setAttribute("type", "text/javascript");
    script.setAttribute("src", "/iwuliu/static/common/js/jquery.qrcode.min.js");
    script.setAttribute("class", "qrcode");
    $("head")[0].appendChild(script);
    script.onload = function () {
        var _value = $(value).find(".hidden").html();
        var _stationName = $(value).find(".station_name").html();
        $(".QRCodeStationName").html(_stationName);
        $(".ModalQRCode").qrcode({
            render: "table", //table方式
            width: 400, //宽度
            height: 400, //高度
            text: _value //任意内容
        });
        $('#ModalQRCode').modal();
        //图片放大模态框隐藏后触发的事件
        $("#ModalQRCode").on("hidden.bs.modal", function (e) {
            $(".ModalQRCode").html("");
        })
    }
}

// 二维码识别中文
//function toUtf8(str) {
//	var out, i, len, c;
//	out = "";
//	len = str.length;
//	for(i = 0; i < len; i++) {
//		c = str.charCodeAt(i);
//		if ((c >= 0x0001) && (c <= 0x007F)) {
//			out += str.charAt(i);
//		} else if (c > 0x07FF) {
//			out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
//			out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
//			out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
//		} else {
//			out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
//			out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
//		}
//	}
//	return out;
//}
//var _value = toUtf8("识别中文！");
//$('.ModalQRCode').qrcode(_value);

//点击详情
var station_id;
function btn_ModalDetail() {
    var selects = $('#allianceBusinessTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length == 1) {
        var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
        station_id = newSelects[0].station_id;
        $.ajax({
            type: "post",
            url: "/iwuliu/allianceBusiness/allianceBusinessDetail",
            data: {station_id: encodeURI(station_id)},
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                var JsonData = JSON.parse(data);

                // 隐藏点击获取经纬度
                $(".getMap").addClass("hidden")
                // 让表单上所有p标签提示清空,避免编辑出现的提示保留到详情中
                $("#stationEditForm").find("p").html("");
                $(".detail_station_name").val(JsonData.station_name);
                if (JsonData.station_type == 0) {
                    $(".stationType").html("维修站");
                } else if (JsonData.station_type == 1) {
                    $(".stationType").html("加气站");
                } else if (JsonData.station_type == 2) {
                    $(".stationType").html("4S店");
                } else if (JsonData.station_type == 3) {
                    $(".stationType").html("加油站");
                } else {
                    $(".stationType").html(JsonData.station_type);
                }
                $(".stationType").attr("value", JsonData.station_type);// 绑定id
                $(".province").html(JsonData.province_name);
                $(".province").attr("value", JsonData.province_id);// 绑定id
                $(".city").html(JsonData.city_name);
                $(".city").attr("value", JsonData.city_id);// 绑定id
                $(".area").html(JsonData.area_name);
                $(".area").attr("value", JsonData.area_id);// 绑定id
                $(".townStreet").val(JsonData.town_street);
                $(".detail_longitude").val(JsonData.longitude);
                $(".detail_latitude").val(JsonData.latitude);
                $(".detail_work_begin_time").val(JsonData.work_begin_time);
                $(".detail_work_end_time").val(JsonData.work_end_time);
                $(".contact_name").val(JsonData.contact_name);
                $(".contact_phone").val(JsonData.contact_phone);
                $(".bank_id").html(JsonData.bank_name);
                $(".bank_id").attr("value", JsonData.bank_id);// 绑定id
                $(".bank_account").val(JsonData.bank_account);
                $(".bank_account_person_name").val(JsonData.bank_account_person_name);
                $(".bank_account_person_id").val(JsonData.bank_account_person_id);

                // 二维码
                var script = document.createElement("script");
                script.setAttribute("type", "text/javascript");
                script.setAttribute("src", "/iwuliu/static/common/js/jquery.qrcode.min.js");
                script.setAttribute("class", "qrcode");
                $("head")[0].appendChild(script);
                script.onload = function () {
                    var _value = JsonData.station_id;
                    $(".Detail_ModalQRCode").qrcode({
                        render: "table", //table方式
                        width: 200, //宽度
                        height: 200, //高度
                        text: _value //任意内容
                    });
                }
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

// 点击删除按钮
function stationDel() {
    var selects = $('#allianceBusinessTable').bootstrapTable('getSelections'); //获取表选择的行
    if (selects.length != 0) {
        $(".titleInfoDelet").html("确定删除？")
        $("#smallModalInfoDelet").modal();
        $(".confirmDelet").click(function () {
            var station_ids = "";
            for (var i = 0; i < selects.length; i++) {
                if (i == 0) {
                    station_ids = selects[i].station_id;
                } else {
                    station_ids += "," + selects[i].station_id;
                }
            }
            ;
            console.log(station_ids)
            $.ajax({
                type: "post",
                url: "/iwuliu/allianceBusiness/allianceBusinessDel?temID=" + station_ids,
                async: true,
                success: function (object) {
                    // json 类型的专为对象
                    var obj = eval(object);
                    //  reload the user data
                    if (obj.result == 0) {
                        $('#allianceBusinessTable').bootstrapTable('refresh');
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

var longitudeCommon, latitudeCommon;

//点击获取经纬度
function getMap(ele) {
    //地图模态框消失
    $("#ModalMap").on("hidden.bs.modal", function () {
        $("#ModalMap").find("input").val("");
    });
    var _formParents = $(ele).parents(".form-inline");
    //省
    var province = _formParents.find(".provinceCommon")
    //市
    var city = _formParents.find(".cityCommon")
    //县
    var area = _formParents.find(".areaCommon");
    //村镇街道
    var townStreet = _formParents.find(".townStreetCommon");
    //经度
    var _longitudeCommon = _formParents.find(".longitudeCommon");
    longitudeCommon = _longitudeCommon
    //纬度
    var _latitudeCommon = _formParents.find(".latitudeCommon");
    latitudeCommon = _latitudeCommon
    $("#ModalMap").modal();
    //省市县
    $(".map_address").val(province.html() + city.html() + area.html() + townStreet.val());

    //图片放大模态框隐藏后触发的事件
    $("#ModalMap").on("hidden.bs.modal", function () {
        $("body").addClass("modal-open")
    })
}

function getClick() {
    objMap.clearMap();
    var geocoder = new AMap.Geocoder({
        radius: 500 //范围，默认：500
    });
    var key = $(".map_address").val();
    //地理编码,返回地理编码结果
    geocoder.getLocation(key, function (status, result) {
        if (status === 'complete' && result.info === 'OK') {
            geocoder_CallBack(result);
        }
    });
}
function addMarker(i, d) {
    var marker = new AMap.Marker({
        map: objMap,
        position: [d.location.getLng(), d.location.getLat()]
    });
    var infoWindow = new AMap.InfoWindow({
        content: d.formattedAddress,
        offset: {x: 0, y: -30}
    });
    marker.on("mouseover", function (e) {
        infoWindow.open(objMap, marker.getPosition());
    });
}
//地理编码返回结果展示
function geocoder_CallBack(data) {
    //地理编码结果数组
    var geocode = data.geocodes;
    addMarker(0, geocode[0]);
    objMap.setFitView();
    $(".map_longitude").val(geocode[0].location.getLng());
    $(".map_latitude").val(geocode[0].location.getLat());
}

//地图点击确定按钮关闭模态框
function determine() {
    var _longitudeValue = $(".map_longitude").val();
    var _latitudeValue = $(".map_latitude").val();
    longitudeCommon.val(_longitudeValue);
    latitudeCommon.val(_latitudeValue);
}

var objMap;
//地图
$(function () {
    objMap = new AMap.Map('container', {
        resizeEnable: true,
        zoom: 11,
        center: [116.397428, 39.90923]

    });
    //加载地图基本控件
    objMap.plugin(["AMap.ToolBar", "AMap.OverView", "AMap.Scale", "AMap.TileLayer", "AMap.MapType", "AMap.Driving", "AMap.Geocoder"], function () {
        //添加工具条
        objMap.addControl(new AMap.ToolBar());
        //比例尺
        objMap.addControl(new AMap.Scale());
        //添加鹰眼
        objMap.addControl(new AMap.OverView({isOpen: true}));
        //实时路况
        objMap.addControl(new AMap.TileLayer.Traffic({
            zIndex: 10
        }));
        //实现默认图层与卫星图、实施交通图层之间切换的控
        objMap.addControl(new AMap.MapType());
        objMap.addControl(new AMap.Driving());
        objMap.addControl(new AMap.Geocoder());
    });
});

//点击新增按钮
function btn_newAdd() {
    $("#myModalNewAdd").modal();
    //服务站类型
    var mydropdown = new customDropDown($(".add_station_type"));
    //开户银行
    var add_bank_id = new customDropDown($(".add_bank_id"));

    // 显示点击获取经纬度按钮
    $(".getMap").removeClass("hidden");

    //获取开户银行
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/bankJson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_bank_id").find(".dropdown-menu").html(str);
            var add_bank_id = new customDropDown($(".add_bank_id"));
        }
    });

//获取省、市、区
    $.ajax({
        type: 'post',
        url: '/iwuliu/dictionaryManager/provinceJson',
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
            }
            $(".add_province").find(".dropdown-menu").html(str);
            //省
            var add_province = new customDropDown($(".add_province"));
            //根据省获取市
            $(".add_province").find(".dropdown-menu li").bind("click", function () {
                var add_province = $(this).attr("value");
                if (add_province != '' && add_province != 'undefined') {
                    $.ajax({
                        type: "POST",
                        url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + add_province,
                        cache: false,
                        dataType: "json",
                        success: function (data) {
                            var str = "";
                            for (var i = 0; i < data.length; i++) {
                                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                            }
                            //市
                            $(".add_city").find(".dropdown-menu").html("");
                            $(".add_city").find(".placeHolder").html("请选择");
                            $(".add_city").find(".dropdown-menu").html(str);
                            //县
                            $(".add_area").find(".dropdown-menu").html("");
                            $(".add_area").find(".placeHolder").html("请选择");
                            $(".add_area").find(".placeHolder").attr("value", "");
                            //市
                            var add_city = new customDropDown($(".add_city"));
                            //根据市获取县
                            $(".add_city").find(".dropdown-menu li").bind("click", function () {
                                var add_city = $(this).attr("value");
                                if (add_city != '' && add_city != 'undefined') {
                                    $.ajax({
                                        type: "POST",
                                        url: "/iwuliu/dictionaryManager/areaJson?dicdata_code=" + add_city,
                                        cache: false,
                                        dataType: "json",
                                        success: function (data) {
                                            var str = "";
                                            for (var i = 0; i < data.length; i++) {
                                                str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
                                            }
                                            $(".add_area").find(".placeHolder").html(data[0].name);
                                            $(".add_area").find(".placeHolder").attr("value", data[0].id);
                                            $(".add_area").find(".dropdown-menu").html(str);
                                            var add_area = new customDropDown($(".add_area"));
                                        }
                                    });
                                }
                            })
                        }
                    });
                }
            })
        }
    });
}

// 新增保存
function stationNewAddSave() {
    if (checkNotNull($("#add_station_name")) & checkSelectNotNull($(".add_station_type").find(".placeHolder"))
        & checkSelectNotNull($(".add_province").find(".placeHolder")) & checkSelectNotNull($(".add_city").find(".placeHolder"))
        & checkSelectNotNull($(".add_area").find(".placeHolder")) & checkNotNull($("#add_townStreet")) & checkNotNull($("#add_longitude"))
        & checkNotNull($("#add_latitude")) & checkNotNull($("#add_work_begin_time")) & checkNotNull($("#add_work_end_time"))
        & checkNotNull($("#add_contact_name")) & checkNotNull($("#add_contact_phone")) & checkSelectNotNull($(".add_bank_id").find(".placeHolder"))
        & checkNotNull($("#add_bank_account")) & checkNotNull($("#add_bank_account_person_name")) & checkNotNull($("#add_bank_account_person_id"))
        & checkPhone($("#add_contact_phone")) & checkID($("#add_bank_account_person_id")) & checkBankNumber($("#add_bank_account"))) {

        var obj = {};
        var _stationName = $("#add_station_name").val();
        var _stationTypeValue = $(".add_station_type").find(".placeHolder").attr("value");
        var _provinceIdValue = $(".add_province").find(".placeHolder").attr("value");
        var _cityIdValue = $(".add_city").find(".placeHolder").attr("value");
        var _areaIdValue = $(".add_area").find(".placeHolder").attr("value");
        var _townStreet = $("#add_townStreet").val();
        var _longitude = $("#add_longitude").val();
        var _latitude = $("#add_latitude").val();
        var _workBeginTime = $("#add_work_begin_time").val();
        var _workEndTime = $("#add_work_end_time").val();
        var _contactName = $("#add_contact_name").val();
        var _contactPhone = $("#add_contact_phone").val();
        var _bankId = $(".add_bank_id").find(".placeHolder").attr("value");
        var _bankAccount = $("#add_bank_account").val();
        var _bankAccountPersonName = $("#add_bank_account_person_name").val();
        var _bankAccountPersonId = $("#add_bank_account_person_id").val();
        obj["station_name"] = _stationName;
        obj["station_type"] = _stationTypeValue;
        obj["province_id"] = _provinceIdValue;
        obj["city_id"] = _cityIdValue;
        obj["area_id"] = _areaIdValue;
        obj["town_street"] = _townStreet;
        obj["longitude"] = _longitude;
        obj["latitude"] = _latitude;
        obj["work_begin_time"] = _workBeginTime;
        obj["work_end_time"] = _workEndTime;
        obj["contact_name"] = _contactName;
        obj["contact_phone"] = _contactPhone;
        obj["bank_id"] = _bankId;
        obj["bank_account"] = _bankAccount;
        obj["bank_account_person_name"] = _bankAccountPersonName;
        obj["bank_account_person_id"] = _bankAccountPersonId;

        //开始ajax操作，data中传的是表单中的参数
        $("#stationNewAddForm").ajaxSubmit({
            type: "POST",//提交类型
            dataType: "json",//返回结果格式
            url: "/iwuliu/allianceBusiness/saveAllianceBusiness",//请求地址
            data: {"list": JSON.stringify(obj)},
            async: true,
            success: function (data) {
                var obj = eval(data);
                if (obj.result == 0) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#myModalNewAdd').modal('hide');
                        allianceBusinessQuery();
                    })
                } else if (obj.result == 2) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("服务站名称重复！");
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

// 点击编辑保存按钮
function stationEditSave() {
    if (checkNotNull($(".detail_station_name")) & checkSelectNotNull($(".detail_station_type").find(".placeHolder"))
        & checkSelectNotNull($(".detail_province").find(".placeHolder")) & checkSelectNotNull($(".detail_city").find(".placeHolder"))
        & checkSelectNotNull($(".detail_area").find(".placeHolder")) & checkNotNull($("#townStreet")) & checkNotNull($(".detail_longitude"))
        & checkNotNull($(".detail_latitude")) & checkNotNull($("#detail_work_begin_time")) & checkNotNull($("#detail_work_end_time"))
        & checkNotNull($(".contact_name")) & checkNotNull($(".contact_phone")) & checkSelectNotNull($(".detail_bank_id").find(".placeHolder"))
        & checkNotNull($(".bank_account")) & checkNotNull($(".bank_account_person_name")) & checkNotNull($(".bank_account_person_id"))
        & checkPhone($(".contact_phone")) & checkID($(".bank_account_person_id")) & checkBankNumber($(".bank_account"))) {

        var obj = {};
        var _stationName = $(".detail_station_name").val();
        var _stationTypeValue = $(".detail_station_type").find(".placeHolder").attr("value");
        var _provinceIdValue = $(".detail_province").find(".placeHolder").attr("value");
        var _cityIdValue = $(".detail_city").find(".placeHolder").attr("value");
        var _areaIdValue = $(".detail_area").find(".placeHolder").attr("value");
        var _townStreet = $("#townStreet").val();
        var _longitude = $(".detail_longitude").val();
        var _latitude = $(".detail_latitude").val();
        var _workBeginTime = $("#detail_work_begin_time").val();
        var _workEndTime = $("#detail_work_end_time").val();
        var _contactName = $(".contact_name").val();
        var _contactPhone = $(".contact_phone").val();
        var _bankId = $(".detail_bank_id").find(".placeHolder").attr("value");
        var _bankAccount = $(".bank_account").val();
        var _bankAccountPersonName = $(".bank_account_person_name").val();
        var _bankAccountPersonId = $(".bank_account_person_id").val();
        obj["station_id"] = station_id;
        obj["station_name"] = _stationName;
        obj["station_type"] = _stationTypeValue;
        obj["province_id"] = _provinceIdValue;
        obj["city_id"] = _cityIdValue;
        obj["area_id"] = _areaIdValue;
        obj["town_street"] = _townStreet;
        obj["longitude"] = _longitude;
        obj["latitude"] = _latitude;
        obj["work_begin_time"] = _workBeginTime;
        obj["work_end_time"] = _workEndTime;
        obj["contact_name"] = _contactName;
        obj["contact_phone"] = _contactPhone;
        obj["bank_id"] = _bankId;
        obj["bank_account"] = _bankAccount;
        obj["bank_account_person_name"] = _bankAccountPersonName;
        obj["bank_account_person_id"] = _bankAccountPersonId;

        console.log(JSON.stringify(obj));
        //开始ajax操作，data中传的是表单中的参数
        $("#stationEditForm").ajaxSubmit({
            type: "POST",//提交类型
            dataType: "json",//返回结果格式
            url: "/iwuliu/allianceBusiness/upDateAllianceBusiness",//请求地址
            data: {"list": JSON.stringify(obj)},
            async: true,
            success: function (data) {
                var obj = eval(data);
                if (obj.result == 1) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("保存成功！")
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                        $('#myModalDetail').modal('hide');
                        allianceBusinessQuery();
                    })
                } else if (obj.result == 0) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("服务信息不存在！");
                    $("#smallModalInfo").on("hidden.bs.modal", function (e) {
                        $("body").addClass("modal-open");
                    })
                    return false;
                } else if (obj.result == 3) {
                    //bootstrap提示框
                    $("#smallModalInfo").modal();
                    $("#smallModalInfo").find(".titleInfo").html("服务站名称重复！");
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
function allianceBusinessQueryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize: params.limit,   //页面大小
        pageNumber: params.pageNumber,  //页码
        station_name: $("#station_name").val(),
        station_type: $(".station_type").find(".placeHolder").attr("value"),
        province_id: $(".province_id").find(".placeHolder").attr("value"),
        city_id: $(".city_id").find(".placeHolder").attr("value"),
        area_id: $(".area_id").find(".placeHolder").attr("value"),
        town_street: $("#town_street").val(),
        sort: params.sort,  //排序列名
        sortOrder: params.order//排位命令（desc，asc）
    };
    return temp;
}

// 查询线下加盟服务商管理列表
function allianceBusinessQuery() {
    $('#allianceBusinessTable').bootstrapTable('refresh', {'url': '/iwuliu/allianceBusiness/allianceBusinessList'});
}

// 查询重置
function allianceBusinessQueryReset() {
    $('#station_name').val("");
    $('.placeHolder').html("请选择");
    $('.placeHolder').attr("value", "");
    $('.station_type').find(".placeHolder").html("全部");
    $('.station_type').find(".placeHolder").attr("value", "");
    $(".city_id").find(".dropdown-menu").html("");
    $(".area_id").find(".dropdown-menu").html("");
    $(".sr-only").removeClass("sr-only");
    $('#town_street').val("");
    $('#allianceBusinessTable').bootstrapTable('removeAll');
}

//goBtn按钮无数据时隐藏
var webservice = $('#allianceBusinessTable').attr("data-url");
$.ajax({
    url: webservice,
    success: function (data) {
        if (data.length == 0) {
            $(".form-inline").css("display", "none");
        }
    }
});

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

// 字段验证
//验证手机号码
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
        $(ele).parent().find("p").html("请输入手机号码");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}

//验证身份证号码
function checkID(ele) {
    var total = $(ele).val();
    if (total != "") {
        if (regExpID.test(total)) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的身份证号码！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入身份证号码");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }
}
//银行卡号码
function checkBankNumber(ele) {
    var total = $(ele).val();
    if (total != "") {
        if (regExpBankNumber.test(total)) {
            $(ele).parent().find("p").html("");
            return true;
        } else {
            $(ele).parent().find("p").html("请输入正确的银行卡号！");
            $(ele).parent().find("p").css("color", "red");
            return false;
        }
    } else {
        $(ele).parent().find("p").html("请输入银行卡号");
        $(ele).parent().find("p").css("color", "red");
        return false;
    }

}