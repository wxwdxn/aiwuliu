$(function(){
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".schedulingOrderManager").parents(".collapse").addClass("in");
		$(".schedulingOrderManager").addClass("menuActive");
		roleManager();
	})
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
	//初始页面
	//翻页
	var table = $('#scheduleOrderTable'),
			page = $('#scheduleOrderTablePage'),
			goBtn = $('#scheduleOrderTablePagegoBtn');
	//go按钮加载
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});
	//goBtn按钮无数据时隐藏 详情
	var webservice = $('#scheduleOrderTable').attr("data-url");
	$.ajax({
		url:webservice,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});

	//运单详情翻页
	var table2 = $('#scheduleFlowTable'),
			page2 = $('#scheduleFlowTablepage'),
			goBtn2 = $('#scheduleFlowTablegoBtn');
	//go按钮加载
	goBtn2.click(function () {
		table2.bootstrapTable('selectPage', +page2.val());
	});
	//goBtn按钮无数据时隐藏 详情
	var webservice2 = $('#scheduleFlowTable').attr("data-url");
	$.ajax({
		url:webservice2,
		success:function(data){
			if(data.length == 0){
				$(".form-inline").css("display","none");
			}
		}
	});

	//获取干线
	$.ajax({
		type:'post',
		url:"/iwuliu/operateMainLineManager/operateMainLines",
		dataType:'json',
		cache:false,
		success:function(data) {
			var _strValue = '';
			for (var i = 0; i < data.length; i++) {
				_strValue += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
			}
			$(".mainLine").find(".dropdown-menu").html(_strValue);
			var mainLine = new customDropDown($(".mainLine"));
		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("获取干线出错！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}

	})


	//获取车辆型号
	$.ajax({
		type:'post',
		url:'/iwuliu/vehicleManager/truckModelLists',
		dataType:'json',
		cache:'false',
		success:function(data) {
			var _strValue = '';
			for (var i = 0; i < data.length; i++) {
				_strValue += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
			}
			$(".truckType").find(".dropdown-menu").html(_strValue);
			var mainLine = new customDropDown($(".truckType"));
		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("获取车辆型号出错！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}

	});


	//时间设置
	$('#endTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii:00',
		language: 'zh-CN',
		autoclose: true,//选中之后自动隐藏日期选择框
		hourStep: 1,
		minuteStep: 30,
		todayBtn:true,
	});
	$('#startTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii:00',
		autoclose: true,//选中之后自动隐藏日期选择框
		language: 'zh-CN',
		hourStep: 1,
		minuteStep: 30,
		todayBtn:true,
	});
});

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
//查询
function searchScheduleOrder(){
	$('#scheduleOrderTable').bootstrapTable('refresh',{'url':'/iwuliu/schedulingOrderManager/findScheduleByConditions'});

}
//配置参数
function scheduleOrderTableParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		schedulePlanNumber:$("#schedulePaln").val(),
		truckPlate:$("#plateNumber").val(),
		truckModel:$(".truckType").find(".placeHolder").attr("value"),
		lineName:$(".mainLine").find(".placeHolder").attr("value"),
		startTime:$("#startTime").val(),
		endTime:$("#endTime").val(),
		person:$("#person").val(),
		personPhone:$("#personPhone").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}
//点击详情
function btn_ModalDetail(){
	var selects = $('#scheduleOrderTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		var schedulingSheetId=newSelects[0].schedulingSheetId;
		$.ajax({
			type:"post",
			url:"/iwuliu/schedulingOrderManager/schedulingOrderDetail",
			data:{"schedulingSheetId":schedulingSheetId},
			success:function(data){
				var JsonData =JSON.parse(data);
				$("#planNumber").val(JsonData.planNumber);
				$("#lineName").val(JsonData.lineName);
				$("#modelName").val(JsonData.modelName);
				$("#total").val(JsonData.total);
				$("#truckNumber").val(JsonData.plateNumber);
				$("#offTime").val(JsonData.offTime);
				$("#receveTime").val(JsonData.receveTime);
				$("#loadWeight").val(JsonData.loadWeight);
				$("#loadStart").val(JsonData.loadStart);
				$("#loadEnd").val(JsonData.loadEnd);
				$("#loadPath").html("<img  onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ JsonData.loadPath +">");
				$("#unloadposition").val(JsonData.unloadposition);
				$("#loadposition").val(JsonData.loadposition);
				$("#unloadStart").val(JsonData.unloadStart);
				$("#unloadEnd").val(JsonData.unloadEnd);
				$("#unloadPath").html("<img  onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ JsonData.unloadPath +">");
				$("#unitPrice").val(JsonData.unitPrice);
				$("#status").val(JsonData.status);
				$("#unloadWeight").val(JsonData.unloadWeight);
				$("#myModal").modal()
			}
		});
		$('#scheduleFlowTable').bootstrapTable('refresh',{'url':'/iwuliu/schedulingOrderManager/scheduleFlowTable?schedulingSheetId='+schedulingSheetId});

	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}

}

// table表格隔行变色
function rowStyle(row, index) {
	if (index % 2 === 0) {
		return{};
	}
	return {
		css: {"background-color": "#eefff9"}
	};
}
//重置
function resetSchedule(){
	$("#schedulePaln").val("");
	$("#plateNumber").val("");
	$("#personPhone").val("");
	$("#person").val("");
	$("#startTime").val("");
	$("#endTime").val("");
	$(".truckType").find(".placeHolder").removeAttr("value");
	$(".mainLine").find(".placeHolder").removeAttr("value");
	$(".truckType").find(".placeHolder").html("请选择");
	$(".mainLine").find(".placeHolder").html("请选择");
}


//支出凭证显示汉字
var imgurl;
function imgFormatter(value,row,index){
	imgurl=value;
	if(value!=""&& value!=null){
		return '<a href="javascript:void(0)" onclick="loadingProofShow(this)"><span style="color: green">' + '已上传图片' + '</span></a>';
	}
	else{
		return '<span style="color: #FF0000" >' + '未上传图片' + '</span>';
	}
}

//点击上传图片时图片变大
function loadingProofShow(ele) {
	var _src = "/iwuliu/contentManager/contentDownload?fileUrl"+imgurl;
	$(".modalImg").html("<img src="+ _src +"/>");
	$('#ModalImg').modal();
	//图片放大模态框隐藏后触发的事件
	$("#ModalImg").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open")
	})
}