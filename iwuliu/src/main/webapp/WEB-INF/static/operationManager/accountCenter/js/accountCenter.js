var timeEnd;
$(function(){
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".accountCenter").parents(".collapse").addClass("in");
		$(".accountCenter").addClass("menuActive");
		roleManager()
	})
	//账户类型
	new customDropDown($(".accountType"));
	$(".accountType").find("li").bind("click",function(){
		var _index = $(this).index();
		switch (_index){
			case 0:
				$(".drivers").removeClass("hidden");
				$(".cars").addClass("hidden")
				$('#accountName').val("");
				$('#start_time').val("");
				$('#end_time').val("");
				$('#companyManagerTable').bootstrapTable('removeAll');
				break;
			case 1:
				$(".drivers").addClass("hidden");
				$(".cars").removeClass("hidden");
				$('#accountName').val("");
				$('#start_time').val("");
				$('#end_time').val("");
				$('#carManagerTable').bootstrapTable('removeAll');
				break;
			default:
				break;
		}
	})

	$(".myModalRechargeAndWithdrawalBtn").find(".btn").click(function(){
		var _index = $(this).index();
		$(this).addClass("clickActive").siblings().removeClass("clickActive")
		switch(_index){
			case 0:
				$(".recharge").removeClass("hidden");
				$(".withdrawal").addClass("hidden");
				console.log(timeEnd)
				if(timeEnd == undefined){

				}else{
					console.log(timeEnd);
					clearInterval(timeEnd);
					$(".codeClick").attr("isClick","true");
					$(".codeClick").html("点击获取验证码");
				}
				break;
			case 1:
				$(".recharge").addClass("hidden")
				$(".withdrawal").removeClass("hidden")
				break;
		}
	})
	//充值提现
	$(".myMoneyModalRechargeAndWithdrawalBtn").find(".btn").click(function(){
		var _index = $(this).index();
		$(this).addClass("clickActive").siblings().removeClass("clickActive")
		switch(_index){
			case 0:
				$(".Moneyrecharge").removeClass("hidden");
				$(".Moneywithdrawal").addClass("hidden");
				break;
			case 1:
				$(".Moneyrecharge").addClass("hidden")
				$(".Moneywithdrawal").removeClass("hidden")
				break;
		}
	})

	//转入转出提现模态框消失
	$("#myModalRechargeAndWithdrawal").on("hidden.bs.modal",function(){
		$(".myModalRechargeAndWithdrawalBtn").find(".btn").eq(0).click()
	})
	//充值提现模态框消失
	$("#myModalTopUp").on("hidden.bs.modal",function(){
		$(".myMoneyModalRechargeAndWithdrawalBtn").find(".btn").eq(0).click()
	})
})
//点击获取验证码
function getCode(ele){
	if($(ele).attr("isClick") == "true"){
		$(ele).attr("isClick","false");
		var i = 60
		$(ele).html("<i>"+ i +"</i>s后重新获取");
		timeEnd = setInterval(function(){
			i--;
			$(ele).html("<i>"+ i +"</i>s后重新获取");
			if(i == 0){
				clearInterval(timeEnd);
				$(ele).attr("isClick","true");
				$(ele).html("点击获取验证码");
			}
		},1000);
	}
}

function accountQuery() {
	var _text = $(".accountType").find(".placeHolder").text();
		if(_text == '车主账户'){
			$('#companyManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/accountCenterManager/accountList'});
		}else{
			$('#carManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/accountCenterManager/accountList'});
		}
}

$(function(){
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
})
//获取公司名
$(function(){
	$.ajax({
		type:"post",
		url:"/iwuliu/accountCenterManager/searchCompanyName",
		cache:"false",
		success:function(object){
			var obj = eval(object);
			var account_name = obj.company_name;
			var account_id = obj.company_id;
			sessionStorage.setItem("company_id",account_id);
			console.log(account_name);
			console.log(account_id);
			if(account_id == '742C9E4DFC6940689A5D0F7CF6A69649'){
				$(".accountDept").find(".placeHolder").text("全部");
				$(".accountDept").find(".placeHolder").attr("value","0");
			}else{
				$(".accountDept").find(".placeHolder").text(account_name);
				$(".accountDept").find(".placeHolder").attr("value",account_id);
			}
			if(account_id == '742C9E4DFC6940689A5D0F7CF6A69649'){
				$.ajax({                                          ////获取物流公司列表
					type: 'post',
					url: '/iwuliu/accountCenterManager/searchCompanyList',
					dataType: 'json',
					cache: 'false',
					success: function (data) {
						var str = "<li value=" +"0"+ "><a href='javascript:;'>" + "全部" + "</a></li>";
						for (var i = 0; i < data.length; i++) {
							str += "<li value=" + data[i].company_id + "><a href='javascript:;'>" + data[i].company_name + "</a></li>"
						}
						$(".accountDept").find(".dropdown-menu").html(str);
						var  accountDept = new customDropDown($(".accountDept"));
					}
				})
			}
		},
		error:function(){
			alert('There are some errors happened');
		}
	})
})






//条件重置
function accountManagerQueryReset() {
	//var account_type =   $(".accountDept").find(".placeHolder").text();
	//var account_value = $(".accountDept").find(".placeHolder").attr("value");
	var  company_id = sessionStorage.getItem("company_id");
	if(company_id == "742C9E4DFC6940689A5D0F7CF6A69649"){
			var _text = $(".accountType").find(".placeHolder").text();
			if(_text == '车主账户'){
				$(".accountType").find(".placeHolder").text("车主账户");
				$(".accountDept").find(".placeHolder").text("全部");
				$(".accountDept").find(".placeHolder").attr("value","0");
				$('#accountName').val("");
				$('#start_time').val("");
				$('#end_time').val("");
				$('#companyManagerTable').bootstrapTable('removeAll');
			}else{
				$(".accountType").find(".placeHolder").text("车辆账户");
				$(".accountDept").find(".placeHolder").text("全部");
				$(".accountDept").find(".placeHolder").attr("value","0");
				$('#accountName').val("");
				$('#start_time').val("");
				$('#end_time').val("");
				$('#carManagerTable').bootstrapTable('removeAll');
			}
		}

	else {
		var _text = $(".accountType").find(".placeHolder").text();
		if(_text == '车主账户'){
			$(".accountType").find(".placeHolder").text("车主账户");
			$('#accountName').val("");
			$('#start_time').val("");
			$('#end_time').val("");
			$('#companyManagerTable').bootstrapTable('removeAll');
		}else{
			$(".accountType").find(".placeHolder").text("车辆账户");
			$('#accountName').val("");
			$('#start_time').val("");
			$('#end_time').val("");
			$('#carManagerTable').bootstrapTable('removeAll');
		}
	}
}


$(function() {
	// 时间查询日历框
	$('.form_datetime').datetimepicker({
		minView: "month", //选择日期后，不会再跳转去选择时分秒
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		todayBtn: 1,
		autoclose: 1
	});
})

//配置参数
function companyManagerQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		account_dept:$(".accountDept").find(".placeHolder").attr("value"),
		account_type:$(".accountType").find(".placeHolder").text(),//$("#accountType option:selected").text(),
		account_name:$("#accountName").val(),
		start_time: $("#start_time").val(),//$("#accountType option:selected").text(),
		end_time: $("#end_time").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}

//配置参数
function carManagerQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		account_dept:$(".accountDept").find(".placeHolder").attr("value"),
		account_type:$(".accountType").find(".placeHolder").text(),//$("#accountType option:selected").text(),
		account_name: $("#accountName").val(),
		start_time: $("#start_time").val(),//$("#accountType option:selected").text(),
		end_time: $("#end_time").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}

// 格式化“操作”列
function companyFormatter(){
	return '<a class="NewDetail btn btn-primary" type="button" onclick="companyOperation(this.parentElement.parentElement)">' +
			'充值/提现</a>';
}

// 格式化“操作”列
function carFormatter(){
	return '<a class="NewDetail btn btn-primary" type="button" onclick="carOperation(this.parentElement.parentElement)">' +
			'转入/转出</a>';
}

function companyOperation(data){
	$("#myModalTopUp").modal();
}

function carOperation(data){
	$("#myModalRechargeAndWithdrawal").modal();
}
