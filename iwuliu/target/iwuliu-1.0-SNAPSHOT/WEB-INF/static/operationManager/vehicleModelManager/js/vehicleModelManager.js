$(function(){
	$(".leftMenu").load("/iwuliu/welcomeManager/home", function () {
		$(".vehicleModelManager").parents(".collapse").addClass("in");
		$(".vehicleModelManager").addClass("menuActive")
		roleManager();
	});

	//详情模态框消失
	$("#myModal").on("hidden.bs.modal", function () {
		$("#myModal").find(".form-group").addClass("uneditable");
		$("#myModal").find("input").attr("disabled", "true");
		$("#myModal").find(".form-group").find("button").attr("disabled", "true");
		$("#myModal").find(".edit").attr("isClick", "true");
		$("#myModal").find(".edit").removeAttr("disabled");
		$("#myModal").find(".edit").css("background", "#69a2b5")
	})

	var table = $('#vehicleModelManagerTable'),
			vehicleModelManager_page = $('#vehicleModelManager_page'),
			vehicleModelManager_goBtn = $('#vehicleModelManager_goBtn');
// 跳转到某页
	vehicleModelManager_goBtn.click(function () {
		table.bootstrapTable('selectPage', +vehicleModelManager_page.val());
	});
	$(".th-inner").eq(0).append('<span>全选</span>');

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
			$(".vehicleModelManager_truckBrand").find(".dropdown-menu").html(str);
			var truckBrand1 = new customDropDown($(".vehicleModelManager_truckBrand"));
		},
	});

	//获取开户银行
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
			$(".detail_truck_brand").find(".dropdown-menu").html(str);
			var detailTruckBrand = new customDropDown($(".detail_truck_brand"));
		}
	});
});

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
});

//配置参数
function vehicleModelManagerQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		truck_brand_id: $(".vehicleModelManager_truckBrand").find(".placeHolder").attr("value"),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}

// 查询车辆型号管理列表
function vehicleModelManagerQuery() {
	$('#vehicleModelManagerTable').bootstrapTable('refresh', {
		'url': '/iwuliu/vehicleModelManager/vehicleModelManagerList'
	});
}

// 查询重置
function vehicleModelManagerQueryReset() {
	$('.placeHolder').html("全部");
	$('.placeHolder').attr("value", "");
	$(".sr-only").removeClass("sr-only")
	$('#vehicleModelManagerTable').bootstrapTable('removeAll');
}

//点击详情按钮
var truck_model_no;
function btn_ModalDetail(){
	var selects = $('#vehicleModelManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		truck_model_no = newSelects[0].truck_model_no;
		sessionStorage.setItem("truck_model_no", truck_model_no);
		$.ajax({
			type: "post",
			url: "/iwuliu/vehicleModelManager/vehicleModelManagerDetail",
			data: {truck_model_no: encodeURI(truck_model_no)},
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success: function (data) {
				var JsonData = JSON.parse(data);
				console.log(JsonData);
				// 让表单上所有p标签提示清空,避免编辑出现的提示保留到详情中
				$("#truckModelEditForm").find("p").html("");
				$(".truck_brand_name").html(JsonData.truck_brand_name);
				$(".truck_brand_name").attr("value", JsonData.truck_brand_code);// 绑定id
				$(".truck_model_name").val(JsonData.truck_model_name);
			}
		});
		$("#myModal").modal();

	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据")
		return false;
	}
}
//点击新增按钮
function btn_ModalNewAdd(){
	$("#myNewAddModal").modal();

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
			$(".add_truckBrand").find(".dropdown-menu").html(str);
			var truckBrand2 = new customDropDown($(".add_truckBrand"));
		},
	});
}

// 新增保存
function truckModelNewAddSave() {
	if (checkNotNull($("#add_truck_model_name")) & checkSelectNotNull($(".add_truckBrand").find(".placeHolder"))) {
		var obj = {};
		var _truckBrandNoValue = $(".add_truckBrand").find(".placeHolder").attr("value");
		var _truckModelName = $("#add_truck_model_name").val();
		obj["truck_brand_no"] = _truckBrandNoValue;
		obj["truck_model_name"] = _truckModelName;

		//开始ajax操作，data中传的是表单中的参数
		$("#truckModelNewAddForm").ajaxSubmit({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/vehicleModelManager/saveVehicleModel",//请求地址
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
						$('#myNewAddModal').modal('hide');
						vehicleModelManagerQuery();
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
function truckModelEditSave() {
	if (checkNotNull($(".truck_model_name")) & checkSelectNotNull($(".detail_truck_brand").find(".placeHolder"))) {
		var obj = {};
		var _truckBrandCodeValue = $(".detail_truck_brand").find(".placeHolder").attr("value");
		var _truckModelName = $(".truck_model_name").val();
		obj["truck_brand_code"] = _truckBrandCodeValue;
		obj["truck_model_name"] = _truckModelName;
		obj["truck_model_no"] = truck_model_no;

		console.log(obj);
		//开始ajax操作，data中传的是表单中的参数
		$("#truckModelEditForm").ajaxSubmit({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/vehicleModelManager/upDateVehicleModel",//请求地址
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
						$('#myModal').modal('hide');
						vehicleModelManagerQuery();
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

// 点击删除按钮
function vehicleModelDel() {
	var selects = $('#vehicleModelManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length != 0) {
		$(".titleInfoDelet").html("确定删除？")
		$("#smallModalInfoDelet").modal();
		$(".confirmDelet").click(function () {
			var station_ids = "";
			for (var i = 0; i < selects.length; i++) {
				if (i == 0) {
					vehicleModel_ids = selects[i].truck_model_no;
				} else {
					vehicleModel_ids += "," + selects[i].truck_model_no;
				}
			};
			console.log(vehicleModel_ids);
			$.ajax({
				type: "post",
				url: "/iwuliu/vehicleModelManager/vehicleModelManagerDel?temID=" + vehicleModel_ids,
				async: true,
				success: function (object) {
					// json 类型的专为对象
					var obj = eval(object);
					//  reload the user data
					if (obj.result == 0) {
						$('#vehicleModelManagerTable').bootstrapTable('refresh');
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