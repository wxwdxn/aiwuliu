var timeEnd;
$(function(){
	
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".logisticsAccountManager").parents(".collapse").addClass("in");
		$(".logisticsAccountManager").addClass("menuActive")
	});
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
	//充值提现模态框消失
	$("#myModalRechargeAndWithdrawal").on("hidden.bs.modal",function(){
		$(".myModalRechargeAndWithdrawalBtn").find(".btn").eq(0).click()
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
function a(){
	$("#myModalRechargeAndWithdrawal").modal()
}
