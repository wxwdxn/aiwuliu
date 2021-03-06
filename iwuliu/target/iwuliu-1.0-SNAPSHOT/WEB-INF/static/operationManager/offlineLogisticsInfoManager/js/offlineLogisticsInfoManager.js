// 密码正则
var regExpPsd = /^.{6,20}$/;
//不能不包含特殊符号
var specialWord=/^([\u4E00-\u9FA5]|\w)*$/;
var company_id;

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
});

// 全局
$(function(){
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".offlineLogisticsInfoManager").parents(".collapse").addClass("in");
		$(".offlineLogisticsInfoManager").addClass("menuActive");
		roleManager();
	});
	// 加载详情内容
	$.ajax({
		type: "post",
		url: "/iwuliu/offlineLogisticsInfoManager/getOfflineCompanyInfo",
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
				company_id = JsonData.company_id;
				$(".Detail_ModalQRCode").qrcode({
					render: "table", //table方式
					width: 200, //宽度
					height: 200, //高度
					text: company_id //任意内容
				});
			}

		}
	});

	// 修改密码模态框消失
	$("#ModalPwd").on("hidden.bs.modal",function(){
		$("#ModalPwd").find("input").val("");
		$("#ModalPwd").find("p").html("");
	})

	//点击修改密码切换
	$(".btn_pwdType").find(".btn").click(function(){
		console.log();
		var _index = $(this).index();
		$(this).addClass("clickActive").siblings().removeClass("clickActive")
		switch(_index){
			case 0:
				$(".lPwd").removeClass("hidden")
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

//点击修改密码
function btn_ModalPwd(){
	$("#ModalPwd").modal()
}

// 修改登录密码，点击确认按钮
function editLoginPassword(){
	if (checkPsd($(".old_member_password"))&checkPsd($(".new_member_password"))
			&checkPsd($(".new_member_password_again"))
			&checkAgainPSW($(".new_member_password"),$(".new_member_password_again"))){
		// 封装表单中的参数
		var editPswInfo = {
			"company_id":company_id,
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
						window.top.location.href = "/iwuliu/login/memberHome";
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

// 修改支付密码，点击确认按钮
function editPaymentPassword(){
	if (checkPsd($(".old_payment_password"))&checkPsd($(".new_payment_password"))
			&checkPsd($(".new_payment_password_again"))
			&checkAgainPSW($(".new_payment_password"),$(".new_payment_password_again"))){
		// 封装表单中的参数
		var editPswInfo = {
			"company_id":company_id,
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

