$(function(){
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".vehiclePlan").parents(".collapse").addClass("in");
		$(".vehiclePlan").addClass("menuActive");
		roleManager();
	})
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
	//手动派单模态框消失
	$("#mymanuallyAssign").on("hidden.bs.modal",function(){
		$(".tranPlan").find(".placeHolder").removeAttr("value")
		$(".tranPlan").find(".placeHolder").html("请选择");
		$("#cargoType").val("");
		$("#unloadCargo").val("");
		$("#lineName").val("");
		$("#loadCargo").val("");
		$("#undistributedAmount").val("");
		$("#price").val("");
		$("#startTime").val("");
	})
	//详情模态框消失
	$("#myModalContractDetail").on("hidden.bs.modal",function(){
		$("#myModalContractDetail").find(".edit").attr("isClick","true");
		$("#myModalContractDetail").find(".edit").removeAttr("disabled");
		$("#myModalContractDetail").find(".edit").css("background","#69a2b5");
		$("#myModalContractDetail").find(".hidden").removeClass("hidden");
		$(".routerInfo").remove();
		$(".newAdd_router").addClass("hidden");
	})
	//goBtn按钮无数据时隐藏 页面管理初始表格
	var webservice = $('#manualTruckSheet').attr("data-url");
	var webservice2 = $('#manualUnFinishedTruckSheet').attr("data-url");
	$.ajax({
		url:webservice,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});
	$.ajax({
		url:webservice2,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});

	var table = $('#manualTruckSheet'),
			page = $('#manualTruckSheetpage'),
			goBtn = $('#manualTruckSheetgoBtn');

	// 跳转到某页
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});

	var table2 = $('#manualUnFinishedTruckSheet'),
			page2 = $('#manualUnFinishedTruckSheetpage'),
			goBtn2 = $('#manualUnFinishedTruckSheetgoBtn');

	// 跳转到某页
	goBtn2.click(function () {
		table2.bootstrapTable('selectPage', +page2.val());
	});

	//手工分配保存派单
	$(".addTruckSheet").click(function (){
		var plateNumber=$("#plateNumber").val();
		var beginDate=$("#startTime").val();
		var planNumber=	$(".tranPlan").find(".placeHolder").attr("value")
		var params = {};
		params["planNumber"]=planNumber;
		params["plateNumber"]=plateNumber;
		params["beginDate"]=beginDate;
		var paramsList=JSON.stringify(params);
		if (planNumber==''|| typeof(planNumber) == "undefined"){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("计划编号不能为空！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}else {
			$.ajax({
				type:"post",
				url:"/iwuliu/ScheduleSheetManager/addScheduleSheet",
				data:{"list":paramsList},
				async:true,
				success:function(data){
					var obj = eval(data);
					if(obj.result==1){
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("保存成功！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
							$("#mymanuallyAssign").modal('hide')
							$('#manualUnFinishedTruckSheet').bootstrapTable('refresh');
							$('#manualTruckSheet').bootstrapTable('refresh');
						});
					}else{
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("保存失败！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				},
				error:function(){
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("系统错误！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			});
		}

	});


	//可用列表详情的表格设置
	//goBtn按钮无数据时隐藏 页面管理初始表格
	var webservice3 = $('#manualTruckSheetTable').attr("data-url");
	var webservice4 = $('#manualTruckSheetTable2').attr("data-url");
	var table3 = $('#manualTruckSheetTable'),
			page3 = $('#manualTruckSheetTablepage'),
			goBtn3 = $('#manualTruckSheetTablegoBtn');

	// 跳转到某页
	goBtn3.click(function () {
		table3.bootstrapTable('selectPage', +page3.val());
	});
	var table4 = $('#manualTruckSheetTable2'),
			page4 = $('#manualTruckSheetTablepage2'),
			goBtn4 = $('#manualTruckSheetTablegoBtn2');

	// 跳转到某页
	goBtn4.click(function () {
		table4.bootstrapTable('selectPage', +page4.val());
	});
	$.ajax({
		url:webservice3,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});
	$.ajax({
		url:webservice4,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});


	//不可用列表详情的表格设置
	//goBtn按钮无数据时隐藏 页面管理初始表格
	var webservice5 = $('#manualTruckSheetTable5').attr("data-url");
	var webservice6 = $('#manualTruckSheetTable6').attr("data-url");
	var table5 = $('#manualTruckSheetTable5'),
			page5 = $('#manualTruckSheetTable5'),
			goBtn5 = $('#manualTruckSheetTable5');

	// 跳转到某页
	goBtn5.click(function () {
		table5.bootstrapTable('selectPage', +page5.val());
	});
	var table6 = $('#manualTruckSheetTable6'),
			page6 = $('#manualTruckSheetTable6'),
			goBtn6 = $('#manualTruckSheetTable6');

	// 跳转到某页
	goBtn6.click(function () {
		table6.bootstrapTable('selectPage', +page6.val());
	});
	$.ajax({
		url:webservice6,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});
	$.ajax({
		url:webservice5,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});
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


//配置参数
function queryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}
//手工分配
var truckId//选中的车辆id
function btn_manuallyAssign(){
	var selects = $('#manualTruckSheet').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		var newSelects = $.parseJSON(JSON.stringify(selects));
		truckId=newSelects[0].truckId;
     //详情赋值
		$.ajax({
			type:"post",
			url:"/iwuliu/companyManager/manualTruckDetails",
			data:{"truckId":truckId},
			success:function(data){
				var JsonData =JSON.parse(data);
				$("#plateNumber").val(JsonData.plateNumber);
				$("#status").val(JsonData.status);
				$("#position").val(JsonData.position);
				$("#loadWeight").val(JsonData.loadWeight);
			}
		});
		//获取可运输的计划编号
		$.ajax({
			type:'post',
			url:'/iwuliu/transportationOrderManager/sheetPlanByTruck',
			data:{"truckId":truckId},
			dataType:'json',
			cache:'false',
			success:function(data){
				var _strValue='';
				if (data.length!=0){
					for (var i= 0;i<data.length;i++){
						_strValue+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
					}
					$(".tranPlan").find(".dropdown-menu").html(_strValue);
					var mainLine = new customDropDown($(".tranPlan"));
					$(".tranPlan").find(".dropdown-menu li").bind("click",function() {
						var tranPlanId = $(this).attr("value");
						//根据计划赋值
						if (tranPlanId!=''||tranPlanId!='undefined'){
							$.ajax({
								type: "POST",
								url: "/iwuliu/transportationOrderManager/manualTruckDetailsByPlan",
								data:{"planNumber":tranPlanId},
								success: function (data) {
									var JsonData =JSON.parse(data);
									$("#cargoType").val(JsonData.cargoTypeName);
									$("#unloadCargo").val(JsonData.unloadingName);
									$("#lineName").val(JsonData.lineName);
									$("#loadCargo").val(JsonData.loadingName);
									$("#undistributedAmount").val(JsonData.unRedistributeTotal);
									$("#price").val(JsonData.price);
									$("#startTime").val(JsonData.beginDate);
								}
							});
						}
					})
				}else {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("没有可运输计划！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			},
			error:function(){
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("查询出错！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}
		});
		$("#mymanuallyAssign").modal();
	}else{
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}

}
//可用计划详情
function btn_ModalDetail(){
	var selects = $('#manualTruckSheet').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		var newSelects = $.parseJSON(JSON.stringify(selects));
		var truck=newSelects[0].truckId;
		$.ajax({
			type:"post",
			url:"/iwuliu/companyManager/manualTruckDetails",
			data:{"truckId":truck},
			success:function(data){
				var JsonData =JSON.parse(data);
				$("#truckNumber").val(JsonData.plateNumber);
				$("#truckStatus").val(JsonData.status);
				$("#truckPosition").val(JsonData.position);
				$("#truckLoadWeight").val(JsonData.loadWeight);
			}
		});
		//刷新2个列表
		$('#manualTruckSheetTable').bootstrapTable('refresh',{'url':'/iwuliu/ScheduleSheetManager/findLookSheetByTruckId?truckId='+truck});
		$('#manualTruckSheetTable2').bootstrapTable('refresh',{'url':'/iwuliu/ScheduleSheetManager/findUnLookSheetByTruckId?truckId='+truck});
		$("#myModal").modal();
	}else{
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}
	
}

//多行删除 撤销可用的派单
function cancelSheetDummy(){
	var selectTions=$("#manualTruckSheetTable2").bootstrapTable("getSelections");
	if (selectTions.length != 0) {
		var OrderTableID = "";
		for (var i = 0; i < selectTions.length; i++) {
			if (i == 0) {
				OrderTableID = selectTions[i].planNumber;
			} else {
				OrderTableID += "," + selectTions[i].planNumber;
			}
		}
		$("#smallModalInfoDelete").modal();
		$("#smallModalInfoDelete").find(".titleInfoDelete").html("确认撤销？")
		//点击确定删除
		$(".deleteTruckType").click(function () {
			$.ajax({
				type: "post",
				url: "/iwuliu/ScheduleSheetManager/OrderTableDel",
				data:{"OrderTableID":OrderTableID},
				dataType:"json",
				cache:'false',
				success: function (data) {
					// json 类型的专为对象
					var obj = eval(data);
					if (obj.result == 0) {
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("撤销成功！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
						$('#manualTruckSheetTable2').bootstrapTable('refresh');
					} else {
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("撤销失败！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				},
				error: function () {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("系统错误！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			})
		})
	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}

};
//不可用计划详情
function btn_UnModalDetail(){
	var selects = $('#manualUnFinishedTruckSheet').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		var newSelects = $.parseJSON(JSON.stringify(selects));
		var car=newSelects[0].truckId;
		$.ajax({
			type:"post",
			url:"/iwuliu/companyManager/manualTruckDetails",
			data:{"truckId":car},
			success:function(data){
				var JsonData =JSON.parse(data);
				$("#carNumber").val(JsonData.plateNumber);
				$("#carStatus").val(JsonData.status);
				$("#carPosition").val(JsonData.position);
				$("#carLoadWeight").val(JsonData.loadWeight);
			}
		});
		//刷新2个列表
		$('#manualTruckSheetTable5').bootstrapTable('refresh',{'url':'/iwuliu/ScheduleSheetManager/findLookSheetByTruckId?truckId='+car});
		$('#manualTruckSheetTable6').bootstrapTable('refresh',{'url':'/iwuliu/ScheduleSheetManager/findUnLookSheetByTruckId?truckId='+car});
		$("#UnmyModal").modal();
	}else{
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}


}

//多行删除 撤销不可用的派单
function cancelUnSheetDummy(){
	var selectTions=$("#manualTruckSheetTable6").bootstrapTable("getSelections");
	if (selectTions.length != 0) {
		var OrderTableID = "";
		for (var i = 0; i < selectTions.length; i++) {
			if (i == 0) {
				OrderTableID = selectTions[i].planNumber;
			} else {
				OrderTableID += "," + selectTions[i].planNumber;
			}
		}
		$("#smallModalInfoDelete").modal();
		$("#smallModalInfoDelete").find(".titleInfoDelete").html("确认撤销？")
		//点击确定删除
		$(".deleteTruckType").click(function () {
			$.ajax({
				type: "post",
				url: "/iwuliu/ScheduleSheetManager/OrderTableDel",
				data:{"OrderTableID":OrderTableID},
				dataType:"json",
				cache:'false',
				success: function (data) {
					// json 类型的专为对象
					var obj = eval(data);
					if (obj.result == 0) {
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("撤销成功！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
						$('#manualTruckSheetTable2').bootstrapTable('refresh');
					} else {
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("撤销失败！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				},
				error: function () {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("系统错误！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			})
		})
	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}

};
