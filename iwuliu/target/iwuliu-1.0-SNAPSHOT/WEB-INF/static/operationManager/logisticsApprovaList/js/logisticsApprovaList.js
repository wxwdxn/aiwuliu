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
		$(".logisticsApprovaList").parents(".collapse").addClass("in");
		$(".logisticsApprovaList").addClass("menuActive");
		roleManager();
	})

	// 物流公司审核，机构名称动态赋值
	$.ajax({
		type:"post",
		url:"/iwuliu/accountCenterManager/searchCompanyName",
		cache:"false",
		success:function(object){
			var obj = eval(object);
			var account_name = obj.company_name;
			var account_id = obj.company_id;
			if(account_id == '742C9E4DFC6940689A5D0F7CF6A69649'){
				$(".company_name").val("");
			}else{
				$(".company_name").val(account_name);
				$(".company_name").attr("readonly","readonly");
			}
		}
	})

	//审批列表名单
	new customDropDown($(".listOfexaminationApproval"));
	$(".listOfexaminationApproval").find("li").bind("click",function(){
		var _index = $(this).index();
		switch(_index){
			case 0:
				$(".drivers").removeClass("hidden");
				$(".cars").addClass("hidden")
				break;
			case 1:
				$(".drivers").addClass("hidden");
				$(".cars").removeClass("hidden")
				break;
		}
	})

	// 司机表格
	var table = $('#driversManagerTable'),
			page = $('#driver_page'),
			goBtn = $('#driver_goBtn');
	// 跳转到某页
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});
	// 单选
	$("#driversManagerTable .th-inner").eq(0).append('<span>单选</span>');

	// 车辆表格
	var trucksManagerTable = $('#trucksManagerTable'),
			truck_page = $('#truck_page'),
			truck_goBtn = $('#truck_goBtn');
	// 跳转到某页
	truck_goBtn.click(function () {
		trucksManagerTable.bootstrapTable('selectPage', +truck_page.val());
	});
	// 单选
	$("#trucksManagerTable .th-inner").eq(0).append('<span>单选</span>');

	//司机详情模态框消失
	$("#DrivermyModal").on("hidden.bs.modal",function(){
		$("#qrCode_driver").html("");
	})

	// 司机拒绝模态框消失
	$("#driverModalInfo").on("hidden.bs.modal",function(){
		$("#driverModalInfo").find("p").html("");
		$("#reason_driver").val("");
	})

	//车辆详情模态框消失
	$("#CarsmyModal").on("hidden.bs.modal",function(){
		$("#qrCode_truck").html("");
	})

	// 车辆拒绝模态框消失
	$("#truckModalInfo").on("hidden.bs.modal",function(){
		$("#truckModalInfo").find("p").html("");
		$("#reason_truck").val("");
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

// =================>>>司机审核
//配置查询参数
function driversManagerQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		company_name: $(".company_name").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}

function trucksManagerQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		company_name: $(".company_name").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}
// 查询列表
function queryList(){
	var type = $(".listOfexaminationApproval").find(".placeHolder").attr("value");
	if (type == 0) {
		$('#driversManagerTable').bootstrapTable('refresh', {'url':'/iwuliu/logisticsApprovaManager/findDriversList'});
	} else {
		$('#trucksManagerTable').bootstrapTable('refresh', {'url':'/iwuliu/logisticsApprovaManager/findTrucksList'});
	}
}

// 重置
function queryListReset(){
	$(".company_name").val("");
}

// 审核同意司机
function agreeDriver(){
	var selects = $('#driversManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		var person_id = newSelects[0].person_id;
		$.ajax({
			type: "post",
			url: "/iwuliu/logisticsApprovaManager/agreeDriver",
			data:{person_id:encodeURI(person_id)},
			success: function (data) {
				var JsonData = eval(data);
				console.log(JsonData);
				if (JsonData.result == 1) {
					$(".titleInfo").html("操作成功！");
					$("#smallModalInfo").modal();
					queryList();
				} else {
					$(".titleInfo").html("操作失败！");
					$("#smallModalInfo").modal();
				}
			},
			error: function () {
				$(".titleInfo").html("系统错误！")
				$("#smallModalInfo").modal();
			}
		});
	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
		return false;
	}
}

// 拒绝同意司机
function disagreeDriver(){
	var selects = $('#driversManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		$("#driverModalInfo").modal();
		$("#driverConfirm").click(function(){
			if (checkNotNull($("#reason_driver"))){
				var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
				person_id = newSelects[0].person_id;
				$.ajax({
					type: "post",
					url: "/iwuliu/logisticsApprovaManager/disagreeDriver",
					data:{person_id:encodeURI(person_id),
						verify_refused_reason:$("#reason_driver").val()
					},
					success: function (data) {
						var JsonData = eval(data);
						console.log(JsonData);
						if (JsonData.result == 1) {
							$(".titleInfo").html("操作成功！");
							$("#smallModalInfo").modal();
							queryList();
						} else {
							$(".titleInfo").html("操作失败！");
							$("#smallModalInfo").modal();
						}
					},
					error: function () {
						$(".titleInfo").html("系统错误！")
						$("#smallModalInfo").modal();
					}
				});
			}else {
				//bootstrap提示框
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("请填写拒绝理由！")
				$("#smallModalInfo").on("hidden.bs.modal", function (e) {
					$("body").addClass("modal-open");
				})
				return false;
			}

		});

	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
		return false;
	}
}

//司机管理详情页面
function btn_DriverModalDetail(){
	var selects = $('#driversManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1){
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		var person_id = newSelects[0].person_id;
		$.ajax({
			type: "post",
			url: "/iwuliu/logisticsDriverManager/getDriverInfo",
			data: {person_id: encodeURI(person_id)},
			success: function (data) {
				var JsonData = eval(data);
				console.log(JsonData);
				$("#company_name_driver").val(newSelects[0].company_name);
				$("#person_name").val(JsonData.person_name);
				$("#person_mobile_phone").val(JsonData.person_mobile_phone);
				$("#id_card_number").val(JsonData.id_card_number);
				$("#driver_licence_number").val(JsonData.driver_licence_number);
				$("#qualification_certificate_number").val(JsonData.qualification_certificate_number);
				// 不可编辑
				$("#verify_status_value").val(JsonData.verify_status_value);
				// 图片
				$("#id_card_front_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
						"fileUrl=" + JsonData.id_card_front_pic_id + ">");
				$("#id_card_back_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
						"fileUrl=" + JsonData.id_card_back_pic_id + ">");
				$("#driver_licence_main_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
						"fileUrl=" + JsonData.driver_licence_main_pic_id + ">");
				$("#driver_licence_sub_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
						"fileUrl=" + JsonData.driver_licence_sub_pic_id + ">");
				$("#qualification_certificate_number_pic_id").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?" +
						"fileUrl=" + JsonData.qualification_certificate_number_pic_id + ">");
				// 二维码
				var script = document.createElement("script");
				script.setAttribute("type", "text/javascript");
				script.setAttribute("src", "/iwuliu/static/common/js/jquery.qrcode.min.js");
				script.setAttribute("class", "qrcode");
				$("head")[0].appendChild(script);
				script.onload = function () {
					var _value = newSelects[0].company_id;
					$("#qrCode_driver").qrcode({
						render: "table", //table方式
						width: 200, //宽度
						height: 200, //高度
						text: _value //任意内容
					});
				}
			}
		});
		$("#DrivermyModal").modal();
	}
}

// 审核同意车辆
function agreeTruck(){
	var selects = $('#trucksManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		var plate_number = newSelects[0].plate_number;
		$.ajax({
			type: "post",
			url: "/iwuliu/logisticsApprovaManager/agreeTruck",
			data:{plate_number:plate_number},
			success: function (data) {
				var JsonData = eval(data);
				console.log(JsonData);
				if (JsonData.result == 1) {
					$(".titleInfo").html("操作成功！");
					$("#smallModalInfo").modal();
					queryList();
				} else {
					$(".titleInfo").html("操作失败！");
					$("#smallModalInfo").modal();
				}
			},
			error: function () {
				$(".titleInfo").html("系统错误！")
				$("#smallModalInfo").modal();
			}
		});
	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
		return false;
	}

}

// 拒绝同意车辆
function disagreeTruck(){
	var selects = $('#trucksManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		$("#truckModalInfo").modal();
		$("#trucKConfirm").click(function(){
			if (checkNotNull($("#reason_truck"))){
				var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
				var plate_number = newSelects[0].plate_number;
				$.ajax({
					type: "post",
					url: "/iwuliu/logisticsApprovaManager/disagreeTruck",
					data:{plate_number:plate_number,
						verify_refused_reason:$("#reason_truck").val()},
					success: function (data) {
						var JsonData = eval(data);
						console.log(JsonData);
						if (JsonData.result == 1) {
							$(".titleInfo").html("操作成功！");
							$("#smallModalInfo").modal();
							$('#trucksManagerTable').bootstrapTable('refresh', {'url':'/iwuliu/logisticsApprovaManager/findTrucksList'});
						} else {
							$(".titleInfo").html("操作失败！");
							$("#smallModalInfo").modal();
						}
					},
					error: function () {
						$(".titleInfo").html("系统错误！")
						$("#smallModalInfo").modal();
					}
				});
			}else {
				//bootstrap提示框
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("请填写拒绝理由！")
				$("#smallModalInfo").on("hidden.bs.modal", function (e) {
					$("body").addClass("modal-open");
				})
				return false;
			}

		});



	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
		return false;
	}
}

//司机车辆详情页面
function btn_CarModalDetail(){
	var selects = $('#trucksManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1){
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		var plate_number = newSelects[0].plate_number;
		var organization_type = "";
		$.ajax({
			type: "post",
			url: "/iwuliu/vehicleManager/vehicleDetail",
			data: {plate_number: encodeURI(plate_number), organization_type: encodeURI(organization_type)},
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success: function (data) {
				var JsonData = JSON.parse(data);
				console.log(JsonData);
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
				$("#organization_name").val(JsonData.organization_name);
				$("#verify_refused_reason").val(JsonData.verify_refused_reason);
				$("#verify_status").val(JsonData.verify_status);
				// 二维码
				var script = document.createElement("script");
				script.setAttribute("type", "text/javascript");
				script.setAttribute("src", "/iwuliu/static/common/js/jquery.qrcode.min.js");
				script.setAttribute("class", "qrcode");
				$("head")[0].appendChild(script);
				script.onload = function () {
					var _value = newSelects[0].organization_type;
					$("#qrCode_truck").qrcode({
						render: "table", //table方式
						width: 200, //宽度
						height: 200, //高度
						text: _value //任意内容
					});
				}
			}
		});
		$("#CarsmyModal").modal()
	}else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
		return false;
	}
}

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