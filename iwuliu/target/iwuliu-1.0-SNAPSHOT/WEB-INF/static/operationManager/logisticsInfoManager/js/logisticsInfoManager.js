// 手机号正则
var regExpMob = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; //手机号码正则验证准确版
// 固定电话正则
var regExpfixedPhone = /0\d{2}-\d{8}|0\d{2}-\d{7}|0\d{3}-\d{7}|0\d{3}-\d{8}/;
// 数字
var numbers= /^\+?[0-9][0-9]*$/;
// 密码正则
var regExpPsd = /^.{6,20}$/;
//不能不包含特殊符号
var specialWord=/^([\u4E00-\u9FA5]|\w)*$/;

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
});

// 全局
$(function(){
	// 加载菜单
	$(".leftMenu").load("/iwuliu/welcomeManager/home", function() {
		$(".logisticsInfoManager").parents(".collapse").addClass("in");
		$(".logisticsInfoManager").addClass("menuActive");
		roleManager();
	});
	// 时分插件加载
	$('.clockpicker').clockpicker();

	// table表格跳转和全选设置
	var table = $('#companyManagerTable'),
			page = $('#company_page'),
			goBtn = $('#company_goBtn');

	// 跳转到某页
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});
	$(".th-inner").eq(0).append('<span>全选</span>');

	//新增模态框消失
	$("#myModalNewAdd").on("hidden.bs.modal",function(){
		$("#myModalNewAdd").find("input").val("");
		$("#add_login_password").val("000000");
		$("#add_payment_password").val("000000");
		$("#myModalNewAdd").find("p").html("");
		$("#myModalNewAdd").find(".placeHolder").html("请选择");
		$("#myModalNewAdd").find(".placeHolder").attr("value","");
		$("#myModalNewAdd").find(".dropdown-menu").html("");
		$("#myModalNewAdd").find(".formgroupImg").find(".display_inlineBlcok").html("点击右侧按钮上传图片"+"<p></p>");
	})
	//详情模态框消失
	$("#myModalDetail").on("hidden.bs.modal",function(){
		$("#myModalDetail").find(".form-group").addClass("uneditable");
		$("#myModalDetail").find("input").attr("disabled","true");
		$("#myModalDetail").find(".form-group").find("button").attr("disabled","true");
		$("#myModalDetail").find(".edit").attr("isClick","true");
		$("#myModalDetail").find(".edit").removeAttr("disabled");
		$("#myModalDetail").find(".edit").css("background","#69a2b5")
		$(".Detail_ModalQRCode").html("");
	})
	// 修改密码模态框消失
	$("#ModalPwd").on("hidden.bs.modal",function(){
		$("#ModalPwd").find("input").val("");
		$("#ModalPwd").find("p").html("");
	})
	//点击编辑按钮
	$(".edit").bind("click",function(){
		$(".latitudeAndlongitude").find("input").attr("disabled","true");
		$(".Pwd").find("input").attr("disabled","true");
		$("#myModalDetail").find(".hidden").removeClass("hidden")
	})
	//点击修改密码切换
	$(".btn_pwdType").find(".btn").click(function(){
		console.log();
		var _index = $(this).index();
		$(this).addClass("clickActive").siblings().removeClass("clickActive")
		switch(_index){
			case 0:
				$(".lPwd").removeClass("hidden");
				
				$(".pPwd").addClass("hidden")
				break;
			case 1:
				$(".lPwd").addClass("hidden");
				$(".pPwd").removeClass("hidden")
				break;
		}
	})
	//修改密码模态框消失重置
	$("#ModalPwd").on("hidden.bs.modal",function(){
		$("#ModalPwd").find(".btn_pwdType").find("a").eq(0).click();
	})
})

//图片放大
function ImgBig(ele){
	var _src = $(ele).attr("src");
	$(".modalImg").html("<img src="+ _src +"/>");
	$('#ModalImg').modal();
	//图片放大模态框隐藏后触发的事件
	$("#ModalImg").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open")
	})
}

//点击详情编辑按钮
var company_id;
function btn_ModalDetail() {
	$("#start_work_time").find("span").addClass("hidden");
	$("#finish_work_time").find("span").addClass("hidden");
	var selects = $('#companyManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		company_id = newSelects[0].company_id;
		$.ajax({
			type: "post",
			url: "/iwuliu/logisticsCompanyManager/getCompanyInfo",
			data:{company_id:encodeURI(company_id)},
			success: function (data) {
				var JsonData = eval(data);
				console.log(JsonData);
				// 让表单上所有p标签提示清空,避免编辑出现的提示保留到详情中
				$("#companyEditForm").find("p").html("");
				$(".company_name").val(JsonData.company_name);
				$(".contact_name").val(JsonData.contact_name);
				$(".contact_mobile_phone").val(JsonData.contact_mobile_phone);
				$(".business_licence").val(JsonData.business_licence);
				$(".company_fixed_phone").val(JsonData.company_fixed_phone);
				$(".contact_sex").html(JsonData.contact_sex);
				$(".contact_sex").attr("value",JsonData.contact_sex_value);// 绑定id
				$(".province").html(JsonData.province_id);
				$(".province").attr("value",JsonData.province_id_value);// 绑定id
				$(".city").html(JsonData.city_id);
				$(".city").attr("value",JsonData.city_id_value);// 绑定id
				$(".area").html(JsonData.area_id);
				$(".area").attr("value",JsonData.area_id_value);// 绑定id
				$(".townStreet").val(JsonData.town_street);
				$("#business_licence_pic").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ JsonData.business_licence_pic_id +">");
				$("#store_pic").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ JsonData.store_pic_id +">");
				$(".longitude").val(JsonData.longitude);
				$(".latitude").val(JsonData.latitude);
				$(".start_work_time").val(JsonData.start_work_time);
				$(".finish_work_time").val(JsonData.finish_work_time);
				$(".bank_id").html(JsonData.bank_id);
				$(".bank_id").attr("value",JsonData.bank_id_value);// 绑定id
				$(".bank_account").val(JsonData.bank_account);
				$(".member_password").val("******");
				$(".payment_password").val("******");
				// 二维码
				var script = document.createElement("script");
				script.setAttribute("type", "text/javascript");
				script.setAttribute("src", "/iwuliu/static/common/js/jquery.qrcode.min.js");
				script.setAttribute("class", "qrcode");
				$("head")[0].appendChild(script);
				script.onload = function () {
					var _value = company_id;
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

// 点击编辑保存按钮
function companyEditSave(){
	if(checkSelectNotNull($(".detail_province").find(".placeHolder"))&checkSelectNotNull($(".detail_city").find(".placeHolder"))
			&checkSelectNotNull($(".detail_area").find(".placeHolder"))&checkNotNull($(".townStreet"))&
			checkNotNull($(".company_name"))&checkNotNull($(".contact_name"))&checkPhone($(".contact_mobile_phone"))
			&checkLicence($(".business_licence"))&checkFixedPhone($(".company_fixed_phone"))
			&checkSelectNotNull($(".detail_sex").find(".placeHolder"))
			&checkImgNotNull($("#business_licence_pic"))&checkImgNotNull($("#store_pic"))&
			checkSelectNotNull($(".detail_bank_id").find(".placeHolder"))&checkBankAccount($(".bank_account"))
	&checkImgNotNull($("#business_licence_pic"))&checkImgNotNull($("#store_pic"))){
		// 封装表单中的参数
		var companyInfoObj = {
			// 主键id
			"company_id":company_id,
			// 下拉框
			"detail_province":$(".detail_province").find(".placeHolder").attr("value"),
			"detail_city":$(".detail_city").find(".placeHolder").attr("value"),
			"detail_area":$(".detail_area").find(".placeHolder").attr("value"),
			"detail_sex":$(".detail_sex").find(".placeHolder").attr("value"),
			"detail_bank_id":$(".detail_bank_id").find(".placeHolder").attr("value"),
			// input框
			"detail_townStreet":$(".townStreet").val(),
			"detail_company_name":$(".company_name").val(),
			"detail_contact_name":$(".contact_name").val(),
			"detail_contact_mobile_phone":$(".contact_mobile_phone").val(),
			"detail_business_licence":$(".business_licence").val(),
			"detail_company_fixed_phone":$(".company_fixed_phone").val(),
			"detail_longitude":$(".longitude").val(),
			"detail_latitude":$(".latitude").val(),
			"detail_start_work_time":$(".start_work_time").val(),
			"detail_finish_work_time":$(".finish_work_time").val(),
			"detail_bank_account":$(".bank_account").val(),
			"detail_member_password":$(".member_password").val(),
			"detail_payment_password":$(".payment_password").val(),
		};
		console.log(JSON.stringify(companyInfoObj));
		//开始ajax操作，data中传的是表单中的参数
		$("#companyEditForm").ajaxSubmit({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/logisticsCompanyManager/editCompanyInfo",//请求地址
			data:{"companyInfo":JSON.stringify(companyInfoObj)
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
						companyManagerQuery();
					})
				}else if (obj.result==2){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("手机号已经注册！");
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
		$("#smallModalInfo").find(".titleInfo").html("数据格式不正确！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		})
		return false;
	}
}


var longitudeCommon, latitudeCommon;
//点击获取经纬度(新增页面)
function getMap(ele){
	//地图模态框消失
	$("#ModalMap").on("hidden.bs.modal", function () {
		$("#ModalMap").find("input").val("");
	});
	var _formParents = $(ele).parents(".form-inline");
	//省
	var province = _formParents.find(".province_add")
	//市
	var city = _formParents.find(".city_add")
	//县
	var area = _formParents.find(".area_add");
	//村镇街道
	var townStreet = _formParents.find("#add_townStreet");
	//经度
	var add_longitude = _formParents.find("#add_longitude");
	longitudeCommon = add_longitude
	//纬度
	var add_latitude = _formParents.find("#add_latitude");
	latitudeCommon = add_latitude
	$("#ModalMap").modal();
	//省市县
	$(".map_address").val(province.html() + city.html() + area.html() + townStreet.val());
	//图片放大模态框隐藏后触发的事件
	$("#ModalMap").on("hidden.bs.modal", function () {
		$("body").addClass("modal-open")
	})
}
//点击获取经纬度(详情页面)
function getMapOfDetail(ele){
	//地图模态框消失
	$("#ModalMap").on("hidden.bs.modal", function () {
		$("#ModalMap").find("input").val("");
	});
	var _formParents = $(ele).parents(".form-inline");
	//省
	var province = _formParents.find(".province")
	//市
	var city = _formParents.find(".city")
	//县
	var area = _formParents.find(".area");
	//村镇街道
	var townStreet = _formParents.find(".townStreet");
	//经度
	var add_longitude = _formParents.find(".longitude");
	longitudeCommon = add_longitude
	//纬度
	var add_latitude = _formParents.find(".latitude");
	latitudeCommon = add_latitude
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

//点击新增物流公司按钮
function btn_NewAdd(){
	// =================== 新增页面下拉框
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
			$(".add_contact_sex").find(".dropdown-menu").html(str);
			var add_contact_sex = new customDropDown($(".add_contact_sex"));
		}
	});

//获取省、市、区
	$.ajax({
		type:'post',
		url:'/iwuliu/dictionaryManager/provinceJson',
		dataType:'json',
		cache:'false',
		success:function(data){
			var str="";
			for (var i= 0;i<data.length;i++){
				str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".add_province").find(".dropdown-menu").html(str);
			//省
			var add_province = new customDropDown($(".add_province"));
			//根据省获取市
			$(".add_province").find(".dropdown-menu li").bind("click",function(){
				var add_province=$(this).attr("value");
				if (add_province!=''&&add_province!='undefined'){
					$.ajax({
						type: "POST",
						url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + add_province,
						cache: false,
						dataType: "json",
						success: function (data) {
							var str="";
							for (var i= 0;i<data.length;i++){
								str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
							}
							//市
							$(".add_city").find(".dropdown-menu").html("");
							$(".add_city").find(".placeHolder").html("请选择");
							$(".add_city").find(".dropdown-menu").html(str);
							//县
							$(".add_area").find(".dropdown-menu").html("");
							$(".add_area").find(".placeHolder").html("请选择");
							$(".add_area").find(".placeHolder").attr("value","");
							//市
							var add_city = new customDropDown($(".add_city"));
							//根据市获取县
							$(".add_city").find(".dropdown-menu li").bind("click",function(){
								var add_city = $(this).attr("value");
								if (add_city!=''&&add_city!='undefined'){
									$.ajax({
										type: "POST",
										url: "/iwuliu/dictionaryManager/areaJson?dicdata_code=" + add_city,
										cache: false,
										dataType: "json",
										success: function (data) {
											var str="";
											for (var i= 0;i<data.length;i++){
												str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
											}
											$(".add_area").find(".placeHolder").html(data[0].name);
											$(".add_area").find(".placeHolder").attr("value",data[0].id);
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

//获取开户银行
	$.ajax({
		type:'post',
		url:'/iwuliu/dictionaryManager/bankJson',
		dataType:'json',
		cache:'false',
		success:function(data){
			var str="";
			for (var i= 0;i<data.length;i++){
				str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".add_bank_id").find(".dropdown-menu").html(str);
			var add_bank_id = new customDropDown($(".add_bank_id"));
		}
	});

	$("#myModalNewAdd").modal();
}

// 新增保存
function companyNewAddSave(){
	if(checkSelectNotNull($(".add_province").find(".placeHolder"))&checkSelectNotNull($(".add_city").find(".placeHolder"))
			&checkSelectNotNull($(".add_area").find(".placeHolder"))&checkNotNull($("#add_townStreet"))&
			checkNotNull($("#add_company_name"))&checkNotNull($("#add_contact_name"))&checkPhone($("#add_contact_mobile_phone"))
			&checkLicence($("#add_business_licence"))&checkFixedPhone($("#add_company_fixed_phone"))&checkSelectNotNull($(".add_contact_sex").find(".placeHolder"))
			&checkImgNotNull($(".add_business_licence_pic"))&checkImgNotNull($(".add_store_pic"))&
			checkSelectNotNull($(".add_bank_id").find(".placeHolder"))&checkBankAccount($("#add_bank_account"))){
		// 封装表单中的参数
		var companyInfoObj = {
			// 下拉框
			"add_province":$(".add_province").find(".placeHolder").attr("value"),
			"add_city":$(".add_city").find(".placeHolder").attr("value"),
			"add_area":$(".add_area").find(".placeHolder").attr("value"),
			"add_contact_sex":$(".add_contact_sex").find(".placeHolder").attr("value"),
			"add_bank_id":$(".add_bank_id").find(".placeHolder").attr("value"),
			// input框
			"add_townStreet":$("#add_townStreet").val(),
			"add_company_name":$("#add_company_name").val(),
			"add_contact_name":$("#add_contact_name").val(),
			"add_contact_mobile_phone":$("#add_contact_mobile_phone").val(),
			"add_company_fixed_phone":$("#add_company_fixed_phone").val(),
			"add_business_licence":$("#add_business_licence").val(),
			"add_longitude":$("#add_longitude").val(),
			"add_latitude":$("#add_latitude").val(),
			"add_start_work_time":$("#add_start_work_time").val(),
			"add_finish_work_time":$("#add_finish_work_time").val(),
			"add_bank_account":$("#add_bank_account").val(),
			"add_login_password":$("#add_login_password").val(),
			"add_payment_password":$("#add_payment_password").val()
		};
		//开始ajax操作，data中传的是表单中的参数
		$("#companyNewAddForm").ajaxSubmit({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/logisticsCompanyManager/saveCompanyInfo",//请求地址
			data:{"companyInfo":JSON.stringify(companyInfoObj)
			},
			success:function(data){
				var obj = eval(data);
				if(obj.result==1){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存成功！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
						$('#myModalNewAdd').modal('hide');
						companyManagerQuery();
					})
				}else if (obj.result==0){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("营业执照重复！");
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					})
					return false;
				} else if (obj.result==2){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("手机号已经注册！");
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
		$("#smallModalInfo").find(".titleInfo").html("数据格式不正确！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		})
		return false;
	}
}

//点击修改密码
var company_id_eidtPsw;
function btn_ModalPwd(){
	var selects = $('#companyManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		$("#ModalPwd").modal();
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		company_id_eidtPsw = newSelects[0].company_id;
	}else {
		$(".titleInfo").html("请选择一行数据")
		$("#smallModalInfo").modal();
	}
}

// 修改登录密码
function editLoginPassword(){
	if (checkPsd($(".old_member_password"))&checkPsd($(".new_member_password"))
			&checkPsd($(".new_member_password_again"))
			&checkAgainPSW($(".new_member_password"),$(".new_member_password_again"))){
		// 封装表单中的参数
		var editPswInfo = {
			"company_id":company_id_eidtPsw,
			"old_member_password":$(".old_member_password").val(),
			"new_member_password":$(".new_member_password").val(),
			"new_member_password_again":$(".new_member_password_again").val()
		}
		//开始ajax操作，data中传的是表单中的参数
		$.ajax({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/logisticsCompanyManager/eiditLoginPsw",//请求地址
			data:{"editPswInfo":JSON.stringify(editPswInfo)
			},
			success:function(data){
				var obj = eval(data);
				if(obj.result==1){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("修改登录密码成功！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
						$('#ModalPwd').modal('hide');
						//window.top.location.href = "/iwuliu/login/memberHome";
					})
				}else if (obj.result==-2){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("旧密码错误！");
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					})
					return false;
				} else if (obj.result==2){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("新密码与旧密码重复！");
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					})
					return false;
				} else {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("修改登录密码失败！");
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

	}
}

// 修改支付密码
function editPaymentPassword(){
	if (checkPsd($(".old_payment_password"))&checkPsd($(".new_payment_password"))
			&checkPsd($(".new_payment_password_again"))
			&checkAgainPSW($(".new_payment_password"),$(".new_payment_password_again"))){
		// 封装表单中的参数
		var editPswInfo = {
			"company_id":company_id_eidtPsw,
			"old_payment_password":$(".old_payment_password").val(),
			"new_payment_password":$(".new_payment_password").val(),
			"new_payment_password_again":$(".new_payment_password_again").val()
		}
		//开始ajax操作，data中传的是表单中的参数
		$.ajax({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/logisticsCompanyManager/eiditPaymentPsw",//请求地址
			data:{"editPswInfo":JSON.stringify(editPswInfo)
			},
			success:function(data){
				var obj = eval(data);
				if(obj.result==1){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("修改支付密码成功！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
						$('#ModalPwd').modal('hide');
						companyManagerQuery();
					})
				}else if (obj.result==-2){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("旧密码错误！");
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					})
					return false;
				} else if (obj.result==2){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("新密码与旧密码重复！");
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					})
					return false;
				} else {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("修改支付密码失败！");
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

	}
}


// 物流公司首页面
// 重置功能
function companyManagerQueryReset() {
	$('#company_name').val("");
	$('#contact_name').val("");
	$('#contact_mobile_phone').val("");
	$('#business_licence').val("");
	$('#city_id').val("");
	$('#companyManagerTable').bootstrapTable('removeAll');
}

// 查询物流公司管理列表
function companyManagerQuery() {
	$('#companyManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/logisticsCompanyManager/companyManagerList'});
}


//配置参数
function companyManagerTableQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		company_name: $("#company_name").val(),
		contact_name: $("#contact_name").val(),
		contact_mobile_phone: $("#contact_mobile_phone").val(),
		business_licence: $("#business_licence").val(),
		city_id:$("#city_id").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
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

// 格式化“支付密码”
function paymentPasswordFormatter(value, row){
	return '<span>******</span>';
}

// 格式化“登录密码”
function loginPasswordFormatter(value, row){
	return '<span>******</span>';
}

// 格式化“二维码照片”
function checkCodeFormatter(){
	return '<a type="button" isClick=true class="NewDetail btn btn-success" ' +
			'onclick="qrCodePhotoDetail(this.parentElement.parentElement)">查看二维码</a>';

}

// 点击“详情”查看二维码
function qrCodePhotoDetail(value) {
	var script = document.createElement("script");
	script.setAttribute("type", "text/javascript");
	script.setAttribute("src", "/iwuliu/static/common/js/jquery.qrcode.min.js");
	script.setAttribute("class", "qrcode");
	$("head")[0].appendChild(script);
	script.onload = function () {
		var _value = $(value).find(".hidden").html();
		var companyName= $(value).find(".companyName").html();
		$(".QRCodeCompanyName").html(companyName);
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

// 点击删除按钮
function btn_Del() {
	var selects = $('#companyManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length != 0) {
		$(".titleInfoDelet").html("确定删除？")
		$("#smallModalInfoDelet").modal();
		$(".confirmDelet").click(function () {
			var company_id = "";
			for (var i = 0; i < selects.length; i++) {
				if (i == 0) {
					company_id = selects[i].company_id;
				} else {
					company_id += "," + selects[i].company_id;
				}
			};
			$.ajax({
				type: "post",
				url: "/iwuliu/logisticsCompanyManager/logisticsCompanyDel?company_id=" + company_id,
				async: true,
				success: function (object) {
					// json 类型的专为对象
					var obj = eval(object);
					console.log(obj)
					//  reload the user data
					if (obj.result == 1) {
						$('#companyManagerTable').bootstrapTable('refresh');
						$(".titleInfo").html("删除成功！");
						$("#smallModalInfo").modal();smallModalInfoDelet
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
		$(".titleInfo").html("请至少选择一行数据")
		$("#smallModalInfo").modal();
	}
}

//图片上传
function loadImg(ele) {
	$(ele).parents(".formgroupImg").find(".sr-only").click();
	//点击图片放大
	$(".formgroupImg").find("img").bind("click",function(){
		var _src = $(this).attr("src");
		$(".modalImg").html("<img src="+ _src +"/>");
		$('#ModalImg').modal();
		//图片放大模态框隐藏后触发的事件
		$("#ModalImg").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open")
		})
	})
}

//图片上传预览    IE是用了滤镜。
function previewImage(ele){
	var MAXWIDTH  = 100;
	var MAXHEIGHT = 40;
	var imgSrc;
	if(ele.files && ele.files[0]){
		$(ele).parents(".formgroupImg").find(".display_inlineBlcok").html('<img title="点击放大显示"/>')
		var img = $(ele).parents(".formgroupImg").find("img")[0];
		img.onload = function() {
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
			img.width  =  rect.width;
			img.height =  rect.height;
		}
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
			imgSrc = evt.target.result;
		}
		reader.readAsDataURL(ele.files[0]);
	} else {
		ele.blur();
		var sFilter = 'FILTER: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)';
		$(ele).parents(".formgroupImg").find(".display_inlineBlcok").html('<img title="点击放大显示"/>')
		var img = $(ele).parents(".formgroupImg").find("img")[0];
		var src = document.selection.createRange().htmlText;
		img.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="+src+")";
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
	if(width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;
		if(rateWidth > rateHeight) {
			param.width =  maxWidth;
			param.height = Math.round(height / rateWidth);
		} else   {
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
	}
});

//获取开户银行
$.ajax({
	type:'post',
	url:'/iwuliu/dictionaryManager/bankJson',
	dataType:'json',
	cache:'false',
	success:function(data){
		var str="";
		for (var i= 0;i<data.length;i++){
			str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
		}
		$(".detail_bank_id").find(".dropdown-menu").html(str);
		var detail_bank_id = new customDropDown($(".detail_bank_id"));
	}
});

//获取省、市、区
$.ajax({
	type:'post',
	url:'/iwuliu/dictionaryManager/provinceJson',
	dataType:'json',
	cache:'false',
	success:function(data){
		var str="";
		for (var i= 0;i<data.length;i++){
			str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
		}
		$(".detail_province").find(".dropdown-menu").html(str);
		//省
		var add_province = new customDropDown($(".detail_province"));
		//根据省获取市
		$(".detail_province").find(".dropdown-menu li").bind("click",function(){
			var detail_province=$(this).attr("value");
			if (detail_province!=''&&detail_province!='undefined'){
				$.ajax({
					type: "POST",
					url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + detail_province,
					cache: false,
					dataType: "json",
					success: function (data) {
						var str="";
						for (var i= 0;i<data.length;i++){
							str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
						}
						//市
						$(".detail_city").find(".dropdown-menu").html("");
						$(".detail_city").find(".placeHolder").html("请选择");
						$(".detail_city").find(".dropdown-menu").html(str);
						//县
						$(".detail_area").find(".dropdown-menu").html("");
						$(".detail_area").find(".placeHolder").html("请选择");
						$(".detail_area").find(".placeHolder").attr("value","");
						//市
						var detail_city = new customDropDown($(".detail_city"));
						//根据市获取县
						$(".detail_city").find(".dropdown-menu li").bind("click",function(){
							var detail_city = $(this).attr("value");
							if (detail_city!=''&&detail_city!='undefined'){
								$.ajax({
									type: "POST",
									url: "/iwuliu/dictionaryManager/areaJson?dicdata_code=" + detail_city,
									cache: false,
									dataType: "json",
									success: function (data) {
										var str="";
										for (var i= 0;i<data.length;i++){
											str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
										}
										$(".detail_area").find(".placeHolder").html(data[0].name);
										$(".detail_area").find(".placeHolder").attr("value",data[0].id);
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


// ===================字段验证
//验证公司手机号码
function checkPhone(ele){
	var total=$(ele).val();
	if(total != ""){
		if (regExpMob.test(total)){
			//$(ele).css("border","1px solid #ccc");
			$(ele).parent().find("p").html("");
			return true;
		}else {
			$(ele).parent().find("p").html("请输入正确的手机号码！");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	}else{
		$(ele).parent().find("p").html("请输入手机号码");
		$(ele).parent().find("p").css("color","red");
		return false;
	}
}

//验证营业执照号码
function checkLicence(ele){
	var total=$(ele).val();
	if(total != ""){
		if (numbers.test(total)){
			$(ele).parent().find("p").html("");
			return true;
		}else {
			$(ele).parent().find("p").html("请输入数字！");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	} else {
		$(ele).parent().find("p").html("请输入营业执照号码");
		$(ele).parent().find("p").css("color","red");
		return false;
	}
}

//验证银行账户
function checkBankAccount(ele){
	var total=$(ele).val();
	if(total != ""){
		if (numbers.test(total)){
			$(ele).parent().find("p").html("");
			return true;
		}else {
			$(ele).parent().find("p").html("请输入数字！");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	} else {
		$(ele).parent().find("p").html("请输入银行账户号码");
		$(ele).parent().find("p").css("color","red");
		return false;
	}
}

//验证公司固定电话号码
function checkFixedPhone(ele){
	var total=$(ele).val();
	if(total != ""){
		if (regExpfixedPhone.test(total)){
			$(ele).parent().find("p").html("");
			return true;
		}else {
			$(ele).parent().find("p").html("请输入正确的电话号码！");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	}else{
		$(ele).parent().find("p").html("请输入电话号码");
		$(ele).parent().find("p").css("color","red");
		return false;
	}
}


//验证密码
function checkPsd(ele){
	var total=$(ele).val();
	if(total != ""){
		if (!specialWord.test(total)){
			$(ele).parent().find("p").text("不能输入特殊字符");
			$(ele).parent().find("p").css("color","red");
			return false;
		} else if (regExpPsd.test(total)){
			$(ele).parent().find("p").html("");
			return true;
		} else {
			$(ele).parent().find("p").html("密码必须为6-20位");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	}else{
		$(ele).parent().find("p").html("请输入密码");
		$(ele).parent().find("p").css("color","red");
		return false;
	}
}

// 判断两次输入的密码是否一致
function checkAgainPSW(fir,sec) {
		if(fir.val() == sec.val()) {
			sec.parents(".form-group").find("p").text("输入正确");
			sec.parents(".form-group").find("p").css("color","#fff");
			return true;
		} else {
			sec.parents(".form-group").find("p").text("两次密码不一致 请重新输入");
			sec.parents(".form-group").find("p").css("color","red");
			return false;
	}
}

// 不能为空的正则校验(input输入框使用)
function checkNotNull(inp) {
	var _value = inp.val();
	var _p = $(inp).parents(".form-group").find("p");
	if(_value!=''){
		_p.html("");
		return true;
	}else{
		_p.css("color","red");
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
	var _p = $(inp).find("p");
	if(_value!=''&&_value!=undefined){
		_p.html("");
		return true;
	}else{
		_p.css("color","red");
		_p.html("未上传图片！");
		return false;
	}
}



