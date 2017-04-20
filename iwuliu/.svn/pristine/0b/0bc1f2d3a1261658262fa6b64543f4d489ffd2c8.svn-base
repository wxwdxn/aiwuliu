var regExpMob = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; //手机号码正则验证准确版

var regPhone=/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;//固定电话验证
//身份证正则表达式(15位)
isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
//身份证正则表达式(18位)
isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
var numbers= /^\+?[1-9][0-9]*$/;//验证数字

$(function(){
	// 加载当前页面菜单
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".ownerManager").parents(".collapse").addClass("in");
		$(".ownerManager").addClass("menuActive");
		roleManager();
	});
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
	//翻页
	var table = $('#ownerList'),
			page = $('#ownerListTablepage'),
			goBtn = $('#ownerListTablegoBtn');
	//go按钮加载
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});

	//重置按钮
	$('.reset').click(function(){
		$("#person_name").val("");
		$("#person_mobile_phone").val("");
		$("#ownerName").val("");
		$("#id_card_number").val("");
	});
	//查询
	$(".searchOwnerList").click(function () {

		var type=$(".shipperList").find(".placeHolder").attr("value");
		if (type==0){
			$('#ownerList').bootstrapTable('hideColumn','ownerName');
			$('#ownerList').bootstrapTable('showColumn','idCardNumber');
		}else {
			$('#ownerList').bootstrapTable('showColumn','ownerName');
			$('#ownerList').bootstrapTable('hideColumn','idCardNumber');
		}
		$('#ownerList').bootstrapTable('refresh', {'url': '/iwuliu/ownerManager/getOwnerList'});
	});
	//货主类型下拉
	var shipperType = new customDropDown($(".shipperType"));
	$(".shipperType").find(".dropdown-menu li").bind("click",function(){
		var _value = $(this).attr("value");
		switch(_value){
			case "0":
				$(".individualOwner").removeClass("hidden")
				$(".companyOwner").addClass("hidden")
			break;
			case "1":
				$(".individualOwner").addClass("hidden")
				$(".companyOwner").removeClass("hidden")
			break;
		}
	})

})
//保存货主
function holdOwner(){
	var type=$(".newAdd_shipperType").find(".placeHolder").attr("value")
	//公司货主保存  1公司 0个人货主
	if(type==1){
		if(checkSelectNotNull($(".newAdd_shipperType"))&checkSelectNotNull($(".provinceJson"))&checkSelectNotNull($(".cityJson"))&checkSelectNotNull($(".areaJson"))&checkNotNull($("#town"))&
				checkNotNull($("#company_name"))&checkNotNull($("#contact_name"))&checkNotNull($("#contact_mobile_phone"))
				&checkNotNull($("#business_licence"))&regFixPhone($("#company_fixed_phone"))&checkSelectNotNull($(".sexJson"))){
			var _inputs = $(".company").find("input");
			var _inputLength = $(".company").find("input").length;
			var _selects = $(".company").find(".placeHolder");
			var _selectLength = $(".company").find(".placeHolder").length;
			var _goodTypeInfo = {};
			for(var i = 0;i<_inputLength;i++){
				var _addAttr = _inputs[i].getAttribute("addAttr");
				_goodTypeInfo[_addAttr] = _inputs[i].value;
			}
			for(var i = 0;i<_selectLength;i++){
				var _addAttr = _selects[i].getAttribute("addAttr");
				_goodTypeInfo[_addAttr] =_selects[i].getAttribute("value");
			}
			var  stringObj=JSON.stringify(_goodTypeInfo);
			$.ajax({
				type:"post",
				url:"/iwuliu/ownerCompanyManager/saveOwnerCompany",
				data:{"list":stringObj},
				async:true,
				success:function(data){
					var obj = eval(data);
					if(obj.result==1){
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("保存成功！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
							$("#myModalNewAdd").modal('hide')
							$('#ownerList').bootstrapTable('refresh');
						});
					}else if (obj.result==0){
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("营业执照号码重复！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}else {
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
		}else{
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("请填写数据！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}
	}else {
		//个人货主保存
		if (checkNotNull($("#personName")) & checkNotNull($("#phones")) & checkNotNull($("#numbers")) & checkSelectNotNull($(".sexJson"))) {
			var _inputs = $(".person").find("input");
			var _inputLength = $(".person").find("input").length;
			var _selects = $(".person").find(".placeHolder");
			var _selectLength = $(".person").find(".placeHolder").length;
			var _goodTypeInfo = {};
			for (var i = 0; i < _inputLength; i++) {
				var _addAttr = _inputs[i].getAttribute("addAttr");
				_goodTypeInfo[_addAttr] = _inputs[i].value;
			}
			for (var i = 0; i < _selectLength; i++) {
				var _addAttr = _selects[i].getAttribute("addAttr");
				_goodTypeInfo[_addAttr] = _selects[i].getAttribute("value")
			}
			var stringObj = JSON.stringify(_goodTypeInfo);
			$.ajax({
				type: "post",
				url: "/iwuliu/ownerManager/saveOwner",
				data: {"list": stringObj},
				async: true,
				success: function (data) {
					var obj = eval(data);
					if(obj.result==1){
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("保存成功！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
							$("#myModalNewAdd").modal('hide')
							$('#ownerList').bootstrapTable('refresh');
						});
					}else {
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("保存失败！")
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
			});

		} else {//没有填写数据
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("请填写数据！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}
	}
}
//点击添加新增货主
function btn_NewAdd(){
	$("#myModalNewAdd").modal();
	$("#myModalNewAdd").find("input").val("");
	$("#myModalNewAdd").find("p").html("");
	$("#myModalNewAdd").find(".placeHolder").html("请选择");
	$("#myModalNewAdd").find(".placeHolder").removeAttr("value");
	var newAdd_shipperType = new customDropDown($(".newAdd_shipperType"));
	$(".newAdd_shipperType").find(".dropdown-menu li").bind("click",function(){
		var _value = $(this).attr("value");
		switch(_value){
			case "0":
				$(".individual").removeClass("hidden")
				$(".company").addClass("hidden")
			break;
			case "1":
				$(".individual").addClass("hidden")
				$(".company").removeClass("hidden")
			break;
		}
	})
}

//点击查看详情
function btn_ModalDetail(){
	var selects = $('#ownerList').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		var companyId=newSelects[0].companyId;
		var personId=newSelects[0].personId;
		if (companyId!=""){
			$(".companyOwner").removeClass("hidden");
			$('.individualOwners').addClass("hidden")
			$.ajax({
				type:"post",
				url:"/iwuliu/ownerCompanyManager/findCompanyOwnerDetailByCompanyId",
				data:{"companyId":companyId},
				success:function(data) {
					var JsonData = JSON.parse(data);
					$("#companyName").val(JsonData.companyName);
					$("#contactName").val(JsonData.contactName);
					$("#phone").val(JsonData.phone);
					$("#licence").val(JsonData.licence);
					$("#fixPhone").val(JsonData.fixPhone);
					$("#townStreet").val(JsonData.townStreet);
					$("#types").val(JsonData.types);
					$("#sex").val(JsonData.sex);
					$(".companyOwner").find(".cityId").find(".placeHolder").html(JsonData.cityId);
					$(".companyOwner").find(".areaId").find(".placeHolder").html(JsonData.areaId);
					$(".companyOwner").find(".provinceId").find(".placeHolder").html(JsonData.provinceId);
					$("#myModal").modal();
				}
			})

		}else if(personId!=""){
			$(".companyOwner").addClass("hidden");
			$('.individualOwners').removeClass("hidden")
			//个人货主
			$.ajax({
				type:"post",
				url:"/iwuliu/ownerManager/findPersonShipperById",
				data:{"personId":personId},
				success:function(data){
					var JsonData =JSON.parse(data);
					$("#Mophone").val(JsonData.phone);
					$("#name").val(JsonData.name);
					$("#types").val(JsonData.types);
					$("#personSex").val(JsonData.sex);
					$("#number").val(JsonData.number);
					$("#myModal").modal();
				}
			})
		}
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
//配置参数
function ownerListTable(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		sort: params.sort,  //排序列名
		sortOrder: params.order,//排位命令（desc，asc）
		types:$(".shipperList").find(".placeHolder").attr("value"),
		ownerName:$("#ownerName").val(),
		id_card_number:$("#id_card_number").val(),
		person_mobile_phone:$("#person_mobile_phone").val(),
		person_name:$("#person_name").val()

	};
	return temp;
}

//获取性别
$.ajax({
	type:'post',
	url:'/iwuliu/dictionaryManager/sexJson',
	dataType:'json',
	cache:'false',
	success:function(data){
		$(".sexJson").find(".placeHolder").html('请选择');
		var sex='';
		for (var i= 0;i<data.length;i++){
			sex+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
		}
		$(".sexJson").find(".dropdown-menu").html(sex);
		var sexJson = new customDropDown($(".sexJson"));
	},
	error:function(){
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("系统错误！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}
});

//获取省市县
$.ajax({
	type:'post',
	url:"/iwuliu/dictionaryManager/provinceJson",
	dataType:'json',
	cache:false,
	success:function(data){
		var _strValue = '';
		for (var i= 0;i<data.length;i++){
			_strValue+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
		}
		$(".provinceJson").find(".dropdown-menu").html(_strValue);
		var provinceJsonList = new customDropDown($(".provinceJson"));
		$(".provinceJson").find(".placeHolder").html('请选择');
		$(".areaJson").find(".placeHolder").html('请选择');
		$(".cityJson").find(".placeHolder").html('请选择');

		$(".provinceJson").find(".dropdown-menu li").bind("click",function(){
			var province = $(this).attr("value");
			$(".cityJson").find(".placeHolder").html('请选择');
			$(".areaJson").find(".placeHolder").html('请选择');
			if (province!=''||province!='undefined'){
				//获取城市
				$.ajax({
					type: "POST",
					url: "/iwuliu/dictionaryManager/cityJson",
					cache: false,
					data:{"dicdata_code":province},
					dataType: "json",
					success: function (data) {
						var _strd = '';
						for (var i= 0;i<data.length;i++){
							_strd+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
						}
						$(".cityJson").find(".dropdown-menu").html(_strd);
						var cityJsonList = new customDropDown($(".cityJson"));
						$(".cityJson").find(".dropdown-menu li").bind("click",function(){
							var cityJson = $(this).attr("value");
							//获取卸货货场
							$.ajax({
								type: "POST",
								url: "/iwuliu/dictionaryManager/areaJson" ,
								cache: false,
								data:{"dicdata_code":cityJson},
								dataType: "json",
								success: function (data) {
									var _strs = '';
									for (var i= 0;i<data.length;i++){
										_strs+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
									}
									$(".areaJson").find(".dropdown-menu").html(_strs);
									var areaJsonList = new customDropDown($(".areaJson"));

								},
								error:function(){
									$("#smallModalInfo").modal();
									$("#smallModalInfo").find(".titleInfo").html("系统错误！")
									$("#smallModalInfo").on("hidden.bs.modal",function(e){
										$("body").addClass("modal-open");
									});
								}
							});
						})
					}
				})
			}
		})
	},
	error:function(){
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("系统错误！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
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
	var _value = inp.find(".placeHolder").attr("value");
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
//验证公司货主手机号码
function loadPhone(ele){
	var total=$(ele).val();
	if(total != ""){
		if (regPhone.test(total)){
			$(ele).css("border","none");
			$(ele).parent().find("p").html("");
			return true;
		}else {
			$(ele).css("border","1px solid red");
			$(ele).parent().find("p").html("请输入正确的号码！");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	}else{
		$(ele).css("border","none");
		$(ele).parent().find("p").html("请输入号码");
		$(ele).parent().find("p").css("color","red");
		return false;
	}

}
//验证货物数量数字
function checkNumber(ele){
	var total=$(ele).val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
		return true;
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
		return false;
	}
}

//验证公司货主固定电话
function regFixPhone(ele){
	var total=$(ele).val();
	if(total != ""){
		if (regPhone.test(total)){
			$(ele).parent().find("p").html("");
			return true;
		}else {
			$(ele).parent().find("p").html("请输入正确的号码！");
			return false;
		}
	}else{
		$(ele).parent().find("p").html("请输入号码");
		$(ele).parent().find("p").css("color", "red");
		return false;
	}

}
//验证个人货主手机号码
function phone(ele){
	var total=$(ele).val();
	if(total != ""){
		if (regExpMob.test(total)){
			$(ele).css("border","none");
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