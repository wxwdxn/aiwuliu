//车牌号码验证
var regTicNum=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;

//身份证
var regID = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;

// 驾驶证验证
var regDriverLicenceNumber = /\b\d{12}\b/;

//手机号正则
var regExpMob = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; //手机号码正则验证准确版

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
});

// 全局
$(function(){
    // 加载当前页面菜单
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".driverManager").parents(".collapse").addClass("in");
		$(".driverManager").addClass("menuActive");
		roleManager();
	})

	//行驶司机证件类型
	var IDType = new customDropDown($(".IDType"));
	//审核状态
	var status = new customDropDown($(".status"));
	//机构类型下拉
	var organizationType = new customDropDown($(".organizationType"));
	$(".organizationType ul").find("a").bind("click",function(){
		var _text = $(this).text();
		switch(_text){
			case "全部":
				$(".nameOfinstitution").removeClass("sr-only");
				$(".TheOwnerName").removeClass("sr-only");
				$(".theCrew").removeClass("sr-only");
				break;
			case "物流公司":
				$(".nameOfinstitution").removeClass("sr-only");
				$(".TheOwnerName").removeClass("sr-only");
				$(".theCrew").removeClass("sr-only");
				break;
			case "车主":
				$(".nameOfinstitution").addClass("sr-only");
				$(".TheOwnerName").removeClass("sr-only");
				$(".theCrew").removeClass("sr-only");
				$('.companyName').val(""); // 选择车主时，机构名称为空
				break;
			case "车组成员":
				$(".nameOfinstitution").addClass("sr-only");
				$(".TheOwnerName").addClass("sr-only");
				$(".theCrew").removeClass("sr-only");
				$('.companyName').val("");// 选择车组成员时，机构名称为空
				$('.truckOwner').val(""); // 选择车组成员时，车主为空
				break;
		}
	})
	// 表格
	var table = $('#driverManagerTable'),
			page = $('#drivers_page'),
			goBtn = $('#drivers_goBtn');
	// 跳转到某页
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});
	$(".th-inner").eq(0).append('<span>全选</span>');

})

// table表格隔行变色
function rowStyle(row, index) {
	if (index % 2 === 0) {
		return{};
	}
	return {
		css: {"background-color": "#eefff9"}
	};
}

// 格式化“证件类型一”
function firstIDTypeFormatter(value, row){
	return '<span>身份证</span>';
}

// 格式化“证件类型二”
function secondIDTypeFormatter(value, row){
	return '<span>驾驶证</span>';
}

// 点击重置按钮
function drivingManagerQueryReset() {
	$(".organizationType").find(".placeHolder").html("请选择");
	$('.companyName').val("");
	$('.truckOwner').val("");
	$('.crew').val("");
	$(".IDType").find(".placeHolder").html("请选择");
	$('.IDNumber').val("");
	$('.plateNumber').val("");
	$('.status').find(".placeHolder").html("请选择");
	$('#driverManagerTable').bootstrapTable('removeAll');
}

//配置参数
function driverManagerQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		organization_type: $(".organizationType").find(".placeHolder").attr("value"),
		company_name: $(".companyName").val(),
		owner_name: $(".truckOwner").val(),
		crew_name: $(".crew").val(),
		ID_type:  $(".status").find(".placeHolder").attr("value"),
		ID_num: $(".IDNumber").val(),
		plate_number: $(".plateNumber").val(),
		verify_status: $(".IDType").find(".placeHolder").attr("value"),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}

// 查询司机管理列表
function drivingManagerQuery(){
	$('#driverManagerTable').bootstrapTable('refresh',{'url':'/iwuliu/driverManager/driverManagerList'});
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


//点击详情按钮
function driverDetailClick(){
	// ======================>>详情编辑页面的下拉框
	// 获取审核状态
	$.ajax({
		type: 'post',
		url: "/iwuliu/dictionaryManager/obdBrandJson",
		dataType: 'json',
		cache: false,
		success: function (data) {
			var str = "";
			str += "<li onclick='undesire(this)'value='0'><a href='javascript:;'>验证中</a></li><li value='1' onclick='undesire(this)'>" +
					"<a href='javascript:;'>验证通过</a></li>" +
					"<li onclick='undesire(this)' value='3'><a href='javascript:;'>不合格</a></li>"
			$('.driving_status').find(".dropdown-menu").html(str);
			var driving_status = new customDropDown($(".driving_status"));
		}
	});
	var selects = $('#driverManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		var detailInfo = JSON.stringify(selects);//将获取的行转化为json对象，注意selects，而不是selects[0]
		$.ajax({
			type: "post",
			url: "/iwuliu/driverManager/getDriverDetailInfo",
			data:{detailInfo:encodeURI(detailInfo)},
			success: function (data) {
				var JsonData = eval(data);
				console.log(JsonData);
				// 让表单上所有p标签提示清空,避免编辑出现的提示保留到详情中
				$("#myModalDetail").find("p").html("");
				// 车辆信息
				$(".plate_number").val(JsonData.plate_number);
				$(".truck_type").val(JsonData.truck_type);
				$(".truck_carriage_type").val(JsonData.truck_carriage_type);
				$(".truck_length").val(JsonData.truck_length);
				$(".fuel_type").val(JsonData.fuel_type);
				$(".load_weight").val(JsonData.load_weight);
				$(".vehicle_identify_number").val(JsonData.vehicle_identify_number);
				$(".engine_number").val(JsonData.engine_number);
				$(".truck_pic").html("<span><img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.truck_first_pic_save_path + ">"
						+ "<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.truck_second_pic_save_path + ">"
						+ "<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.truck_third_pic_save_path + "></span>");
				$(".line").val(JsonData.transport_line);
				$(".driving_licence_first_page").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driving_licence_first_page_save_path + ">");
				$(".driving_licence_second_page").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driving_licence_second_page_save_path + ">");
				$(".submit_relate_time").val(JsonData.submit_relate_time);
				$(".confirm_relate_time").val(JsonData.confirm_relate_time);
				$(".submit_verify_time").val(JsonData.submit_verify_time);
				// 人员信息
				$(".organization_type").val(JsonData.organization_type);
				$(".company_name").val(JsonData.company_name);
				$(".owner_name").val(JsonData.owner_name);
				$(".owner_phone").val(JsonData.owner_phone);
				$(".id_card_number").val(JsonData.id_card_number);
				$(".id_card_front_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.id_card_front_pic_id + ">");
				$(".id_card_back_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.id_card_back_pic_id + ">");
				$(".driver_licence_number").val(JsonData.driver_licence_number);
				$(".driver_licence_main_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driver_licence_main_pic_id + ">");
				$(".driver_licence_sub_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driver_licence_sub_pic_id + ">");
				$(".qualification_certificate_number").val(JsonData.qualification_certificate_number);
				$(".qualification_certificate_number_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.qualification_certificate_number_pic_id + ">");
				// 车组成员
				$(".crew_name").val(JsonData.crew_name);
				$(".crew_phone").val(JsonData.crew_phone);
				$(".id_card_number_crew").val(JsonData.id_card_number_crew);
				$(".id_card_front_pic_id_crew").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.id_card_front_pic_id_crew + ">");
				$(".id_card_back_pic_id_crew").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.id_card_back_pic_id_crew + ">");
				$(".driver_licence_number_crew").val(JsonData.driver_licence_number_crew);
				$(".driver_licence_main_pic_id_crew").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driver_licence_main_pic_id_crew + ">");
				$(".driver_licence_sub_pic_id_crew").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.driver_licence_sub_pic_id_crew + ">");
				$(".qualification_certificate_number_crew").val(JsonData.qualification_certificate_number_crew);
				$(".qualification_certificate_number_pic_id_crew").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl=" + JsonData.qualification_certificate_number_pic_id_crew + ">");
				// 判断verify_refused_reason是否为undefined
				var verify_refused_reason = JsonData.verify_refused_reason;
				if (JsonData.verify_refused_reason==undefined){
					verify_refused_reason = "";
				}
				if (JsonData.verify_status=="0"){
					$(".driving_status").find(".placeHolder").html("验证中");
					$(".driving_status").find(".placeHolder").attr("value",JsonData.verify_status);//绑定id
					$(".verify_refused_reason").html('<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason">' + (verify_refused_reason) + '</textarea><p></p>');
				} else if(JsonData.driving_status=="3"){
					$(".driving_status").find(".placeHolder").html("不合格");
					$(".driving_status").find(".placeHolder").attr("value",JsonData.driving_status);//绑定id
					$(".verify_refused_reason").html('<textarea name="" rows="5" cols="100" id="verify_refused_reason">' + (verify_refused_reason) + '</textarea><p></p>');
				} else {
					$(".driving_status").find(".placeHolder").html("验证通过");
					$(".driving_status").find(".placeHolder").attr("value",JsonData.driving_status);//绑定id
					$(".verify_refused_reason").html('<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason">' + (verify_refused_reason) + '</textarea><p></p>');
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

//判断审核不通过填写审核拒绝理由
function undesire(ele) {
	var _text = $(ele).attr("value");
	if (_text == 3) {
		$(".verify_refused_reason").html('<textarea name="" rows="5" cols="100" id="verify_refused_reason"></textarea><p></p>')
	} else {
		$(".verify_refused_reason").html('<textarea disabled name="" rows="5" cols="100" id="verify_refused_reason"></textarea><p></p>')
	}
}

// 点击详情审核保存
function driverInfoEditSave(){
	var crew_phone = $('.crew_phone').val();
	var driving_status = $('.driving_status').find(".placeHolder").attr("value");
	var verify_refused_reason = $('.verify_refused_reason').val();
	$.ajax({
		type:"post",
		url:"/iwuliu/driverManager/driverVerify",
		data:{"crew_phone":crew_phone,
			"driving_status":driving_status,
			"verify_refused_reason":verify_refused_reason},
		async:true,
		success:function(object){
			// json 类型的专为对象
			var obj = eval(object);
			//  reload the user data
			if(obj.result==0){
				//bootstrap提示框
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("保存成功！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
					$('#myModalDetail').modal('hide');
					drivingManagerQuery();
				})
			}else{
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
}
